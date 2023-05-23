package com.example.phishguard.navigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.phishguard.navigation.BottomNavigationBar
import com.example.phishguard.navigation.TopBar

@Composable
fun Home(
    openDrawer: () -> Unit,
    navController: NavHostController,
    onAddButtonClicked: (String) -> Unit,
    onRemoveButtonClicked: (Int) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var removeId by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopBar(
                    title = "Home",
                    buttonIcon = Icons.Filled.Menu,
                    onButtonClicked = { openDrawer() }
                )
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Welcome to PhishGuard",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = { Text("Enter link") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { onAddButtonClicked(searchQuery) },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(text = "ADD")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Enter ID to Remove:")
                    TextField(
                        value = removeId,
                        onValueChange = { removeId = it },
                        label = { Text("Screenshot ID") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { onRemoveButtonClicked(removeId.toIntOrNull() ?: 0) },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(text = "REMOVE")
                    }
                }
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        )
    }
}




