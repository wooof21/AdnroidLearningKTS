package com.adnroidlearningkts.jetpackcompose.apps.todolist.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.adnroidlearningkts.jetpackcompose.apps.todolist.navigation.Screens
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.screens.TodoListPage
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Action
import com.adnroidlearningkts.jetpackcompose.apps.todolist.viewmodel.SharedViewModel


/**
 * the `composable` fun is inside the scope of NavGraphBuilder,
 * to call it in the fun, have it extends from NavGraphBuilder
 */
fun NavGraphBuilder.todoListComponent(
    navigateToTaskDetailPage: (Int) -> Unit, sharedViewModel: SharedViewModel
) {

    composable<Screens.TodoList>(
    ) {navBackStackEntry ->

        //receive the action from TaskDetailPage
        val action = navBackStackEntry.toRoute<Screens.TodoList>().action
        var localAction by rememberSaveable { mutableStateOf(Action.NO_ACTION) }
        Log.i("AndroidLearningKts", "todoListComponent - action: ${action.toString()}")
        Log.i("AndroidLearningKts", "todoListComponent - localAction: ${localAction.toString()}")

        /**
         * rememberSaveable -> remembered even when device configuration changes
         * Problem:
         *         //update actionState in ViewModel for observe/to be handled in ViewModel
         *         //handle the action in TodoListPage
         *         LaunchedEffect(key1 = action) {
         *             sharedViewModel.actionState.value = action
         *         }
         *  1. List Page -> TaskDetails Page -> Add a new Task
         *  2. After adding: TaskDetails Page -> List Page -> actionState -> ADD
         *  3. LaunchedEffect triggers since List Page already in Back Stack with NO_ACTION
         *  4. actionState set to ADD and add the new Task to DB
         *  5. actionState reset to NO_ACTION
         *  6. Press Back Key: current List page popped, and back to previous TaskDetail page
         *  7. press Back key again: back to List page again, the NavBackStackEntry for ListPage
         *      might still hold the same action argument (Action.ADD) that it received when
         *      first navigated to it from TaskDetailsPage after adding the task.
         *  8. since actionState was reset to NO_ACTION, with the value of ADD from Back Stack,
         *      LaunchedEffect triggers again, then add the duplicate Task since DB Insert
         *      doesn't have checks for duplicates based on content
         *
         *  New Code Prevent this Issue:
         *  1. List Page -> TaskDetails Page -> Add a new Task
         *  2. After adding: TaskDetails Page -> List Page -> actionState -> ADD
         *      * action (from nav args) = Action.ADD.
         *      * localAction (from rememberSaveable) is initially Action.NO_ACTION.
         *  3. LaunchedEffect(key1 = localAction): Initially, localAction is Action.NO_ACTION.
         *      * action != localAction
         *      * localAction = action: sets the local localAction to Action.ADD.
         *      * sharedViewModel.actionState.value = action (ViewModel processes Action.ADD).
         *  4.The localAction state is now Action.ADD and is saved by rememberSaveable.
         *  5. Pressing Back Key (from ListPage): land back on TaskDetailsPage.
         *  6. Pressing Back Key Again (from TaskDetailsPage): navigate back to ListPage.
         *      The NavBackStackEntry still provides action = Action.ADD
         *  7. Now:
         *      * action (from nav args) = Action.ADD.
         *      * localAction (restored by rememberSaveable) is Action.ADD (because it was saved from the first arrival).
         *      * LaunchedEffect(key1 = localAction): localAction is currently Action.ADD.
         *  8. LaunchedEffect not triggered since key not change
         *  9. Result: The sharedViewModel.actionState is not updated again with Action.ADD
         *
         *  The new code introduces a local, persistent state (localAction via rememberSaveable).
         *  This local state acts as a flag to remember if the current action from navigation has
         *  already been seen and processed by this instance/lifecycle of the composable state.
         *
         *  Purpose of localAction: localAction tracks the last action that this specific composable
         *  instance decided to process and send to the ViewModel.
         *
         *
         */
        LaunchedEffect(key1 = localAction) {
            if(action != localAction){
                localAction = action
                sharedViewModel.updateAction(action)
            }
        }

        /**
         * observe the actionState and pass it to TodoListPage
         * Single Source of Truth (ViewModel): for UI-related state that needs to be observed and can be modified.
         *
         * action is local, may or may not trigger the actionState update in sharedViewModel,
         * observe the state from ViewModel make sure it's always reacting to the canonical state managed by the ViewModel.
         */
        val databaseAction: Action = sharedViewModel.actionState
        Log.i("databaseAction", databaseAction.toString())

        TodoListPage(navigateToTaskDetailPage, sharedViewModel, databaseAction)
    }
}