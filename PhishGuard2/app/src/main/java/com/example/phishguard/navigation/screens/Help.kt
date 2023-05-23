package com.example.phishguard.navigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalUriHandler
import com.example.phishguard.navigation.BottomNavigationBar
import com.example.phishguard.navigation.TopBar
import kotlinx.coroutines.launch

@Composable
fun Help(openDrawer: () -> Unit, navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val uriHandler = LocalUriHandler.current

    var showLinkClicked by remember { mutableStateOf(false) }

    val handleClick = {
        scope.launch {
            val url = "https://github.com/PhishGuard/phishguard/"
            uriHandler.openUri(url)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopBar(
                    title = "Help",
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
                    Text(text = "Do you need help? Click the button below")

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { handleClick() },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(text = "Help")
                    }

                    if (showLinkClicked) {
                        Text(text = "Link clicked!")
                    }
                }
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        )
    }
}


