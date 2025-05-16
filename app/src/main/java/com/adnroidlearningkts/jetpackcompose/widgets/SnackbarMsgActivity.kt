package com.adnroidlearningkts.jetpackcompose.widgets

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.adnroidlearningkts.jetpackcompose.widgets.ui.theme.AdnroidLearningKTSTheme
import kotlinx.coroutines.launch

class SnackbarMsgActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdnroidLearningKTSTheme {
                SnackbarComponent()
            }
        }
    }
}

/**
 * Snackbar provide brief messages about app process at the bottom of the screen,
 * it has an action button to do something
 *
 *      * Scaffold: to place the Snackbar in the layout
 *      * Snackbar: visual representation of Snackbar as defined in Material Design guidelines,
 *          without show or hide options and without animations
 *      * SnackbarHost: responsible for showing and hiding Snackbar as well as the animations.
 *          its a UI wrapper around Snackbar
 *      * SnackbarHostState: controls the current Snackbar being shown inside the SnackbarHost as
 *          well as the queue of Snackbars supposed to be shown later
 *              - guarantee to show at most one Snackbar at a time
 *              - has a `suspend` method to show a new Snackbar
 */
@Composable
fun SnackbarComponent(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    //observe the Snackbar host state
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                //snackbar customization
                snackbar = {
                    Snackbar(snackbarData = it,
                        containerColor = Color.Red,
                        contentColor = Color.White,
                        actionColor = Color.Blue,
                        dismissActionContentColor = Color.Black)
                }
            )
        },
        /**
         * The lambda receives a PaddingValues, that should be applied to the content root via
         * `modifier.padding()` to properly offset the top and bottom bars, to prevent layout overlapping
         *
         * snackbarHostState.showSnackbar() is a `suspend` fun, need to run in a coroutine scope
         */
        content = { innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Button(onClick = {

                    coroutineScope.launch {
                        val snackBarResult = snackbarHostState.showSnackbar(
                            message = "The Snackbar Message",
                            actionLabel = "Show Toast",
                            //if set duration(Long/Short), Snackbar will be automatically closed
                            //Indefinite: remain on the UI until user clicked an action button
                            duration = SnackbarDuration.Indefinite,
                            //to show the close icon (X) when true, false -> only show action button
                            withDismissAction = true
                        )
                        if(snackBarResult == SnackbarResult.ActionPerformed) {
                            Toast.makeText(context, "The Toast Message", Toast.LENGTH_SHORT).show()
                        }
                    }
                }) {
                    Text("Show Snackbar Message")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    AdnroidLearningKTSTheme {
        SnackbarComponent()
    }
}