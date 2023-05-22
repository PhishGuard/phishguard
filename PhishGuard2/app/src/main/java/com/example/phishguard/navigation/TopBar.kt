package com.example.phishguard.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.phishguard.R

enum class SearchWidgetState {
    OPENED,
    CLOSED
}

@Composable
fun TopBar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
    var searchWidgetState by remember{ mutableStateOf(SearchWidgetState.CLOSED) }
    var searchTextState by remember { mutableStateOf("") }


    if (searchWidgetState == SearchWidgetState.OPENED) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = searchTextState,
                onValueChange = {
                    searchTextState = it
                },
                placeholder = {
                    Text(
                        modifier = Modifier.alpha(ContentAlpha.medium),
                        text = "Search",
                        color = Color.White,
                    )
                },
                singleLine = true,
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        modifier = Modifier.alpha(ContentAlpha.high),
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (searchTextState.isNotEmpty() || searchTextState.isNotBlank()) {
                                searchTextState = ""
                            } else {
                                searchWidgetState = SearchWidgetState.CLOSED
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Close Icon",
                            tint = Color.White
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions (
                    onSearch = {
                        searchWidgetState = SearchWidgetState.CLOSED
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    backgroundColor = Color(0xFFBB86FC), // Color(R.color.purple_200),
                    cursorColor = Color.White
                ),
            )
        }
    } else {
        TopAppBar(
            title = { Text(text = title) },

            navigationIcon = {
                IconButton(onClick = {
                    onButtonClicked()
                }) {
                    Icon(
                        imageVector = buttonIcon,
                        contentDescription = "Drawer Icon"
                    )
                }
            },

            actions = {
                IconButton(onClick = { searchWidgetState = SearchWidgetState.OPENED }) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "",
                        tint = Color.White,
                    )
                }
            },
        )
    }
}

