package com.example.phishguard.navigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.phishguard.MainViewModel
import com.example.phishguard.navigation.BottomNavigationBar
import com.example.phishguard.navigation.NavRoutes
import com.example.phishguard.navigation.TopBar

@Composable
fun About(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopBar(
                title = "About",
                buttonIcon = Icons.Filled.Settings,
                onButtonClicked = {}
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            item {
                Text(
                    text = "PhishGuard",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(all = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "PhishGuard is an Android Kotlin app designed to efficiently manage screenshots of login pages obtained from our backend. These screenshots are scraped from various websites and used for phishing landing page templates, primarily for testing purposes. The app allows users to view, organize, and comment on these screenshots, which are stored in a database.",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(all = 8.dp)
                )
                Text(
                    text = "Features",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(all = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Screenshot Management:",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(all = 8.dp)
                )
                Text(
                    text = "PhishManager provides a user-friendly interface to view and manage the collection of screenshots obtained from the backend. Users can easily browse through the screenshots and access their details.",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(all = 8.dp)
                )
                // Add other features with appropriate styles and padding

                Text(
                    text = "Team",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(all = 8.dp),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "PhishManager is developed by a dedicated team with expertise in their respective roles:",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(all = 8.dp),
                )
                Text(
                    text = "Leon Walder - Database & App GUI:",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 8.dp)
                )
                Text(
                    text = "Leon is responsible for creating the user interface (UI) and the frontend of the PhishGuard app. He also focuses on developing the database.",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(all = 8.dp)
                )
                Text(
                    text = "Fabian Huber - Backend & App GUI:",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(all = 4.dp)
                )
                Text(
                    text = "Fabian is responsible for connecting the backend system to the PhishGuard app. He is also working for the user interface (UI).",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(all = 8.dp)
                )
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

