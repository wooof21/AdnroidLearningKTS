package com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data

import androidx.compose.ui.graphics.Color
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.theme.HighPriorityColor
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.theme.LowPriorityColor
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.theme.MediumPriorityColor
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.theme.NonePriorityColor


enum class TaskPriority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}
