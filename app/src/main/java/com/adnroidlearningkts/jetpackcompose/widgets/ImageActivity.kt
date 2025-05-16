package com.adnroidlearningkts.jetpackcompose.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adnroidlearningkts.R
import com.adnroidlearningkts.jetpackcompose.ui.theme.AndroidLearningKTSTheme

class ImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidLearningKTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ImageComponent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ImageComponent(modifier: Modifier = Modifier) {

    val imagePath = remember {
        mutableStateOf(R.drawable.grocery_header)
    }
    Column {
        Image(painter = painterResource(imagePath.value), contentDescription = "",
            //set image container size
            modifier = Modifier.height(300.dp).fillMaxWidth(),
            /**
             * FillBounds: fill the image into container when image size is bigger
             * Fit: scale the image uniformly so that both dimensions of the picture will
             *      be equal to or less than corresponding dimension of image component and
             *      maintain the pick's aspect ratio
             */
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center)

        Button(onClick = {
            imagePath.value = R.drawable.cardview_header
        }) {
            Text("Change Image")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    AndroidLearningKTSTheme {
        ImageComponent()
    }
}