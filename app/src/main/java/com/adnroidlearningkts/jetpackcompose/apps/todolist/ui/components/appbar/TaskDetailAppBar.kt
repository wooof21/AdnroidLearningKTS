package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.adnroidlearningkts.R
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TaskPriority
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TodoTask
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.dialog.ShowAlertDialog
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Action


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailAppBarComponent(
    selectedTask: TodoTask,
    navigateToListPage: (Action) -> Unit) {

    TopAppBar(
        navigationIcon = {
            CloseActionComponent(onCloseClicked = navigateToListPage)
        },
        title = {
            Text(text = selectedTask.title,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
        },
        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primary
            containerColor = colorResource(R.color.purple)
        ),
        actions = {
            TaskAppBarActions(selectedTask, navigateToListPage)
        }
    )
}

@Composable
fun TaskAppBarActions(
    selectedTask: TodoTask,
    navigateToListPage: (Action) -> Unit) {

    var openDialog by remember { mutableStateOf(false) }

    ShowAlertDialog(
        title = "Remove ${selectedTask.title}?",
        message = "Are you sure to remove the task: ${selectedTask.title}?",
        openDialog,
        closeDialog = { openDialog = false},
        onConfirmClicked = {
            navigateToListPage(Action.DELETE)
        }
    )

    DeleteActionComponent(onDeleteClicked = {
        openDialog = true
    })
    UpdateActionComponent(onUpdateClicked = navigateToListPage)
}

@Composable
fun CloseActionComponent(onCloseClicked: (Action) -> Unit) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(imageVector = Icons.Filled.Close,
            contentDescription = "Close",
            tint = Color.White)
    }
}

@Composable
fun DeleteActionComponent(
    onDeleteClicked: () -> Unit) {
    IconButton(onClick = { onDeleteClicked() }) {
        Icon(imageVector = Icons.Filled.Delete,
            contentDescription = "Delete",
            tint = Color.White)
    }
}

@Composable
fun UpdateActionComponent(onUpdateClicked: (Action) -> Unit) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(imageVector = Icons.Filled.CheckCircle,
            contentDescription = "Update",
            tint = Color.White)
    }
}

@Composable
@Preview
fun TaskDetailAppBarPreview() {
    TaskDetailAppBarComponent(
        TodoTask(0, "Some Long ToDo Task Title", "Description", TaskPriority.MEDIUM),
        {}
    )
}