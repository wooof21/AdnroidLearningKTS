package com.adnroidlearningkts.jetpackcompose.apps.todolist.utils

/**
 * used to pass the ACTION from Task composable to List composable
 */
enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}
