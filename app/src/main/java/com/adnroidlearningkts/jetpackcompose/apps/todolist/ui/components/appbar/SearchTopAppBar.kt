package com.adnroidlearningkts.jetpackcompose.apps.todolist.ui.components.appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.adnroidlearningkts.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    searchText: String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primary
            containerColor = colorResource(R.color.purple)
        ),
        title = {
            TextField(
                value = searchText,
                onValueChange = {
                    onTextChanged(it)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Search Title or Description", color = Color.LightGray)
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search",
                        tint = Color.White)
                },
                trailingIcon = {
                    IconButton(onClick = { onCloseClicked() }) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close",
                            tint = Color.White)
                    }
                },
                /**
                 * the Keyboard `Enter` button becomes a `Search` Icon
                 * Enter action becomes a `Search` action
                 */
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(searchText)
                    }
                )
            )

        }
    )
}

@Composable
@Preview
fun SearchBarPReview() {
    SearchTopBar("", {}, {}, {})
}