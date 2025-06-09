package com.adnroidlearningkts.jetpackcompose.apps.todolist.navigation

import androidx.navigation.NavHostController
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Action
import kotlinx.serialization.Serializable


//type safe navigation
@Serializable
sealed class Screens {
    @Serializable
    data class TodoList(val action: Action = Action.NO_ACTION): Screens()
    @Serializable
    data class TodoTask(val id: Int): Screens()
}