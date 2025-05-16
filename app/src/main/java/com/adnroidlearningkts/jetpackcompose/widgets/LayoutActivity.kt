package com.adnroidlearningkts.jetpackcompose.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adnroidlearningkts.jetpackcompose.ui.theme.AndroidLearningKTSTheme

class LayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidLearningKTSTheme {
                ColumnAlignments()
            }
        }
    }
}


@Composable
fun ColumnAlignments() {
    /**
     * Change Position of the texts, use 2 properties of Layouts
     *
     * If Layout is Column, use verticalArrangement and horizontalAlignment
     */
    Column(modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Hello", color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Blue)
//                .padding(20.dp)
                .padding(start = 20.dp, end = 20.dp)

        )

        /**
         * Spacer: Leave some distance between components
         *      - a structure used to keep distance between components
         */
        Spacer(modifier = Modifier.size(20.dp))

        Text(text = "Android", color = Color.Cyan, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Blue).width(100.dp))

        Spacer(modifier = Modifier.size(20.dp))

        Text(text = "Kotlin", color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Blue).width(100.dp))
    }
}

@Composable
fun RowAlignments() {
    /**
     * Change Position of the texts, use 2 properties of Layouts
     *
     * If Layout is Row, use horizontalArrangement and verticalAlignment
     */
    Row(modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Hello", color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Blue).width(100.dp))

        Text(text = "Android", color = Color.Cyan, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Blue).width(100.dp))

        Text(text = "Kotlin", color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Blue).width(100.dp))
    }
}

@Composable
fun BoxAlignments() {
    /**
     * Change Position of the texts, use 2 properties of Layouts
     *
     * If Layout is Box, use contentAlignment
     */
    Box(modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center) {
        Text(text = "Hello", color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Blue).width(100.dp))

        Text(text = "Android", color = Color.Cyan, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Blue).width(100.dp))

        Text(text = "Kotlin", color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Blue).width(100.dp))
    }
}

@Composable
fun FirstLayouts(name: String) {
    Column {
        Box(modifier = Modifier.background(Color.Red)
            .height(200.dp).fillMaxWidth()) {

            Row {
                Box(modifier = Modifier.size(50.dp).background(Color.Blue))
                Column {
                    Text(text = "Hello")
                    Text(text = "$name!")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LayoutPreview() {
    AndroidLearningKTSTheme {
//        Greeting("Android")
//        FirstLayouts("Android")
        ColumnAlignments()
//        RowAlignments()
//        BoxAlignments()
    }
}