package com.example.phishguard.navigation.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.phishguard.MainViewModel
import com.example.phishguard.navigation.BottomNavigationBar
import com.example.phishguard.navigation.NavRoutes
import com.example.phishguard.navigation.TopBar


@Composable
fun Home(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    var searchQuery by remember { mutableStateOf("") }
    var removeId by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val onSearchQueryChange = { text: String ->
        searchQuery = text
    }

    val onIdChange = { text: String ->
        removeId = text
    }

    val descriptionChange = { text: String ->
        description = text
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopBar(
                    title = "Home",
                    buttonIcon = Icons.Filled.List,
                    onButtonClicked = {}
                )
            },
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    CustomTextField(
                        title = "Website Link",
                        textState = searchQuery,
                        onTextChange = onSearchQueryChange,
                        keyboardType = KeyboardType.Text
                    )

                    CustomTextField(
                        title = "Screenshot ID",
                        textState = removeId,
                        onTextChange = onIdChange,
                        keyboardType = KeyboardType.Number
                    )

                    CustomTextField(
                        title = "Description",
                        textState = description,
                        onTextChange = descriptionChange,
                        keyboardType = KeyboardType.Text
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Button(onClick = {
                            if (searchQuery.isNotEmpty() && removeId.isNotEmpty()) {
                                viewModel.newScreenshot(
                                    screenshotName = searchQuery,
                                    id = removeId,
                                    description = description
                                )
                                navController.navigate(NavRoutes.Home.route)
                            }
                        }) {
                            Text("Add Phishing Page")
                        }
                        Button(onClick = {
                            if (removeId.isNotEmpty()) {
                                viewModel.deleteScreenshot(
                                    id = removeId
                                )
                                navController.navigate(NavRoutes.Home.route)
                            }
                        }) {
                            Text("Delete Phishing Page")
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



