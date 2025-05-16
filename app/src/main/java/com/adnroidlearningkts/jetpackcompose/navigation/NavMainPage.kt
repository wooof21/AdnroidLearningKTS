package com.adnroidlearningkts.jetpackcompose.navigation

import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun NavMainPage(navController: NavController) {

    val username = remember {
        mutableStateOf("")
    }
    val userAge = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Main Page", color = Color.White, fontSize = 18.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.purple)
                )
            )
        },
        content = {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                TextField(value = username.value,
                    onValueChange = {
                        username.value = it
                    },
                    label = { Text(text = "Enter Your Name") },
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = colorResource(R.color.purple),
                        unfocusedContainerColor = colorResource(R.color.purple)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 88.dp),
                    textStyle = TextStyle(fontSize = 18.sp, color = Color.White)
                )

                Spacer(Modifier.height(18.dp))

                TextField(value = userAge.value,
                    onValueChange = {
                        userAge.value = it
                    },
                    label = { Text(text = "Enter Your Age") },
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = colorResource(R.color.purple),
                        unfocusedContainerColor = colorResource(R.color.purple)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 88.dp),
                    textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(Modifier.height(26.dp))

                Button(
                    onClick = {
                        /**
                         * use the label defined to navigate to NavSecondPage
                         *
                         * To prevent the current page(MainPage) being added to back stack
                         *      - use popUpTo() and give the `label` of the page that want to prevent
                         *      - it pops everything up to `MainPage` off the back stack before navigating
                         *          to SecondPage
                         *      - if want the MainPage also removed(destroyed) from back stack
                         *              * use `inclusive`
                         *
                         * When want to pass data from one page to another,
                         *  1. need to add the values
                         *      to the route by placing a "/" after the label for destination page
                         *      and put the value after "/"
                         *  2. need to specify the variable name on SecondPage it will receive in the
                         *      main composable fun(NavigationActivity - Navigation) which manage the navigation
                         *
                         * "SecondPage/${username.value}/${userAge.value}": all params are required, app crash if not provided
                         *
                         * to make all params optional: give the default value when params are null/empty or construct the route string
                         */
//                        navController.navigate("SecondPage?name=${username.value}&age=${userAge.value}") {
////                            popUpTo("MainPage") { inclusive = true }
//
//                        }

                        //or dynamically construct the route string
                        val queryParams = buildList {
                            if (username.value.isNotBlank()) {
                                add("name=${username.value}")
                            }
                            if (userAge.value.isNotBlank()) {
                                // might want to add validation to ensure userAge is a number
                                add("age=${userAge.value}")
                            }
                        }.joinToString(separator = "&", prefix = if (username.value.isNotBlank() || userAge.value.isNotBlank()) "?" else "")
                        val route = "SecondPage$queryParams"
                        Log.i("TAG", route)
                        navController.navigate(route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = colorResource(R.color.purple)
                    ),
                    border = BorderStroke(1.dp, colorResource(R.color.purple)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Send",
                        fontSize = 26.sp,
                        modifier = Modifier.padding(horizontal = 26.dp))
                }
            }
        }
    )


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AdnroidLearningKTSTheme {
        NavMainPage(rememberNavController())
    }
}