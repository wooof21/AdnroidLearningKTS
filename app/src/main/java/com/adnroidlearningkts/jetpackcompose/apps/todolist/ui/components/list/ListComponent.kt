package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.list

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TaskPriority
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TodoTask
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Action
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.RequestState
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.TopBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ListComponent(
    allTasks: RequestState<List<TodoTask>>,
    searchedTasks: RequestState<List<TodoTask>>,
    sortState: RequestState<TaskPriority>,
    tasksByLowPriority: List<TodoTask>,
    tasksByHighPriority: List<TodoTask>,
    navigateToTaskDetailPage: (taskId: Int) -> Unit,
    searchAppBarState: TopBarState,
    onSwipeToDelete: (Action, TodoTask) -> Unit,
    modifier: Modifier = Modifier) {

    //use RequestState to prevent when loading the data is in progress and not complete yet,
    //the ListEmptyComponent will be showing for a quick moment
    //only when loading is complete, and the data is empty, show the ListEmptyComponent
    if(sortState is RequestState.Success) {
        when {
            searchAppBarState == TopBarState.TRIGGERED -> {
                //When the TopBarState is TRIGGERED, send the searchedTasks to ListContent
                if(searchedTasks is RequestState.Success) {
                    HandleListContent(tasks = searchedTasks.data,
                        navigateToTaskDetailPage = navigateToTaskDetailPage,
                        onSwipeToDelete = onSwipeToDelete,
                        modifier = modifier)
                }
            }
            //not searching or sorting, use allTasks to display the List
            sortState.data == TaskPriority.NONE -> {
                if(allTasks is RequestState.Success) {
                    HandleListContent(tasks = allTasks.data,
                        navigateToTaskDetailPage = navigateToTaskDetailPage,
                        onSwipeToDelete = onSwipeToDelete,
                        modifier = modifier)
                }
            }
            sortState.data == TaskPriority.LOW -> {
                HandleListContent(tasks = tasksByLowPriority,
                    navigateToTaskDetailPage = navigateToTaskDetailPage,
                    onSwipeToDelete = onSwipeToDelete,
                    modifier = modifier)
            }
            sortState.data == TaskPriority.HIGH -> {
                HandleListContent(tasks = tasksByHighPriority,
                    navigateToTaskDetailPage = navigateToTaskDetailPage,
                    onSwipeToDelete = onSwipeToDelete,
                    modifier = modifier)
            }
        }
    }



}

@Composable
fun HandleListContent(
    tasks: List<TodoTask>,
    onSwipeToDelete: (Action, TodoTask) -> Unit,
    navigateToTaskDetailPage: (taskId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if(tasks.isEmpty()) {
        ListEmptyComponent(modifier)
    } else {
        ListContent(
            tasks = tasks,
            onSwipeToDelete = onSwipeToDelete,
            navigateToTaskDetailPage = navigateToTaskDetailPage,
            modifier = modifier)
    }
}

@Composable
fun ListContent(tasks: List<TodoTask>,
                onSwipeToDelete: (Action, TodoTask) -> Unit,
                navigateToTaskDetailPage: (taskId: Int) -> Unit,
                modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(
            items = tasks,
            key = {task ->
                task.id
            },
            itemContent = {todoTask ->
                val dismissState = rememberSwipeToDismissBoxState()
                val dismissDirection = dismissState.dismissDirection
                //when release, either snap back to End or Start, will make the progress to 1f
                //and trigger the Delete Action -> why check dismissState.progress != 1f
                //dismissState.progress >= 0.6f -> when swipe to 60%, then trigger Delete Action
                val isDismissed = dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart
                        && dismissState.progress != 1f && dismissState.progress >= 0.6f
                val scope = rememberCoroutineScope()
                if (isDismissed && dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                    SideEffect {
                        scope.launch {
                            delay(300)
                            onSwipeToDelete(Action.DELETE, todoTask)
                        }
                    }
                } else {
                    SideEffect {
                        scope.launch {
                            dismissState.reset()
                        }
                    }
                }

                val degrees by animateFloatAsState(
                    targetValue = if (dismissState.progress in 0f..0.5f) 0f else -45f,
                    label = "Degree animation"
                )

                SwipeToDismissBox(
                    state = dismissState,
                    enableDismissFromStartToEnd = false,
                    enableDismissFromEndToStart = true,
                    backgroundContent = {
                        SwipeToDeleteRedBg(degrees = degrees)
                    }
                ) {
                    TaskListItemComponent(task = todoTask, navigateToTaskDetailPage = navigateToTaskDetailPage)
                }
            }
        )
    }
}

@Composable
fun SwipeToDeleteRedBg(degrees: Float) {
    //wrap SwipeToDelete background in the Card with same padding value in TaskListItemComponent
    Card(modifier = Modifier
        .padding(start = 18.dp, end = 18.dp, top = 18.dp)
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Red
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.CenterEnd) {
            Icon(
                modifier = Modifier.rotate(degrees = degrees).padding(end = 26.dp),
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = Color.White)
        }
    }
}

@Composable
@Preview
fun SwipeToDeleteRedBgPreview() {
    SwipeToDeleteRedBg(60f)
}

