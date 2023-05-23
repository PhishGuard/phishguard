package com.example.phishguard.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.ui.graphics.vector.ImageVector

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = "home"
        ),
        BarItem(
            title = "List",
            image = Icons.Filled.List,
            route = "list"
        ),
        BarItem(
            title = "About",
            image = Icons.Filled.Settings,
            route = "about"
        ),
        BarItem(
            title = "Help",
            image = Icons.Filled.Info,
            route = "help"
        )
    )
}
