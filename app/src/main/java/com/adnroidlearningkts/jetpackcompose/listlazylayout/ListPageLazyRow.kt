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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adnroidlearningkts.R
import dagger.Component


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ListPageRowComponent(navController: NavController) {

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
            ListLazyRow(modifier = Modifier.padding(it), navController)
        }
    )
}

@Composable
fun ListLazyRow(modifier: Modifier, navController: NavController) {

    val countries = retrieveCountries()

    //LazyColumn -> LazyRow
    LazyRow(modifier = modifier) {
        /**
         * item {}: to display only one item in LazyColumn, can be used for list header
         * items {]: to display a list of items - data set
         */
        items(
            count = countries.size,
            itemContent = { index ->
                val country = countries[index]
                CountryItemCardRowComponent(country, navController)
            }
        )
    }
}

@Composable
fun CountryItemCardRowComponent(country: CountryModel, navController: NavController) {

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
            .width(188.dp)
            .height(288.dp)
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