package com.adnroidlearningkts.jetpackcompose.apps.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.adnroidlearningkts.jetpackcompose.apps.todolist.navigation.NavigationSetup
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.theme.AdnroidLearningKTSTheme
import com.adnroidlearningkts.jetpackcompose.apps.todolist.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Compose Navigation, RoomDB, SQL Query, Transition Animation, ViewModel
 * Preference DataStore, Dagger-Hilt, Theme Switch, Splash Screen,
 * SwipeToDelete, Undo Delete, Snackbar, State
 */
@AndroidEntryPoint
class TodoListActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdnroidLearningKTSTheme {
                navController = rememberNavController()
                NavigationSetup(navController, sharedViewModel)
            }
        }
    }
}

/**
 * State in Compose: is any value that can change over time
 *  - Stateful Composable: hold State inside the composable fun - hard to reuse and test
 *  - Stateless Composable: do not hold any State - recommend to use
 *
 * To achieve it: use `State Hoisting` - a programming pattern
 *      - move the state of a composable to the caller of that composable
 *      - e.g. replace the State with parameters and using lambdas to represent events
 *      - ViewModels are the recommended State holder for Composable+
 *
 * https://developer.android.com/develop/ui/compose/side-effects
 * Side Effect: a change to the State of the app that happens outside the scope of composable fun
 *      - all composable should be a side effect free
 *      - e.g.
 *              var number = 0
 *
 *              @Composable
 *              fun CountComposable() {
 *                  number++
 *              }
 *      - Side Effect happens since trying to modify a variable which lives outside of the Composable lifecycle
 *      - can cause some unexpected value in `number`, since recomposition happens multiple times when `number` changes
 *      - use the `SideEffect` Api
 *
 *              var number = 0
 *              @Composable
 *              fun CountComposable() {
 *                  SideEffect {
 *                      number++
 *                  }
 *              }
 *       - Non-Suspended: SideEffect {}, DisposableEffect {} ...
 *       - Suspended: used for internet call - LaunchedEffect {} , rememberCoroutineScope {} ...
 */

/**
 * Optional Arguments:
 *  1. must be included using query parameter syntax: path?argName={argName}
 *  2. must have a `defaultValue` set or have `nullability=true` - which implicitly set the
 *      defaultValue to null
 */
