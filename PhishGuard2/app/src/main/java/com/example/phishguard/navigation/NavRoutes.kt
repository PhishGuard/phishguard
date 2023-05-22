package com.example.phishguard.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object About : NavRoutes("about")
    object Help : NavRoutes("help")
}