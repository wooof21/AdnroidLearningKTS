package com.adnroidlearningkts.jetpackcompose.widgets

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.adnroidlearningkts.R
import com.adnroidlearningkts.jetpackcompose.widgets.ui.theme.AdnroidLearningKTSTheme

class DialogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdnroidLearningKTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DialogComponent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * dialog: a small window that prompts user to make a decision or enter additional info
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogComponent( modifier: Modifier = Modifier) {

    val dialogState = remember {
        mutableStateOf(false)
    }
    val btnBgColor = remember {
        mutableStateOf(Color.Unspecified)
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = {
            dialogState.value = true
        }, colors = ButtonDefaults.buttonColors(
            containerColor = btnBgColor.value
        )) {
            Text("Show Dialog Message")
        }

        if(dialogState.value) {
            BasicAlertDialog(
                onDismissRequest = {
                    //set to true if dont want to close the AlertDialog when user clicked the rest screen
                    dialogState.value = true
                }
            ) {
                Surface(modifier = Modifier.wrapContentSize(),
                    shape = RoundedCornerShape(8.dp),
                    tonalElevation = AlertDialogDefaults.TonalElevation) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Row(horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically) {
                            Image(painter = painterResource(R.drawable.compose_alert_icon), contentDescription = "")
                            Text("Dialog Message", color = Color.Red, fontSize = 26.sp, modifier = Modifier.padding(start = 8.dp))
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        Row {
                            Text("Change the background color of the Button?", color = Color.Black, fontSize = 18.sp)
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Button(onClick = { dialogState.value = false }) { Text("No")}
                            Spacer(modifier.width(8.dp))
                            Button(onClick = {
                                dialogState.value = false
                                btnBgColor.value = Color.Red
                            }) { Text("Yes") }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview9() {
    AdnroidLearningKTSTheme {
        DialogComponent()
    }
}