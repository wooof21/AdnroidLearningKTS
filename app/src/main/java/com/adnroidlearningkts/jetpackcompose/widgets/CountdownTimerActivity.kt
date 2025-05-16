package com.adnroidlearningkts.jetpackcompose.widgets

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adnroidlearningkts.jetpackcompose.widgets.ui.theme.AdnroidLearningKTSTheme
import java.util.Locale

class CountdownTimerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdnroidLearningKTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CountdownTimerComponent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CountdownTimerComponent(modifier: Modifier = Modifier) {
    val remainingTimeText = remember {
        mutableStateOf("30")
    }
    val totalTimeInMills = remember {
        mutableLongStateOf(30000L)
    }

    val timer = remember {
        mutableStateOf(
            /**
             * CountDownTimer -> abstract class, use `object` keyword
             * 2 params: total time in mills and count down interval - 1 s
             */
            object : CountDownTimer(totalTimeInMills.longValue,
                1000) {
                //called for every count down interval
                override fun onTick(millsTillFinish: Long) {
                    remainingTimeText.value =
                        String.format(Locale.getDefault(), "%02d", millsTillFinish/1000)
                }
                //called when count down finish
                override fun onFinish() {
                    //cancel the timer
                    cancel()
                    remainingTimeText.value = "Time's up"
                }
            }
                    //to auto start the timer
                    .start()
        )
    }


    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text("Remaining Time: ")
            Text(text = remainingTimeText.value)
        }

        Spacer(modifier.height(26.dp))

        Row(modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                timer.value.start()
            }) { Text("Start Timer") }
            Button(onClick = {
                //stop timer
                timer.value.cancel()
            }) { Text("Stop Timer") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    AdnroidLearningKTSTheme {
        CountdownTimerComponent()
    }
}