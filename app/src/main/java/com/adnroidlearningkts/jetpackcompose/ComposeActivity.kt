package com.adnroidlearningkts.jetpackcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adnroidlearningkts.jetpackcompose.apps.todolist.TodoListActivity
import com.adnroidlearningkts.jetpackcompose.listlazylayout.LazyColumnActivity
import com.adnroidlearningkts.jetpackcompose.listlazylayout.LazyGridHorizontalActivity
import com.adnroidlearningkts.jetpackcompose.listlazylayout.LazyGridVerticalActivity
import com.adnroidlearningkts.jetpackcompose.listlazylayout.LazyRowActivity
import com.adnroidlearningkts.jetpackcompose.navigation.NavigationActivity
import com.adnroidlearningkts.jetpackcompose.ui.theme.AndroidLearningKTSTheme
import com.adnroidlearningkts.jetpackcompose.widgets.CheckBoxActivity
import com.adnroidlearningkts.jetpackcompose.widgets.CountdownTimerActivity
import com.adnroidlearningkts.jetpackcompose.widgets.DialogActivity
import com.adnroidlearningkts.jetpackcompose.widgets.DropdownMenuActivity
import com.adnroidlearningkts.jetpackcompose.widgets.ImageActivity
import com.adnroidlearningkts.jetpackcompose.widgets.LayoutActivity
import com.adnroidlearningkts.jetpackcompose.widgets.RadioBtnActivity
import com.adnroidlearningkts.jetpackcompose.widgets.SnackbarMsgActivity
import com.adnroidlearningkts.jetpackcompose.widgets.SwitchActivity
import com.adnroidlearningkts.jetpackcompose.widgets.TextFieldActivity
import com.adnroidlearningkts.jetpackcompose.widgets.ToastActivity
import com.adnroidlearningkts.jetpackcompose.widgets.TopbarActivity

//https://developer.android.com/develop/ui/compose/documentation
class ComposeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidLearningKTSTheme {
                /**
                 * Surface vs Scaffold:
                 *
                 * Surface
                 *      Purpose: Surface is a fundamental building block in Material Design.
                 *      It represents a section of the UI that has a distinct background color,
                 *      elevation, and shape. It's used to give visual structure to different parts
                 *      of the layout and apply Material styling.
                 * Key Features:
                 *      Background: Sets a background color.
                 *      Elevation: Adds a shadow to simulate depth.
                 *      Shape: Defines the shape of the surface (e.g., rounded corners, cut corners).
                 *      Borders: Allows adding a border.
                 *      Clicks: Can be made clickable.
                 * Usage: Would typically use Surface for individual UI elements or containers within a larger layout, such as:
                 *      Cards
                 *      Buttons
                 *      Dialogs
                 *      Containers for lists or grids
                 *      Any area where you want to apply Material background, elevation, and shape.
                 *
                 * Scaffold
                 *      Purpose: Scaffold is a high-level layout structure that provides a standardized
                 *      framework for building screens that follow the core Material Design layout principles.
                 *      It defines slots for common screen elements.
                 * Key Features:
                 *      Top App Bar: A slot for placing an AppBar at the top of the screen.
                 *      Bottom Bar: A slot for placing a BottomAppBar or other bottom navigation elements.
                 *      Floating Action Button (FAB): A slot for a FloatingActionButton, often with an optional position relative to the bottom bar.
                 *      Drawer: A slot for a navigation drawer.
                 *      Snackbar: A slot for displaying Snackbar messages.
                 *      Content: The main content area of the screen, where you place the primary UI elements.
                 *
                 * Usage: Would use Scaffold as the top-level layout for a complete screen or
                 *      a major section of the UI. It helps quickly assemble a screen with standard
                 *      Material Design components in their appropriate positions.
                 */
                Scaffold(
                    topBar = {
                        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Gray
                        ),
                            title = {
                                Text(text = "Scaffold TopAppBar")
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar() {
                            CompositionLocalProvider() {
                                IconButton(onClick = { /* doSomething() */ }) {
                                    Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                                }
                            }
                            Spacer(Modifier.weight(1f, true))
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                            }
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(Icons.Filled.Favorite, contentDescription = "Localized description")
                            }
                        }
                    },
                    modifier = Modifier) { innerPadding ->
                    Components(
                        modifier = Modifier.padding(
                            top = innerPadding.calculateTopPadding(),
                            bottom = innerPadding.calculateBottomPadding()).verticalScroll(
                            rememberScrollState()
                        )
                    )
                }
            }
        }
    }
}

/**
 *  calling startActivities from inside the onClick lambda of a Button,
 *  which is part of the @Composable function Components.
 *
 *  Composable functions are not tied to a specific lifecycle instance like Activities or Fragments.
 *
 *  The onClick lambda is executed by the Compose runtime when the button is interacted with.
 *  This execution happens within the Compose context, not directly within the
 *  ComposeActivity instance's scope in the way a traditional OnClickListener in an XML-based layout would be.
 *
 *  Need to obtain the Context within the composable and then pass it to the function
 */
private fun startActivities(context: Context, java: Class<out android.app.Activity>) {
    context.startActivity(Intent(context, java))
}

@Composable
fun Components(modifier: Modifier = Modifier) {
    // Obtain the Context within the composable
    val context = LocalContext.current

    /**
     * remember {} : The remember composable function is used to cache a value during the initial
     * composition and subsequent recompositions. It ensures that the lambda expression inside
     * `remember` is executed only once when the composable is first added to the composition.
     * This is crucial for maintaining state across recompositions.
     *
     * mutableStateOf: a special type in Compose that holds a value and triggers a
     *      recomposition whenever the value changes.
     */
    val btnBackgroundColor = remember {
        mutableStateOf(Color.Unspecified)
    }

    Column(modifier =
            modifier
            .fillMaxSize()
            .background(Color.White),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Row {
            Text("--------Compose Widgets--------", fontSize = 26.sp,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Center)
        }
        Row {
            Button(onClick = {
                //change btn background color
                btnBackgroundColor.value = Color.Magenta
                startActivities(context, LayoutActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value, // Set the background color here
                    contentColor = Color.White // Set the text color here (optional, but good practice)
                    // Can also specify other colors for different states:
                    // disabledContainerColor = Color.Gray,
                    // disabledContentColor = Color.DarkGray
                ),
                //to make the shape circle, either put the correct dp size or put integer value
                // instead of dp (100), it will become percentage
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                //use the padding modifier to add space around a Composable,
                // effectively creating a margin
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("Layout")
            }

            Button(onClick = {
                startActivities(context, SwitchActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("Switch")
            }

            Button(onClick = {
                startActivities(context, ImageActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("Image")
            }

            Button(onClick = {
                startActivities(context, ToastActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("Toast")
            }
        }

        Row {

            Button(onClick = {
                startActivities(context, CheckBoxActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("CheckBox")
            }

            Button(onClick = {
                startActivities(context, RadioBtnActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("RadioButton")
            }

            Button(onClick = {
                startActivities(context, TextFieldActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("TextField")
            }
        }

        Row {
            Button(onClick = {
                startActivities(context, DropdownMenuActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("Dropdown Menu")
            }



            Button(onClick = {
                startActivities(context, SnackbarMsgActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("Snackbar")
            }

            Button(onClick = {
                startActivities(context, DialogActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("Dialog")
            }
        }

        Row {

            Button(onClick = {
                startActivities(context, TopbarActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("TopBar & Options Menu")
            }

            Button(onClick = {
                startActivities(context, CountdownTimerActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("Countdown Timer")
            }


        }

        Row {
            Text("--------Compose Navigation--------", fontSize = 26.sp,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Center)
        }
        Row {
            Button(onClick = {
                startActivities(context, NavigationActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("Navigation")
            }
        }

        Row {
            Text("--------Compose List--------", fontSize = 26.sp,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Center)
        }
        Row {
            Button(onClick = {
                startActivities(context, LazyColumnActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("LazyColumn")
            }

            Button(onClick = {
                startActivities(context, LazyRowActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("LazyRow")
            }

        }

        Row {
            Button(onClick = {
                startActivities(context, LazyGridHorizontalActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("LazyGrid - Horizontal")
            }

            Button(onClick = {
                startActivities(context, LazyGridVerticalActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("LazyGrid - Vertical")
            }
        }

        Row {
            Text("--------Compose Apps--------", fontSize = 26.sp,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Center)
        }
        Row {
            Button(onClick = {
                startActivities(context, TodoListActivity::class.java) },
                colors = ButtonDefaults.buttonColors(
                    //dynamic change the btn color
                    containerColor = btnBackgroundColor.value,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Cyan),
                modifier = Modifier.padding(start = 8.dp)

            ) {
                Text("TodoList")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ComponentsPreview() {
    AndroidLearningKTSTheme {
        Components()
    }
}