package com.adnroidlearningkts.jetpackcompose.apps.todolist.navigation.destinations

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.adnroidlearningkts.jetpackcompose.apps.todolist.navigation.Screens
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.screens.TaskScreen
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Action
import com.adnroidlearningkts.jetpackcompose.apps.todolist.viewmodel.SharedViewModel
import androidx.compose.runtime.getValue


fun NavGraphBuilder.todoTaskComponent(
    navigateToListPage: (Action) -> Unit, sharedViewModel: SharedViewModel
) {

    composable<Screens.TodoTask>(
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(
                    durationMillis = 500
                )
            )
        }
    ) {navBackStackEntry ->
        val taskId = navBackStackEntry.toRoute<Screens.TodoTask>().id
        Log.i("taskId", taskId.toString())

        // 1. LaunchedEffect to fetch the task when taskId changes
        LaunchedEffect(key1 = taskId) {
            Log.i("LaunchedEffect", taskId.toString())
            sharedViewModel.getSelectedTaskById(taskId)
        }

        // 2. Collect the selectedTask state from the ViewModel
        // This will automatically recompose TaskScreen when selectedTask changes.
        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        /**
         * Whenever navigate to TaskDetail Composable, and the `taskId` changes,
         * update the UI field with new selectedTask, or empty with new task
         *
         * This wont work, as the selectedTask passed will always be the previous state, this is due to
         * asynchronous UI programming, the second LaunchedEffect(key1 = taskId) is called immediately
         * after the fetch is initiated, but likely before selectedTaskFromViewModel has received
         * the new task from the database.
         *
         * `selectedTask` might still hold the value from the previous taskId (if any) or be in
         * its initial state (e.g., null) because the database fetch for the current taskId hasn't
         * finished updating the StateFlow yet
         *
         * Option A: to let the composable (TaskScreen) directly rely on the selectedTask collected from the StateFlow.
         * Avoid having a separate "UI variable" in the ViewModel that you manually sync
         * unless absolutely necessary for complex transformations.
         */
//        LaunchedEffect(key1 = taskId) {
//            Log.i("LaunchedEffect?", taskId.toString())
//            sharedViewModel.updateTask(selectedTask = selectedTask)
//        }
        /**
         *  Option B: React to changes in the *collected* `selectedTask`
         *
         *  This ensures `updateTask` is called AFTER selectedTask (from the StateFlow) has its new value.
         *  Add a check to prevent running with initial/null values if not desired when the task
         *  is being loaded or if taskId is for a new task.
         *
         *  This effect runs whenever the selectedTask (the one collected from the StateFlow) changes.
         *  This ensures that `updateTask` is called after the StateFlow has been updated with the new task.
         */
        LaunchedEffect(key1 = selectedTask) {
            // Handle initial states or new task case (taskId == -1)
            if (selectedTask != null || taskId == -1) {
                sharedViewModel.updateTask(selectedTask = selectedTask)
            }
        }

        // 4. Pass the collected state to the screen
        // TaskScreen will recompose whenever 'selectedTask' changes.
        TaskScreen(selectedTask, sharedViewModel, navigateToListPage)
    }
}