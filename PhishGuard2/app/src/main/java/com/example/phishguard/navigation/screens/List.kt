package com.example.phishguard.navigation.screens

import Screenshot
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.phishguard.MainViewModel
import com.example.phishguard.PhishGuardUiState
import com.example.phishguard.navigation.BottomNavigationBar
import com.example.phishguard.navigation.NavRoutes
import com.example.phishguard.navigation.TopBar

@Composable
fun List(navController: NavHostController, viewModel: MainViewModel) {
    val uiState = viewModel.phishGuardUiState

    val screenshots: List<Screenshot> = when (uiState) {
        is PhishGuardUiState.Success -> uiState.screenshots.value ?: emptyList()
        else -> emptyList()
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
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Screenshot List",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(all = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = {
                        viewModel.getAllScreenshots()
                    }) {
                        Text("Show Phishing Page")
                    }

                    LazyColumn(modifier = Modifier.weight(1f)) {
                        itemsIndexed(screenshots) { _, screenshot ->
                            val imageBitmap = screenshot.imageBitmap
                            Box(modifier = Modifier.height(200.dp)) {
                                Image(
                                    bitmap = imageBitmap,
                                    contentDescription = "Screenshot Image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        )
    }
}