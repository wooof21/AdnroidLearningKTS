package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TaskPriority
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TodoTask
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.appbar.NewTaskAppBarComponent
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.appbar.TaskDetailAppBarComponent
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.task.TaskContentComponent
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Action
import com.adnroidlearningkts.jetpackcompose.apps.todolist.viewmodel.SharedViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Constants.MAX_TITLE_LENGTH


@Composable
fun TaskScreen(
    selectedTask: TodoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListPage: (Action) -> Unit) {

    //observe the value from SharedViewModel
    val title: String = sharedViewModel.title
    val description: String = sharedViewModel.description
    val priority: TaskPriority = sharedViewModel.priority

    val context = LocalContext.current

    //Intercept Back button: send NO_ACTION
    BackHandler {
        navigateToListPage(Action.NO_ACTION)
    }

    Scaffold(
        topBar = {
            TaskAppBarMainComponent(
                selectedTask = selectedTask,
                navigateToListPage = {action ->
                    when (action) {
                        //need to validate the fields
                        Action.ADD, Action.UPDATE -> {
                            if (sharedViewModel.validateTaskFields()) {
                                navigateToListPage(action)
                            } else {
                                showErrorToast(context = context)
                            }
                        }
                        // NO_ACTION, DELETE, DELETE_ALL - just navigate back to List Page
                        //and pass with the action
                        else -> {
                            navigateToListPage(action)
                        }
                    }

                })
        },
        content = {
            TaskContentComponent(
                title = title,
                onTitleChange = {newTitle ->
                    //passing the value to SharedViewModel.title
                    //limit the title length to max 20 chars
//                    if(newTitle.length <= MAX_TITLE_LENGTH) {
                        sharedViewModel.updateTitle(newTitle = newTitle)
//                    }
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModel.updateDescription(newDescription = it)
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.updatePriority(newPriority = it)
                },
                modifier = Modifier.padding(it))
        }
    )
}


@Composable
fun TaskAppBarMainComponent(
    selectedTask: TodoTask?,
    navigateToListPage: (Action) -> Unit) {

    if(selectedTask == null) {
        NewTaskAppBarComponent(navigateToListPage)
    } else {
        TaskDetailAppBarComponent(selectedTask, navigateToListPage)
    }
}

private fun showErrorToast(context: Context) {
    Toast.makeText(context, "Fields Validation Failed -" +
            " Must Not Be Empty And Satisfy The Condition", Toast.LENGTH_SHORT).show()
}