package com.example.phishguard.navigation.screens

sealed class Screens(val title: String, val route: String) {
    object Login : Screens("Login", "login")
    object Home : Screens("Home", "home")
    object About : Screens("About", "about")
    object Help : Screens( "Help", "help")
    object List : Screens( "List", "list")
}

val screens = listOf(
    Screens.Login,
    Screens.Home,
    Screens.About,
    Screens.Help,
    Screens.List,
)

