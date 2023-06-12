package com.example.phishguard.navigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.phishguard.MainViewModel
import com.example.phishguard.navigation.BottomNavigationBar
import com.example.phishguard.navigation.NavRoutes
import com.example.phishguard.navigation.TopBar

@Composable
fun List(navController: NavHostController, viewModel: MainViewModel) {
    var removeId by remember { mutableStateOf("") }
    val onIdChange = { text: String ->
        removeId = text
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopBar(
                    title = "List",
                    buttonIcon = Icons.Filled.List,
                    onButtonClicked = {}
                )
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Screenshot List")
                    CustomTextField(title = "Screenshot ID", textState = removeId, onTextChange = onIdChange, keyboardType = KeyboardType.Number)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        if (removeId.isNotEmpty()) {
                            viewModel.getScreenshot(
                                id = removeId
                            )
                            navController.navigate(NavRoutes.Home.route)
                        }
                    }) {
                        Text("Find Phishing Page")
                    }
                }
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        )
    }
}