package com.adnroidlearningkts.jetpackcompose.listlazylayout


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adnroidlearningkts.R


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ListPageGridHorizontalComponent(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Countries", fontSize = 18.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.purple),
                    titleContentColor = Color.White,
                    scrolledContainerColor = colorResource(R.color.purple_light)
                )
            )
        },
        content = {
            ListLazyGridHorizontal(modifier = Modifier.padding(it), navController)
        }
    )
}

@Composable
fun ListLazyGridHorizontal(modifier: Modifier, navController: NavController) {

    val countries = retrieveCountries()

    //LazyColumn -> LazyRow
    /**
     * Two options for LazyGrid:
     *      LazyVerticalGrid: display items in a vertically scrollable container span multiple columns
     *      LazyHorizontalGrid: display items in a horizontal scrollable container span multiple rows
     *
     * LazyRow -> LazyHorizontalGrid
     *  -> rows = GridCells.Adaptive(200.dp): define minimum height of each item for the Grid,
     *              and automatically calculate how many rows will be
     *              - e.g. for a screen with 600 DPI, Grid will then create 3 rows
     *              - make the height 250.dp, for a screen with 600 DPI, Grid will then create 2 rows
     */
    LazyHorizontalGrid(modifier = modifier,
            rows = GridCells.Adaptive(288.dp)
            ) {
        /**
         * item {}: to display only one item in LazyColumn, can be used for list header
         * items {]: to display a list of items - data set
         */
        items(
            count = countries.size,
            itemContent = { index ->
                val country = countries[index]
                CountryItemCardGridHorizontalComponent(country, navController)
            }
        )
    }
}

@Composable
fun CountryItemCardGridHorizontalComponent(country: CountryModel, navController: NavController) {

    val context = LocalContext.current

    Card(
        onClick = {
            Toast.makeText(
                context,
                "Country - ${country.countryName} Clicked",
                Toast.LENGTH_SHORT
            ).show()
        },
        //no need to specify the height of Card, the height will be adjust automatically according
        //to the value specified for the minimum height of Grid
        modifier = Modifier
            .width(188.dp)
//            .height(288.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.purple)
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        border = BorderStroke(2.dp, Color.Yellow)
    ) {
        //Row -> Column
        Column(modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally) {

            //Row -> Column
            Column(horizontalAlignment = Alignment.CenterHorizontally,
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

                //start -> top
                //add: horizontalAlignment = Alignment.CenterHorizontally to center the content
                Column(modifier = Modifier.padding(top = 18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    //add : textAlign = TextAlign.Center to center text
                    Text(text = country.countryName, fontSize = 26.sp, color = Color.White, textAlign = TextAlign.Center)
                    Spacer(Modifier.height(5.dp))
                    Text(text = country.countryDetail, fontSize = 18.sp, color = Color.White, textAlign = TextAlign.Center)
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