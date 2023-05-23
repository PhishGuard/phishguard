package com.example.phishguard.navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.phishguard.navigation.BottomNavigationBar
import com.example.phishguard.navigation.TopBar

@Composable
fun List(openDrawer: () -> Unit, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopBar(
                    title = "List",
                    buttonIcon = Icons.Filled.Menu,
                    onButtonClicked = { openDrawer() }) },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { Text(text = "This is a list of the screenshots") } },
            bottomBar = {
                BottomNavigationBar(
                    navController = navController)
            }
        )
    }
}