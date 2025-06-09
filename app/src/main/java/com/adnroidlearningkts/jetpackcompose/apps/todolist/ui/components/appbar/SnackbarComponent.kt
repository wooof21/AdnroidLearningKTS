package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.appbar

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Action
import com.adnroidlearningkts.jetpackcompose.apps.todolist.viewmodel.SharedViewModel


@Composable
fun SnackBarHostComponent(snackBarHostState: SnackbarHostState, action: Action,
                          sharedViewModel: SharedViewModel) {

    SnackbarHost(
        hostState = snackBarHostState,
        //snackbar customization
        snackbar = {
            Snackbar(snackbarData = it,
                containerColor = Color.DarkGray,
                contentColor = Color.White,
                actionColor = Color.Cyan,
                dismissActionContentColor = Color.Black)
        }
    )

    ShowSnackBar(snackBarHostState = snackBarHostState,
        onComplete = { sharedViewModel.updateAction(it) },
        onUndoClicked = { sharedViewModel.updateAction(it) },
        taskTitle = sharedViewModel.title,
        action = action)

}

@Composable
fun ShowSnackBar(
    snackBarHostState: SnackbarHostState,
    onComplete: (Action) -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {

    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = setMessage(action = action, taskTitle = taskTitle),
                actionLabel = setActionLabel(action = action),
                duration = SnackbarDuration.Short
            )
            if (snackBarResult == SnackbarResult.ActionPerformed
                && action == Action.DELETE) {
                onUndoClicked(Action.UNDO)
            } else if (snackBarResult == SnackbarResult.Dismissed || action != Action.DELETE) {
                onComplete(Action.NO_ACTION)
            }
        }
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All Tasks Removed."
        else -> "${action.name}: $taskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if (action == Action.DELETE) {
        "UNDO"
    } else {
        "OK"
    }
}

