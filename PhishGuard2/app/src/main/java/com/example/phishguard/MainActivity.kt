package com.example.phishguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.phishguard.navigation.Drawer
import com.example.phishguard.navigation.NavRoutes
import com.example.phishguard.navigation.screens.About
import com.example.phishguard.navigation.screens.Help
import com.example.phishguard.navigation.screens.Home
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }

    val closeDrawer = {
        scope.launch {
            drawerState.close()
        }
    }

    Surface(color = MaterialTheme.colors.background) {
        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        closeDrawer()
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.Home.route,
            ) {
                composable(NavRoutes.Home.route) {
                    Home(openDrawer = { openDrawer() }, navController)
                }

                composable(NavRoutes.About.route) {
                    About(openDrawer = { openDrawer() }, navController)
                }

                composable(NavRoutes.Help.route) {
                    Help(openDrawer = { openDrawer() }, navController)
                }
            }
        }
    }
}

