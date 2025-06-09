package com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Constants.TODO_DB_TABLE_NAME

@Entity(tableName = TODO_DB_TABLE_NAME)
data class TodoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: TaskPriority
)
