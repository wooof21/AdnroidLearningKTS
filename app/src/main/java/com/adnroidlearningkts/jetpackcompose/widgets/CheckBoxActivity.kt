package com.adnroidlearningkts.jetpackcompose.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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

class CheckBoxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdnroidLearningKTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CheckBoxComponent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CheckBoxComponent(modifier: Modifier = Modifier) {

//    val gender = remember {
//        mutableStateOf("Gender?")
//    }

    val checkStateFirst = remember {
        mutableStateOf(false)
    }
    val checkStateSecond = remember {
        mutableStateOf(false)
    }

    // use a fun to determine the text displayed in gender Text
    val gender = getSelectedGenderText(checkStateFirst.value, checkStateSecond.value)

    /**
     * 0x -> hexadecimal number
     * FF -> opacity of the color
     * 027CDD -> RGB color code
     */
    Column(modifier.fillMaxSize().background(Color(0xFF027CDD)),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier.size(80.dp))

        Text(text = gender, fontSize = 26.sp, color = Color.White,
            modifier = modifier.fillMaxWidth()
                    .background(Color.Cyan)
                    //to add inner padding, apply padding after other properties
                    .padding(8.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier.size(80.dp))

        //place 2 Row in a Column to left align the CheckBox when they are not in same length
        Column {
            /**
             * checked:
             *      false -> appear unchecked at beginning
             *      true -> appear checked at beginning
             * onCheckedChange: return true or false when click the checkbox
             *
             * verticalAlignment; align the CheckBox and Text
             *
             * Checkbox has no text property, use Text() combined in a Row layout
             */
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = checkStateFirst.value, onCheckedChange = {
                    checkStateFirst.value = it
                    checkStateSecond.value = false
                },
                    colors = CheckboxDefaults.colors(
                        Color(0xFFE91E63)
                    ))
                Text("Male", color = Color.White, fontSize = 20.sp)
            }

            Spacer(modifier.size(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = checkStateSecond.value, onCheckedChange = {
                    checkStateSecond.value = it
                    checkStateFirst.value = false
                })
                Text("Female", color = Color.White, fontSize = 20.sp)
            }
        }
    }
}

// Helper function to determine the selected gender text
private fun getSelectedGenderText(isMaleChecked: Boolean, isFemaleChecked: Boolean): String {
    return when {
        isMaleChecked -> "Male"
        isFemaleChecked -> "Female"
        else -> "Gender?"
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    AdnroidLearningKTSTheme {
        CheckBoxComponent()
    }
}