package com.adnroidlearningkts.jetpackcompose.navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adnroidlearningkts.R
import com.adnroidlearningkts.jetpackcompose.navigation.ui.theme.AdnroidLearningKTSTheme
import kotlinx.serialization.Serializable

class NavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdnroidLearningKTSTheme {
                Navigation()
            }
        }
    }
}

/**
 * Composable fun to control navigation
 */
@Composable
fun Navigation() {

    val navController = rememberNavController()
    /**
     * NavHost links NavController with Navgraph
     *
     * Each NavController must be associated with a single NavHost composable,
     * when navigate between composables, the content of NavHost is automatically recomposed
     *
     * use `startDestinations` instead of `graph`: a string label represent the page,
     * when working with Navigation, set a label for each screen, and can use the label for navigation
     *
     * NavHost creation use lambda syntax to construct the navigation graph
     */
    NavHost(navController = navController, startDestination = "MainPage") {
        /**
         * navigation structure by composable() method
         *
         * each composable destination in the navigation graph is associated with a route
         *
         * route: a string defines the path to the composable -> link to a specific destination
         *      - each destination should has a unique route
         *      - use the label as route
         */
        composable(route = "MainPage") {
            NavMainPage(navController)
        }

        /**
         * define the parameters with type that SecondPage receive from MainPage
         * define type: arguments
         *
         * SecondPage/{uName}/{uAge}: when define this way, uName & uAge is required, will cause the
         *      app crash if not have values
         *
         * To make the parameters optional, define as query parameters
         *      SecondPage/{uName}?uAge={uAge}: uName is required and uAge is optional
         *      SecondPage?uName={uName}&uAge={uAge}: all params are optional
         */
        composable(route = "SecondPage?name={uName}&age={uAge}",
                    arguments = listOf(
                        navArgument("uName") {
                            type = NavType.StringType; nullable = true; defaultValue = "Unknown"},
                        navArgument("uAge") {
                            type = NavType.IntType; defaultValue = 0 }
                    )) {navBackStackEntry ->
            //To get the data from MainPage - use navBackStackEntry(it)
            val name = navBackStackEntry.arguments?.getString("uName")
            val age = navBackStackEntry.arguments?.getInt("uAge")
            Log.i("TAG", "name: $name - age: $age")
            NavSecondPage(navController, name ?: "Unknown", age ?: 0)
//            NavSecondPage(navController, name!!, age!!)
        }
    }
}