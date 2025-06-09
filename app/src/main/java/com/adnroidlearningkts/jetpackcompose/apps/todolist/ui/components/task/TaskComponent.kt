package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adnroidlearningkts.jetpackcompose.apps.todolist.model.data.TaskPriority
import com.adnroidlearningkts.jetpackcompose.apps.todolist.utils.Constants.MAX_TITLE_LENGTH

@Composable
fun TaskContentComponent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: TaskPriority,
    onPrioritySelected: (TaskPriority) -> Unit,
    modifier: Modifier = Modifier
) {

    val isError: Boolean = title.length > MAX_TITLE_LENGTH

    Column(modifier = modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(8.dp)) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = "Title") },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            isError = isError,
            supportingText = {
                if (isError) {
                    Text(
                        text = "Maximum $MAX_TITLE_LENGTH characters exceeded",
                        color = MaterialTheme.colorScheme.error
                    )
                } else {
                    // Optional: Show character count
                    Text(text = "${title.length} / $MAX_TITLE_LENGTH")
                }
            }
        )

        Spacer(Modifier.height(18.dp))

        PriorityDropdownMenu(priority, onPrioritySelected)

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = { onDescriptionChange(it) },
            label = { Text(text = "Description") },
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
@Preview
fun TaskContentComponentPreview() {
    TaskContentComponent("", {}, "", {},
        TaskPriority.MEDIUM, {})
}