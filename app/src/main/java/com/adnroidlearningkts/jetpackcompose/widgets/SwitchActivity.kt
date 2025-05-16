package com.adnroidlearningkts.jetpackcompose.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adnroidlearningkts.R
import com.adnroidlearningkts.jetpackcompose.widgets.ui.theme.AdnroidLearningKTSTheme

class SwitchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdnroidLearningKTSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SwitchComponents(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SwitchComponents(modifier: Modifier = Modifier) {

    val switchState = remember {
        mutableStateOf(true)
    }
    val switchTexts = remember {
        mutableStateOf("On")
    }
    val textView = remember {
        mutableStateOf("The Image is Visible")
    }

    // Start the slide from 40 (pixels) above where the content is supposed to go,
    // initialOffsetY to produce a parallax effect
    val enterTransition = slideInVertically(initialOffsetY = { -40 }) +
            expandVertically(expandFrom = Alignment.Top, animationSpec = tween(500)) +
            // Animate scale from 0f to 1f using the top center as the pivot point.
            //0.5f, 0.5f -> center of image
            scaleIn(transformOrigin = TransformOrigin(0.5f, 0.5f), animationSpec = tween(2000)) +
            fadeIn(initialAlpha = 0f)

//        fadeIn(animationSpec = tween(2000)) +
//            expandVertically(animationSpec = tween(2000))
    val exitTransition  = slideOutVertically(targetOffsetY = { -40 }) +
            shrinkVertically(shrinkTowards = Alignment.Top,animationSpec = tween(2000)) +
            fadeOut(animationSpec = tween(2000)) + scaleOut(targetScale = 2f)
//        fadeOut(animationSpec = tween(2000)) +
//            shrinkVertically(animationSpec = tween(2000))

    Column{
        Text(text = "A Switch allows the user to change a setting between two states: on/off",
            textAlign = TextAlign.Center, modifier = Modifier.padding(18.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = switchState.value, onCheckedChange = {
                switchState.value = it
                switchTexts.value = when(it) {
                    true -> "On"
                    else -> "Off"
                }
                textView.value = when(it) {
                    true -> "The Image is Visible"
                    else -> "The Image is Invisible"
                }
            },
                colors = SwitchDefaults.colors(
                    //circle color when on
                    checkedThumbColor = Color.Blue,
                    //switch background color when on
                    checkedTrackColor = Color.Green
                ))
            Text(switchTexts.value, Modifier.padding(start = 8.dp))
        }

        /**
         * Using AnimatedVisibility: for animating the appearance and disappearance of content,
         * including an Image.
         * AnimatedVisibility removes the composable from the composition once the exit animation is complete.
         */
        AnimatedVisibility(visible = switchState.value,
            enter = enterTransition,
            exit = exitTransition) {
            Image(painter = painterResource(R.drawable.cardview_football), contentDescription = "",
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
        }

        /**
         * Using the alpha modifier for invisibility (without removing from composition):
         *      If want the Image to take up space in the layout but be completely transparent (invisible),
         *      can use the alpha modifier.
         *      - 1.0f is fully visible, 0.0f is fully transparent
         */

        Image(painter = painterResource(R.drawable.cardview_tennis), contentDescription = "",
            //set image container size
            modifier = Modifier.height(300.dp).fillMaxWidth()
                .alpha(if (switchState.value) 1.0f else 0.0f),
            /**
             * FillBounds: fill the image into container when image size is bigger
             * Fit: scale the image uniformly so that both dimensions of the picture will
             *      be equal to or less than corresponding dimension of image component and
             *      maintain the pick's aspect ratio
             */
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center)


        Text(textView.value,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    AdnroidLearningKTSTheme {
        SwitchComponents()
    }
}