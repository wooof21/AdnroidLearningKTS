package com.adnroidlearningkts.jetpackcompose.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adnroidlearningkts.jetpackcompose.ui.theme.AndroidLearningKTSTheme

class TextFieldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidLearningKTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextFields(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * value: show the input text on TextField
 * onValueChange: used to determine what to do with the entered data when user start input
 *
 * label -> hint
 * fontSize -> no direct fontSize property, use `textStyle`
 */
@Composable
fun TextFields(modifier: Modifier = Modifier) {
    val textField = remember {
        mutableStateOf("")
    }
    val userInput = remember {
        mutableStateOf("Input:")
    }
    Column {
        TextField(
            value = textField.value, onValueChange = {
                textField.value = it
            },
            label = { Text("Name") },
            modifier = Modifier.width(300.dp).padding(top = 20.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray,
                focusedContainerColor = Color.Blue,
                unfocusedContainerColor = Color.Cyan,
                focusedLabelColor = Color.Yellow,
                unfocusedLabelColor = Color.White,
                unfocusedIndicatorColor = Color.Red,
                focusedIndicatorColor = Color.Green
            ),
            textStyle = TextStyle.Default.copy(fontSize = 18.sp),
            maxLines = 5,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            //prevent entered data from appearing
//            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.size(20.dp))

        Text(text = userInput.value, color = Color.White,
            fontSize = 18.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Red).padding(8.dp))

        Spacer(modifier = Modifier.size(20.dp))

        //click btn to get input and display in the Text
        Button(onClick = {
            userInput.value = textField.value
            //clear the text field
            textField.value = ""
        }) {
            Text("Get Input")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidLearningKTSTheme {
        TextFields()
    }
}