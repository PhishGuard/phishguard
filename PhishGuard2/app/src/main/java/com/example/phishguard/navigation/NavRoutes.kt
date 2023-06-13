package com.example.phishguard.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object List : NavRoutes("list")
    object About : NavRoutes("about")
    object Help : NavRoutes("help")
}