package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.adnroidlearningkts.R


@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize()
        .background(colorResource(R.color.purple)),
        contentAlignment = Alignment.Center) {

        Image(
            painter = painterResource(R.drawable.todo_logo_light),
            contentDescription = "Logo"
        )
    }
}