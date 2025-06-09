package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.appbar

import androidx.compose.runtime.Composable
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Action
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.TopBarState
import com.adnroidlearningkts.jetpackcompose.apps.todolist.viewmodel.SharedViewModel


@Composable
fun TodoListTopAppBar(sharedViewModel: SharedViewModel,
                      topBarState: TopBarState,
                      searchTextState: String) {

    //recompose TodoListTopAppBar by topBarState
    when(topBarState) {
        TopBarState.CLOSED -> {
            DefaultTopBar(
                onSearchClicked = { sharedViewModel.updateTopAppBarState(newState = TopBarState.OPENED)  },
                onSortClicked = {priority ->
                    sharedViewModel.persistSortState(priority)
                },
                onDeleteAllConfirmed = {
                    sharedViewModel.updateAction(Action.DELETE_ALL)
                })
        }
        else -> {
            SearchTopBar(searchText = searchTextState,
                onTextChanged = { newText ->
                    sharedViewModel.updateSearchTextState(newState = newText)
                },
                onSearchClicked = {
                    sharedViewModel.searchTasks(query = it)
                },
                onCloseClicked = {
                    when(sharedViewModel.searchTextState.isEmpty()) {
                        true -> {
                            //when empty, close the SearchTopBar
                            sharedViewModel.updateTopAppBarState(newState = TopBarState.CLOSED)
                        }
                        else -> {
                            //else, clear the text
                            sharedViewModel.updateSearchTextState(newState = "")
                        }
                    }
                })
        }
    }



}


