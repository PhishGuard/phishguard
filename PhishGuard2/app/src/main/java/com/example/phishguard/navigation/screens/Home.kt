package com.example.phishguard.navigation.screens
import com.example.phishguard.navigation.network.backendService
import com.example.phishguard.navigation.network.SubmitDataRequest



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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Home(
    openDrawer: () -> Unit,
    navController: NavHostController,
    onAddButtonClicked: (String) -> Unit,
    onRemoveButtonClicked: (Int) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var removeId by remember { mutableStateOf("") }
    data class SubmitDataRequest(
        val link: String,
        val screenshotId: Int
    )

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
                        onClick = {
                            // Create an instance of the data you want to send to the backend
                            val request = SubmitDataRequest(searchQuery, removeId.toIntOrNull() ?: 0)// Replace `Data` with your actual data class

                            // Make the backend call using the Retrofit service
                            backendService.submitData(request).enqueue(object : Callback<Void> {
                                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                    // Handle the response from the backend
                                    if (response.isSuccessful) {
                                        // Success
                                    } else {
                                        // Handle error
                                    }
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    // Handle network or other errors
                                }
                            })
                        },
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




