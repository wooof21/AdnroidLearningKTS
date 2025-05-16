package com.adnroidlearningkts.jetpackcompose.listlazylayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adnroidlearningkts.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDetailsComponent(navController: NavController, countryId: Int) {

    val countries = retrieveCountries()
    val selectedCountry = countries.find { it.countryId == countryId }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                title = { Text("DetailsPage", fontSize = 26.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.purple),
                    titleContentColor = Color.White
                )
            )
        },
        content = {
            ListDetailsColumn(modifier = Modifier.padding(it), selectedCountry)
        }
    )
}

/**
 * Navigation Component advises against passing complex data directly in navigation arguments and
 * recommends passing only minimal necessary information (like an ID).
 *
 * Passing an ID and Retrieving the Object in the Destination (Recommended):
 * it is generally the preferred approach, especially for more complex objects or if the data might change.
 */
@Composable
fun ListDetailsColumn(modifier: Modifier, selectedCountry: CountryModel?) {


    Column(modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = painterResource(selectedCountry?.countryImg ?: R.drawable.ic_launcher_foreground),
            contentDescription = selectedCountry?.countryName,
            modifier = Modifier.size(358.dp, 188.dp).border(2.dp, Color.Black),
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center)

        Spacer(Modifier.height(55.dp))
        Text(text = selectedCountry?.countryName ?: "N/A", fontSize = 26.sp, color = Color.Black)

        Spacer(Modifier.height(18.dp))
        Text(text = selectedCountry?.countryDetail ?: "N/A", fontSize = 18.sp, color = Color.Black)
    }
}