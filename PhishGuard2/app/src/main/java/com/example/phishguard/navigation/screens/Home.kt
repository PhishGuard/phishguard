package com.example.phishguard.navigation.screens

import androidx.compose.foundation.layout.*
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
import com.example.phishguard.navigation.TopBar

@Composable
fun Home(openDrawer: () -> Unit, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopBar(
                    title = "Home",
                    buttonIcon = Icons.Filled.Menu,
                    onButtonClicked = { openDrawer() }) },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { Text(text = "Welcome to PhishGuard") } },
            bottomBar = {
                BottomNavigationBar(
                    navController = navController)}
        )
    }
}