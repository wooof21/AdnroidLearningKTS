package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.TopBarState
import com.adnroidlearningkts.jetpackcompose.apps.todolist.viewmodel.SharedViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.appbar.SnackBarHostComponent
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.appbar.TodoListTopAppBar
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.list.ListComponent
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.list.LoadingSpinnerComponent
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Action


@Composable
fun TodoListPage(navigateToTaskDetailPage: (Int) -> Unit,
                 sharedViewModel: SharedViewModel,
                 databaseAction: Action) {

    //import androidx.compose.runtime.getValue
    val topBarState: TopBarState = sharedViewModel.topBarState
    val searchTextState: String = sharedViewModel.searchTextState

    /**
     * `LaunchedEffect` is a composable function designed to run suspend functions (like those used
     * with Kotlin Coroutines) in response to lifecycle events of another composable.
     *      - It takes one or more key parameters. The block of code inside LaunchedEffect (the lambda)
     *          will be executed when LaunchedEffect enters the Composition for the first time.
     *      - If any of the key values change between recompositions, the existing coroutine
     *          running the block will be cancelled, and a new coroutine will be launched to execute the block again.
     *      - key1 = true means the key is a constant value, Therefore, the block of code will
     *          only execute once when the composable containing this LaunchedEffect first enters the Composition.
     *          It will not re-execute on subsequent recompositions unless the
     *          LaunchedEffect itself is removed from and then re-added to the Composition
     *          (e.g., due to conditional logic like an if statement).
     * In summary:
     * When the composable function that contains this LaunchedEffect is first composed (i.e., displayed on the screen),
     * the sharedViewModel.getAllTasks() function will be called once.
     * This is a common pattern for performing initial data loading or other one-time setup operations
     * when a screen or a specific part of the UI becomes visible.
     * Because the key1 is a constant true, this effect will not be re-launched due to changes in other state within the composable.
     *
     * When to Use LaunchedEffect:
     *  1. Running Suspend Functions on Composition: Loading initial data for a screen, starting an animation that runs in a coroutine.
     *  2. Controlling Side Effects Based on Keys: when want a side effect (like a network request or a timer) to start,
     *      stop, or restart based on the values of one or more "keys." If a key changes, the current coroutine is cancelled,
     *      and a new one is launched with the new key value.
     *      - e.g.:
     *          * Fetching user details when userId (a key) changes.
     *          * Starting a countdown timer with a specific duration (the duration being a key).
     *          * Showing a Snackbar message when a messageId (a key) changes, and dismissing the previous one.
     *  3. One-Time Initialization: need to perform an action exactly once when the composable enters the composition.
     *  4. Collecting from Flows (when tied to specific keys):
     *      While `collectAsStateWithLifecycle` is often preferred for collecting Flows that drive UI state,
     *      LaunchedEffect can be used to collect a Flow and perform actions when values are emitted,
     *      especially if the collection needs to restart based on key changes.
     *      i.e. Collecting from a Flow that emits events and showing a Snackbar for each event, but only for a specific itemId.
     *
     * Why Use LaunchedEffect:
     *  1. Lifecycle Awareness:
     *      - LaunchedEffect is tied to the lifecycle of the composable it's called in.
     *      - When the composable enters the Composition, the coroutine inside LaunchedEffect is launched.
     *      - When the composable leaves the Composition (e.g., the user navigates away), the coroutine is automatically cancelled.
     *          This is crucial for preventing memory leaks and unnecessary work (like network requests for a screen that's no longer visible).
     *  2. Structured Concurrency:
     *      - provides a structured way to launch coroutines within the Composable hierarchy. The coroutine's scope is tied to the Composable's scope.
     *  3. Predictable Execution Based on Keys:
     *      - The key parameters give you fine-grained control over when the effect runs and restarts.
     *      - If a key changes, the previous coroutine is cancelled, and the effect is re-launched with the new key.
     *          This ensures that the side effect is always operating with the latest relevant data.
     *      - If all keys remain the same across recompositions, the effect is not re-launched.
     *  4. Safety with Suspend Functions:
     *      - Composables themselves should ideally be side-effect free and not call suspend functions
     *          directly because recomposition can happen at any time.
     *          LaunchedEffect provides a safe and designated place to call suspend functions from within the Composable UI.
     *  5. Avoiding Common Pitfalls:
     *      - Without LaunchedEffect or similar effect handlers, trying to launch coroutines directly from a composable
     *          (e.g., using GlobalScope or a ViewModel scope without proper cancellation) can lead to:
     *          * Leaked resources: Coroutines might continue running even after the UI is gone.
     *          * Multiple effects firing: An effect might run on every recomposition instead of just when needed.
     *          * Race conditions: If not handled correctly.
     *
     * key1 = userId -> Effect will run on first composition and re-launch if `userId` changes.
     * key1 = Unit or key1 = true -> only run once when first launch
     */

    /**
     * A small problem: when app start, this LaunchedEffect only triggered once,
     * when device configuration changes(screen rotate), it will also triggered again
     *
     * to avoid this: make the call inside SharedViewModel init block
     */
//    LaunchedEffect(key1 = true) {
//        sharedViewModel.getAllTasks()
//        sharedViewModel.readSortState()
//    }
    //Observe the data from DB
    //collectAsState(): collect value from `StateFlow` as `State`
    //Whenever State value updated, it will cause recomposition
    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val tasksByLowPriority by sharedViewModel.tasksByLowPriority.collectAsState()
    val tasksByHighPriority by sharedViewModel.tasksByHighPriority.collectAsState()

//    val currentAction: Action by sharedViewModel.actionState
//    sharedViewModel.handleActions(currentAction)

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    //handle the database action, whenever the databaseAction changes
    //to update changes in database
    LaunchedEffect(key1 = databaseAction) {
        sharedViewModel.handleActions(action = databaseAction)
    }

    Scaffold(
        topBar = { TodoListTopAppBar(sharedViewModel, topBarState, searchTextState) },
        floatingActionButton = {
            TodoListFABComponent(onFabClicked = navigateToTaskDetailPage)
        },
        content = {
            ListComponent(
                allTasks = allTasks,
                searchedTasks = searchedTasks,
                sortState = sortState,
                tasksByLowPriority = tasksByLowPriority,
                tasksByHighPriority = tasksByHighPriority,
                navigateToTaskDetailPage = navigateToTaskDetailPage,
                searchAppBarState = topBarState,
                onSwipeToDelete = { action, task ->
                    sharedViewModel.updateAction(action)
                    //set the task fields in SharedViewMode to the task about to be deleted
                    sharedViewModel.updateTask(task)
                    /**
                     * dismiss the Undo snack bar if there are any, in case when SwipeToDelete multiple
                     * items in a row, prevent the first Undo snack bar is still present
                     */
                    snackBarHostState.currentSnackbarData?.dismiss()
                },
                modifier = Modifier.padding(it))
        },
        snackbarHost = {
            SnackBarHostComponent(snackBarHostState = snackBarHostState,
                action = databaseAction,
                sharedViewModel = sharedViewModel)
        }
    )
}



@Composable
fun TodoListFABComponent(onFabClicked: (taskId: Int) -> Unit) {
    FloatingActionButton(onClick = {
        //-1 to indicate "Add New Task"
        onFabClicked(-1)
    }, containerColor = Color.Cyan) {
        Icon(imageVector = Icons.Filled.Add,
            contentDescription = "ADD",
            tint = Color.White)
    }
}

//@Composable
//@Preview
//private fun TodoListPagePreview() {
//    TodoListPage(navigateToTaskDetailPage = {})
//}