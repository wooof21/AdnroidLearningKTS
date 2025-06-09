package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.appbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adnroidlearningkts.R
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TaskPriority
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.PriorityItemComponent
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.dialog.ShowAlertDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(onSearchClicked: () -> Unit,
                  onSortClicked: (TaskPriority) -> Unit,
                  onDeleteAllConfirmed: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Tasks", color = Color.White)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.purple)
        ),
        actions = {
            DefaultTopBarActions(onSearchClicked, onSortClicked, onDeleteAllConfirmed)
        }
    )
}

@Composable
fun DefaultTopBarActions(onSearchClicked: () -> Unit,
                         onSortClicked: (TaskPriority) -> Unit,
                         onDeleteAllConfirmed: () -> Unit) {
    DefaultSearchAction(onSearchClicked)
    DefaultSortAction(onSortClicked)

    var openDialog by remember { mutableStateOf(false) }

    ShowAlertDialog(
        title = "Remove All Tasks?",
        message = "Are you sure to remove all tasks? There is no Undo option.",
        openDialog,
        closeDialog = { openDialog = false},
        onConfirmClicked = {
            onDeleteAllConfirmed()
        }
    )

    DefaultDeleteAllAction(onDeleteAllConfirmed = { openDialog = true })
}

@Composable
fun DefaultSearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = onSearchClicked) {
        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search",
            tint = Color.White)
    }
}

@Composable
fun DefaultSortAction(onSortClicked: (TaskPriority) -> Unit) {

    //`by` keyword: automatically take the State.value
    var dropdownState by remember { mutableStateOf(false) }

    IconButton(onClick = { dropdownState = true }) {
        Icon(painter = painterResource(R.drawable.compose_sort_icon),
            contentDescription = "Sort",
            tint = Color.White)

        DropdownMenu(expanded = dropdownState, onDismissRequest = {
            dropdownState = false
        }) {
            //take only the indexes 0, 2, 3 which are Low, HIGH & NONE, since only sort the list
            //based on these 3 priorities
            TaskPriority.entries.slice(setOf(0, 2, 3)).forEach {
                    priority ->
                DropdownMenuItem(
                    onClick = {
                        dropdownState = false
                        onSortClicked(priority)
                    },
                    text = {
                        PriorityItemComponent(priority = priority)
                    }
                )
            }
        }
    }
}

@Composable
fun DefaultDeleteAllAction(onDeleteAllConfirmed: () -> Unit) {

    var dropdownState by remember { mutableStateOf(false) }

    IconButton(onClick = { dropdownState = true }) {
        Icon(imageVector = Icons.Filled.MoreVert,
            contentDescription = "Delete All",
            tint = Color.White)

        DropdownMenu(expanded = dropdownState, onDismissRequest = {
            dropdownState = false
        }) {

            DropdownMenuItem(
                onClick = {
                    dropdownState = false
                    onDeleteAllConfirmed()
                },
                text = {
                    Text(text = "Delete All Tasks",
                        modifier = Modifier.padding(start = 18.dp),
                        style = MaterialTheme.typography.bodyMedium)
                }
            )
        }
    }
}

@Composable
@Preview
fun DefaultTopBarPreview() {
    DefaultTopBar(onSearchClicked = {}, onSortClicked = {}, onDeleteAllConfirmed = {})
}