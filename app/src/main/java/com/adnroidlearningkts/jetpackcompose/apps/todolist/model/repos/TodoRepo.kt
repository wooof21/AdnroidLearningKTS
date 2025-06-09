package com.adnroidlearningkts.jetpackcompose.apps.todolist.model.repos

import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TodoTask
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.room.TodoTaskDAO
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//to scope the instance of this repo class to the lifecycle of a view model
@ViewModelScoped
class TodoRepo @Inject constructor(private val dao: TodoTaskDAO) {

    val getAllTasks: Flow<List<TodoTask>> = dao.getAllTasks()
    val sortByLowPriority: Flow<List<TodoTask>> = dao.sortByLowPriority()
    val sortByHighPriority: Flow<List<TodoTask>> = dao.sortByHighPriority()

    fun getTaskById(id: Int) = dao.getTaskById(id)

    suspend fun insertTask(task: TodoTask) = dao.insertTask(task)

    suspend fun updateTask(task: TodoTask) = dao.updateTask(task)

    suspend fun deleteTask(task: TodoTask) = dao.deleteTask(task)

    suspend fun deleteAllTasks() = dao.deleteAllTasks()

    fun searchTask(query: String) = dao.searchTask(query)
}