package com.adnroidlearningkts.jetpackcompose.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.adnroidlearningkts.R
import com.adnroidlearningkts.jetpackcompose.navigation.ui.theme.AdnroidLearningKTSTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavSecondPage(navController: NavController, name: String, age: Int) {

    val username = remember {
        mutableStateOf("")
    }
    val userAge = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Second Page", color = Color.White, fontSize = 18.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.purple),
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        /**
                         * When move from one page to another page, old page is not destroyed, it is
                         * put into a stack and when click the `back` button,
                         * current page is closed(destroyed and removed from the back stack)
                         *      - Compose runtime disposes of the composables and resources associated with current page
                         * and the top page in stack will be visible again
                         *
                         * popBackStack -> go back to previous page(top page in stack)
                         */
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Text("Name: $name", color = Color.Black, fontSize = 26.sp)

                Spacer(Modifier.height(18.dp))

                Text("Age: $age", color = Color.Black, fontSize = 26.sp)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NavSecondPagePreview() {
    AdnroidLearningKTSTheme {
        NavSecondPage(rememberNavController(), "", 10)
    }
}