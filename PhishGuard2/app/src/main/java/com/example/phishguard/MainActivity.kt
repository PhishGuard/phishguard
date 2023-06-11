package com.example.phishguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.phishguard.navigation.NavRoutes
import com.example.phishguard.navigation.screens.About
import com.example.phishguard.navigation.screens.Login
import com.example.phishguard.navigation.screens.Help
import com.example.phishguard.navigation.screens.Home
import com.example.phishguard.navigation.screens.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.phishguard.ui.theme.PhishGuardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhishGuardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val owner = LocalViewModelStoreOwner.current
                    owner?.let {
                        val viewModel: MainViewModel = viewModel(
                            it,
                            "MainViewModel",
                        )
                        ScreenSetup(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: MainViewModel) {
    val uiState = viewModel.phishGuardUiState

    MainScreen(
        viewModel = viewModel,
        uiState = uiState
    )
}


@Composable
fun MainScreen(
    viewModel: MainViewModel,
    uiState: PhishGuardUiState
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Login.route,
    ) {
        composable(NavRoutes.Login.route) {
            Login(navController = navController, viewModel = viewModel)
        }
        composable(NavRoutes.Home.route) {
            Home(navController = navController, viewModel = viewModel)
        }
        composable(NavRoutes.About.route) {
            About(navController = navController)        }
        composable(NavRoutes.List.route) {
            List(navController = navController, viewModel = viewModel)        }
        composable(NavRoutes.Help.route) {
            Help(navController = navController)
        }
    }
}







