package com.adnroidlearningkts.jetpackcompose.apps.todolist.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TodoTask

@Database(entities = [TodoTask::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {

    abstract fun getTodoDAO(): TodoTaskDAO
}