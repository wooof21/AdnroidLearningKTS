package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TaskPriority


@Composable
fun PriorityItemComponent(priority: TaskPriority) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 8.dp)) {
        Canvas(modifier = Modifier.size(18.dp)) {
            drawCircle(color = priority.color)
        }
        Text(text = priority.name,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface)
    }
}

@Composable
@Preview
fun PriorityItemComponentPreview() {
    PriorityItemComponent(TaskPriority.HIGH)
}