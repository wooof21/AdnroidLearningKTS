package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.task

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TaskPriority
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.PriorityItemComponent


@Composable
fun PriorityDropdownMenu(priority: TaskPriority, onPrioritySelected: (TaskPriority) -> Unit) {

    var expandState by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if(expandState) 180f else 0f
    )

    //instead of using an hard coded fraction value to set the Dropdown Menu width
    //use parentSize to dynamic calculate the parent width
    var parentSize by remember { mutableStateOf(IntSize.Zero) }

    Row(
        modifier = Modifier.fillMaxWidth()
            /**
             * It's used to get the size and position of a composable after it has been measured and placed on the screen.
             * The lambda expression ({ ... }) will be executed when the layout coordinates of the composable it's attached to are known.
             * it.size: This property of LayoutCoordinates gives the measured IntSize of the composable (width and height in pixels).
             */
            .onGloballyPositioned {
                parentSize = it.size
            }
            .height(58.dp)
            .background(Color.Transparent)
            .clickable( onClick = { expandState = true } )
            .border(width = 1.dp,
                color = Color.Cyan,
                shape = RoundedCornerShape(5.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(8f).padding(start = 8.dp)) {
            PriorityItemComponent(priority)
        }
        IconButton(
            modifier = Modifier.weight(2f).rotate(degrees = angle),
            onClick = { expandState = true }
        ) {
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
        }

        DropdownMenu(
            expanded = expandState,
            onDismissRequest = {
                expandState = false
            },
            //fraction: with * fraction = final width
            modifier = Modifier
//                .fillMaxWidth(fraction = 0.95f)
                /**
                 * set the width of Dropdown Menu to its parent width
                 *
                 *  * with(LocalDensity.current) {}: scope function
                 *      - LocalDensity.current: LocalDensity provides information about the
                 *          current screen's pixel density. `.current` accesses this density value.
                 *      - The with block makes the Density object available as this inside the lambda.
                 *          This allows to call functions defined on the Density object directly, like toDp().
                 */
                .width(with(LocalDensity.current) { parentSize.width.toDp() })

        ) {
            //take only the indexes0, 2, 3 which are Low, HIGH & NONE, since only sort the list
            //based on these 3 priorities
            TaskPriority.entries.slice(0..2).forEach {
                    priority ->
                DropdownMenuItem(
                    onClick = {
                        expandState = false
                        onPrioritySelected(priority)
                    },
                    text = {
                        PriorityItemComponent(priority = priority)
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun PriorityDropdownMenuPreview() {
    PriorityDropdownMenu(TaskPriority.HIGH, {})
}