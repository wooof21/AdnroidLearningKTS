package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adnroidlearningkts.R
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.theme.MediumGray


@Composable
fun ListEmptyComponent(modifier: Modifier = Modifier) {

    Column(modifier = modifier.fillMaxSize().background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Icon(painter = painterResource(R.drawable.compose_empty_icon),
            contentDescription = "Empty Content",
            modifier = Modifier.size(188.dp),
            tint = Color.Gray)

        Text("No Todo Tasks Found", fontSize = 26.sp, color = MediumGray,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
@Preview
fun ListEmptyComponentPreview() {
    ListEmptyComponent()
}