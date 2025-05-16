package com.adnroidlearningkts.jetpackcompose.listlazylayout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.adnroidlearningkts.R

@Composable
fun retrieveCountries(): SnapshotStateList<CountryModel> {

    val countries = remember {
        mutableStateListOf(
            CountryModel(1,"Argentina","This is the Argentina Flag",R.drawable.compose_argentina),
            CountryModel(2,"Brazil","This is the Brazil Flag",R.drawable.compose_brazil),
            CountryModel(3,"Bulgaria","This is the Bulgaria Flag",R.drawable.compose_bulgaria),
            CountryModel(4,"France","This is the France Flag",R.drawable.compose_france),
            CountryModel(5,"Germany","This is the Germany Flag",R.drawable.compose_germany),
            CountryModel(6,"Ireland","This is the Ireland Flag",R.drawable.compose_ireland),
            CountryModel(7,"Italy","This is the Italy Flag",R.drawable.compose_italy),
            CountryModel(8,"Netherlands","This is the Netherlands Flag",R.drawable.compose_netherlands),
            CountryModel(9,"Romania","This is the Romania Flag",R.drawable.compose_romania),
            CountryModel(10,"Slovakia","This is the Slovakia Flag",R.drawable.compose_slovakia),
            CountryModel(11,"Spain","This is the Spain Flag",R.drawable.compose_spain),
            CountryModel(12,"Turkey","This is the Turkey Flag",R.drawable.compose_turkey)
        )
    }

    return countries
}