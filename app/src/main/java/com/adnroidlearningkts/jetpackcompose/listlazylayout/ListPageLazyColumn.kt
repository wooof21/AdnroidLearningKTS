package com.adnroidlearningkts.jetpackcompose.listlazylayout

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adnroidlearningkts.R


/**
 * In Jetpack Compose, the equivalent of the traditional Android ListView and RecyclerView are the Lazy Layouts,
 *
 * specifically:
 *
 *  * LazyColumn: For vertical scrolling lists.
 *  * LazyRow: For horizontal scrolling lists.
 *
 * These Compose components are called "lazy" because they only compose and lay out items that are
 * currently visible in the viewport, similar to how RecyclerView reuses and recycles views.
 * This makes them highly efficient for displaying large numbers of items or lists of unknown length.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPageColumnComponent(navController: NavController) {

    /**
     * Monitor the TopBar behavior and tell TopBar to change the background color as soon as the content
     * goes behind the TopBar
     *
     * Then in Scaffold, use `Modifier.nestedScroll()` to track the scroll event
     *
     * the action to take when content behind the TopBar is to change color, use `scrolledContainerColor`
     *
     * Last, to notify the TopAppBar about the change by: scrollBehavior = topBarBehavior
     */
    val topBarBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(topBarBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Countries", fontSize = 18.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.purple),
                    titleContentColor = Color.White,
                    scrolledContainerColor = colorResource(R.color.purple_light)
                ),
                scrollBehavior = topBarBehavior
            )
        },
        content = {
            ListLazyColumn(modifier = Modifier.padding(it), navController)
        }
    )
}

@Composable
fun ListLazyColumn(modifier: Modifier, navController: NavController) {

    val countries = retrieveCountries()

    LazyColumn(modifier = modifier) {
        /**
         * item {}: to display only one item in LazyColumn, can be used for list header
         * items {]: to display a list of items - data set
         */
        items(
            count = countries.size,
            itemContent = { index ->
                val country = countries[index]
                CountryItemCardComponent(country, navController)
            }
        )
    }
}

@Composable
fun CountryItemCardComponent(country: CountryModel, navController: NavController) {

    val context = LocalContext.current

    Card(
        onClick = {
            Toast.makeText(
                context,
                "Country - ${country.countryName} Clicked",
                Toast.LENGTH_SHORT
            ).show()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.purple)
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        border = BorderStroke(2.dp, Color.Yellow)
    ) {
        Row(modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(8f)) {
                Image(painter = painterResource(id = country.countryImg),
                    contentDescription = country.countryName,
                    modifier = Modifier.size(88.dp)
                        //clip: to create circle shape
                        .clip(RoundedCornerShape(100))
                        .border(2.dp, Color.Red, RoundedCornerShape(100)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )

                Column(modifier = Modifier.padding(start = 18.dp)) {
                    Text(text = country.countryName, fontSize = 26.sp, color = Color.White)
                    Spacer(Modifier.height(5.dp))
                    Text(text = country.countryDetail, fontSize = 18.sp, color = Color.White)
                }
            }

            Button(
                onClick = {

                    navController.navigate("DetailsPage/${country.countryId}")

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(2.dp, Color.Red),
                modifier = Modifier.weight(2f)) {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowForward, contentDescription = "Details",
                    //tint: change the color of Icon
                    tint = Color.Red)
            }
        }


    }
}