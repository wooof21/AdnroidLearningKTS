package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.adnroidlearningkts.R
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Action


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBarComponent(navigateToListPage: (Action) -> Unit) {

    TopAppBar(
        navigationIcon = {
            BackArrowComponent(onBackClicked = navigateToListPage)
        },
        title = {
            Text(text = "Add Task", color = Color.White)
        },
        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primary
            containerColor = colorResource(R.color.purple)
        ),
        actions = {
            DoneActionComponent(onDoneClicked = navigateToListPage)
        }
    )
}

//pass NO_ACTION back to List screen
@Composable
fun BackArrowComponent(onBackClicked: (Action) -> Unit) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White)
    }
}

@Composable
fun DoneActionComponent(onDoneClicked: (Action) -> Unit) {
    IconButton(onClick = { onDoneClicked(Action.ADD) }) {
        Icon(imageVector = Icons.Filled.Done,
            contentDescription = "Add",
            tint = Color.White)
    }
}

@Composable
@Preview
fun NewTaskBarPreview() {
    NewTaskAppBarComponent({})
}

