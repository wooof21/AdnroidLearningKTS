package com.adnroidlearningkts.jetpackcompose.apps.todolist.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TaskPriority
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TodoTask
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.repos.DataStoreRepo
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.repos.TodoRepo
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Action
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Constants.MAX_TITLE_LENGTH
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.RequestState
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.TopBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val todoRepo: TodoRepo,
    private val dataStoreRepo: DataStoreRepo
    ): ViewModel() {

    var topBarState by mutableStateOf(TopBarState.CLOSED)
        private set
    //set the text in the TextFiled of Search Bar
    var searchTextState by mutableStateOf("")
        private set

    var id by mutableIntStateOf(0)
        private set
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set
    var priority by mutableStateOf(TaskPriority.LOW)
        private set

    //private setter: can only be modified within current class
    var actionState by mutableStateOf(Action.NO_ACTION)
        private set

    private val _allTask =
                MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<TodoTask>>> = _allTask


    private val _sortState =
        MutableStateFlow<RequestState<TaskPriority>>(RequestState.Idle)
    val sortState: StateFlow<RequestState<TaskPriority>> = _sortState

    private val _selectedTask: MutableStateFlow<TodoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<TodoTask?> = _selectedTask

    private val _searchedTasks =
        MutableStateFlow<RequestState<List<TodoTask>>>(RequestState.Idle)
    val searchedTasks: StateFlow<RequestState<List<TodoTask>>> = _searchedTasks

    init {
        getAllTasks()
        readSortState()
    }

    fun updateAction(newAction: Action) {
        Log.i("AndroidLearningKts", "updateAction: ${newAction.toString()}")
        actionState = newAction
    }

    fun updateDescription(newDescription: String) {
        description = newDescription
    }

    fun updateTitle(newTitle: String) {
        title = newTitle
    }

    fun updatePriority(newPriority: TaskPriority) {
        priority = newPriority
    }

    fun updateTopAppBarState(newState: TopBarState) {
        topBarState = newState
    }

    fun updateSearchTextState(newState: String) {
        searchTextState = newState
    }

    /********************************************************/

    private fun getAllTasks() {
        _allTask.value = RequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                todoRepo.getAllTasks.collect {
                    _allTask.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTask.value = RequestState.Error(e)
        }
    }


    /********************************************************/

    /********************************************************/

    fun getSelectedTaskById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepo.getTaskById(id).collect {
                _selectedTask.value = it
            }
        }
    }
    /********************************************************/

    /********************************************************/

    //call the fun in TaskDetailDestination
    fun updateTask(selectedTask: TodoTask?) {
        //FAB clicked: new task -> set all fields to default value
        id = selectedTask?.id ?: 0
        title = selectedTask?.title ?: ""
        description = selectedTask?.description ?: ""
        priority = selectedTask?.priority ?: TaskPriority.LOW
    }
    /********************************************************/

    fun validateTaskFields(): Boolean {
        return title.isNotEmpty() && title.length <= MAX_TITLE_LENGTH
                && description.isNotEmpty()
    }



    /********************************************************/

    fun handleActions(action: Action) {
        when(action) {
            Action.ADD -> {
                addTask()
                updateAction(Action.NO_ACTION)
            }
            Action.UPDATE -> updateTask()
            Action.DELETE -> deleteTask()
            Action.DELETE_ALL -> deleteAllTasks()
            Action.UNDO -> {
                undoDeleteTask()
                updateAction(Action.NO_ACTION)
            }
            else -> {}
        }
        //reset current Action state - reset done in SnackBar now
//        actionState.value = Action.NO_ACTION
    }


    private fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            //create TodoTask from current observable
            val task = TodoTask(
                title = title,
                description =  description,
                priority = priority
            )
            todoRepo.insertTask(task)

            //When in searching state, after adding a new Task, List wont show the added Task since in
            //Triggered state, reset the app bar state to CLOSED to show the all tasks
            topBarState = TopBarState.CLOSED
            /**
             * Since the getAllTasks() defined in DAO is returning Flow<List<TodoTask>>,
             * Room can automatically push updates to this Flow whenever the underlying table changes.
             * The ViewModel would collect this Flow and update its `_allTasks` `StateFlow`.
             *
             * No need to manually refresh _allTasks, Room will trigger an update to the Flow
             */
        }
    }

    private fun updateTask() = viewModelScope.launch(Dispatchers.IO) {
        val task = TodoTask(
            //use the id to reference the same TodoTask object
            id = id,
            title = title,
            description =  description,
            priority = priority
        )
        todoRepo.updateTask(task)
    }

    /**
     * Race Condition:
     *
     * After adding a new Task then immediately click the new added Task for deleting, race condition may occur
     * when the add operation is not done yet, delete operation trying to delete the Task may not be
     * fully committed or its ID might not be the final one if generated by the DB
     *
     * After add a new Task, The UI might update to show the new task before the database insertion
     * is fully confirmed and the main list data source (allTasks StateFlow)
     * has been refreshed from the database post-insertion.
     *
     * The Race:
     *
     * Scenario A (Delete "wins" but on stale data): If the delete operation starts before the
     * add operation has completed and the allTasks list in the ViewModel has been updated with
     * the actual task from the database (with its final ID), the delete operation might be trying to delete:
     *      * A task with a temporary ID that doesn't match the final database ID.
     *      * A task that doesn't "exist" yet from the perspective of the data source that the delete operation is querying.
     *
     * Scenario B (Operations Interleave Incorrectly): The add operation might finish inserting.
     * Then, the delete operation starts. However, the UI list (allTasks StateFlow) might not have
     * refreshed yet to include the newly added task before the delete operation attempts to act on the (now stale) list data.
     *
     *
     */
    private fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val task = TodoTask(
                //use the id to reference the same TodoTask object
                id = id,
                title = title,
                description =  description,
                priority = priority
            )
            todoRepo.deleteTask(task)
        }
    }

    private fun deleteAllTasks() = viewModelScope.launch(Dispatchers.IO) {
        todoRepo.deleteAllTasks()
    }

    private fun undoDeleteTask() = viewModelScope.launch(Dispatchers.IO) {
        val deletedTask = TodoTask(
            title = title,
            description =  description,
            priority = priority
        )
        todoRepo.insertTask(deletedTask)
    }
    /********************************************************/

    fun searchTasks(query: String) {
        _searchedTasks.value = RequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                //%$query% -> search the query string in any position
                todoRepo.searchTask(query = "%$query%").collect { searchedTasks ->
                    _searchedTasks.value = RequestState.Success(searchedTasks)
                }
            }
        } catch (e: Exception) {
            _searchedTasks.value = RequestState.Error(e)
        }
        topBarState = TopBarState.TRIGGERED
    }

    /**************************DataStore******************************/


    val tasksByLowPriority: StateFlow<List<TodoTask>> =
        todoRepo.sortByLowPriority.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val tasksByHighPriority: StateFlow<List<TodoTask>> =
        todoRepo.sortByHighPriority.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun persistSortState(priority: TaskPriority) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepo.persistSortState(priority)
    }

    private fun readSortState() {
        _sortState.value = RequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreRepo.readSortState
                    .map {
                        //convert String value of TaskPriority into TaskPriority enum
                        TaskPriority.valueOf(it)
                    }
                    .collect {
                        _sortState.value = RequestState.Success(it)
                    }
            }
        } catch (e: Exception) {
            _sortState.value = RequestState.Error(e)
        }
    }

}