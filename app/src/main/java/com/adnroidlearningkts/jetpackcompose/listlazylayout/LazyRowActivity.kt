package com.adnroidlearningkts.jetpackcompose.listlazylayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adnroidlearningkts.jetpackcompose.listlazylayout.ui.theme.AdnroidLearningKTSTheme

class LazyRowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdnroidLearningKTSTheme {
                LazyRowListNavigation()
            }
        }
    }
}

@Composable
fun LazyRowListNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "ListPageLazyRow") {

        composable("ListPageLazyRow") {
            ListPageRowComponent(navController)
        }

        composable("DetailsPage/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )) {
            val countryId = it.arguments?.getInt("id")
            ListDetailsComponent(navController, countryId ?: -1)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    AdnroidLearningKTSTheme {
        LazyRowListNavigation()
    }
}