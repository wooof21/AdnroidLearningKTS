package com.adnroidlearningkts.jetpackcompose.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.adnroidlearningkts.R
import com.adnroidlearningkts.jetpackcompose.widgets.ui.theme.AdnroidLearningKTSTheme

/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

class TopbarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //https://github.com/android/nowinandroid/blob/2b218b97d959d79268bb0883a9e31ebcea18cb82/app/src/main/java/com/google/samples/apps/nowinandroid/MainActivity.kt
            //https://github.com/android/nowinandroid/pull/817/commits/2b218b97d959d79268bb0883a9e31ebcea18cb82
            //https://medium.com/@shubhgajjar2004/different-ways-to-change-the-color-of-status-bar-and-navigation-bar-in-android-jetpack-compose-edd1c5542257
            val darkTheme = isSystemInDarkTheme()
            // Update the edge to edge configuration to match the theme
            // This is the same parameters as the default enableEdgeToEdge call, but we manually
            // resolve whether or not to show dark theme using uiState, since it can be different
            // than the configuration's dark theme value based on the user preference.
            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        android.graphics.Color.TRANSPARENT,
                        android.graphics.Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim,
                        darkScrim,
                    ) { darkTheme },
                )
                onDispose {}
            }
            AdnroidLearningKTSTheme {
                TopbarComponent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopbarComponent() {

    val actionText = remember {
        mutableStateOf("")
    }

    val optionsMenuState = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        actionText.value = "Navigation Icon Clicked"
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "")
                    }
                },
                //include the subtitle
                title = {
                    Column {
                        Text(text = stringResource(R.string.app_name),
                            fontSize = 18.sp, color = Color.White)
                        //subtitle
                        Text(text = "Subtitle",
                            fontSize = 15.sp, color = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        actionText.value = "Share Icon Clicked"
                    }) {
                        Icon(Icons.Filled.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = {
                        actionText.value = "Search Icon Clicked"
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = {
                        actionText.value = "More Icon Clicked"
                        optionsMenuState.value = true
                    }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "More")
                        if(optionsMenuState.value) {
                            OptionsMenComponent(actionText, optionsMenuState)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.purple),
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) {innerPadding ->
        ContentComponent(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
            actionText.value)
    }
}

@Composable
fun OptionsMenComponent(actionText: MutableState<String>, optionsMenuState: MutableState<Boolean>) {


    DropdownMenu(expanded = optionsMenuState.value,
        //respond to when use click the rest of screen -> close the Dropdown Menu
        onDismissRequest = { optionsMenuState.value = false}) {
        DropdownMenuItem(
            text = { Text("Settings") },
            onClick = {
                optionsMenuState.value = false
                actionText.value = "Options Menu - Settings Clicked"
            }
        )
        DropdownMenuItem(
            text = { Text("Logout") },
            onClick = {
                optionsMenuState.value = false
                actionText.value = "Options Menu - Logout Clicked"
            }
        )
    }
}

@Composable
fun ContentComponent(modifier: Modifier = Modifier, action: String) {


    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(action, color = Color.Red, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    AdnroidLearningKTSTheme {
        TopbarComponent()
    }
}