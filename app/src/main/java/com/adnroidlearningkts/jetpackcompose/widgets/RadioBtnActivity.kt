package com.adnroidlearningkts.jetpackcompose.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adnroidlearningkts.jetpackcompose.widgets.ui.theme.AdnroidLearningKTSTheme

class RadioBtnActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdnroidLearningKTSTheme {
                Scaffold(modifier = Modifier) { innerPadding ->
                    RadioBtnComponents(modifier = Modifier.padding(top = innerPadding.calculateTopPadding()))
                }
            }
        }
    }
}

@Composable
fun RadioBtnComponents(modifier: Modifier = Modifier) {

    val selectedColor = remember {
        mutableStateOf(Color.Red)
    }

    val radioBtnIndex = remember {
        mutableStateOf(0)
    }
    val btnColors = listOf(Color.Red, Color.Green, Color.Blue)
    val radioBtnTexts = listOf("Red", "Green", "Blue")

    Column(modifier.fillMaxSize().background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Change The Background Color", fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().background(selectedColor.value)
                .padding(18.dp)
        )

        //use for loop to dynamically create RadioButton
        //Column: to left align RadioButton when has different length
        Column {
            radioBtnTexts.forEachIndexed { index, s ->
                /**
                 * Use Row to group the RadioButton and Text
                 *
                 * verticalAlignment: to align the RadioButton and Text
                 */
                Row(verticalAlignment = Alignment.CenterVertically,
                    //make the entire Row clickable, both RadioButton and Text
                    //do the same as RadioButton onClick
                    modifier = Modifier.clickable {
                        radioBtnIndex.value = index
                        selectedColor.value = btnColors[index]
                    }) {
                    RadioButton(
                        selected = index == radioBtnIndex.value,
                        onClick = {
                            radioBtnIndex.value = index
                            selectedColor.value = btnColors[index]
                    },
                        colors = RadioButtonDefaults.colors(btnColors[index]))
                    Text(s)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    AdnroidLearningKTSTheme {
        RadioBtnComponents()
    }
}