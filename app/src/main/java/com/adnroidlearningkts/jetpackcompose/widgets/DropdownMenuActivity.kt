package com.adnroidlearningkts.jetpackcompose.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.adnroidlearningkts.R
import com.adnroidlearningkts.jetpackcompose.widgets.ui.theme.AdnroidLearningKTSTheme

class DropdownMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdnroidLearningKTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DropdownMenuComponent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DropdownMenuComponent(modifier: Modifier = Modifier) {

    val selectedItem = remember {
        mutableStateOf("")
    }
    val dropdownState = remember {
        mutableStateOf(false)
    }
    val countries = listOf("China", "USA", "Russia", "UK", "Canada")

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        /**
         * To have the Dropdown Menu opened under the Text&Image component,
         * use Box
         */
        Box {
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                //to open the Dropdown Menu when user clicked the Row
                modifier = Modifier.clickable {
                    dropdownState.value = true
                }) {
                Text(text = selectedItem.value)
                Image(painter = painterResource(R.drawable.compose_dropdown_icon), contentDescription = "")
            }

            /**
             * expanded:
             *      true -> Dropdown Menu will be open
             *      false -> Dropdown Menu will be close
             *
             * onDismissRequest: handle user dismiss event to close the Dropdown Menu
             *
             * When the Menu Items are too many to fit the screen, a scroll bar will be
             * automatically added
             */
            DropdownMenu(expanded = dropdownState.value, onDismissRequest = {
                dropdownState.value = false
            }) {
                countries.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        text = {Text(s)},
                        onClick = {
                            selectedItem.value = countries[index]
                            dropdownState.value = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    AdnroidLearningKTSTheme {
        DropdownMenuComponent()
    }
}