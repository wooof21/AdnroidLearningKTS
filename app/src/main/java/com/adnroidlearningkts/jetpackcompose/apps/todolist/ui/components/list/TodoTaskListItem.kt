package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TaskPriority
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TodoTask
import com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.PriorityItemComponent


@Composable
fun TaskListItemComponent(task: TodoTask, navigateToTaskDetailPage: (taskId: Int) -> Unit) {

    Card(
        modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 18.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        border = BorderStroke(2.dp, Color.Blue),
        onClick = { navigateToTaskDetailPage(task.id) }
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()) {
            Row(modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = task.title,
                    color = Color.Black,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.MiddleEllipsis,
                    modifier = Modifier.weight(6f))
                Box(modifier = Modifier.weight(4f).fillMaxWidth()) {
                    PriorityItemComponent(task.priority)
                }
            }
            Text(modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                text = task.description,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}



@Composable
@Preview
fun TaskListItemComponentPreview() {
    TaskListItemComponent(
        TodoTask(title = "title", description = "description", priority = TaskPriority.LOW),
        {})
}