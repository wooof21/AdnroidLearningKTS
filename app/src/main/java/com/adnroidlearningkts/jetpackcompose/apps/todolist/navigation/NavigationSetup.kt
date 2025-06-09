package com.adnroidlearningkts.jetpackcompose.apps.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.adnroidlearningkts.jetpackcompose.apps.todolist.navigation.destinations.todoListComponent
import com.adnroidlearningkts.jetpackcompose.apps.todolist.navigation.destinations.todoTaskComponent
import com.adnroidlearningkts.jetpackcompose.apps.todolist.viewmodel.SharedViewModel


@Composable
fun NavigationSetup(navController: NavHostController, sharedViewModel: SharedViewModel) {

    NavHost(navController = navController, startDestination = Screens.TodoList()) {
        todoListComponent(navigateToTaskDetailPage = {taskId ->
            navController.navigate(Screens.TodoTask(taskId))
        }, sharedViewModel)
        todoTaskComponent(navigateToListPage = {action ->
            navController.navigate(Screens.TodoList(action = action)) {
                popUpTo(Screens.TodoList()) { inclusive = true }
            }
        }, sharedViewModel)
    }
}