package com.example.phishguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.phishguard.navigation.Drawer
import com.example.phishguard.navigation.NavRoutes
import com.example.phishguard.navigation.screens.About
import com.example.phishguard.navigation.screens.Help
import com.example.phishguard.navigation.screens.Home
import com.example.phishguard.navigation.screens.List
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            MainScreen(viewModel)
        }
    }
}

@Composable
fun <T> LiveData<T>.observeNullableAsStateWithLifecycle(lifecycleOwner: LifecycleOwner): State<T?> {
    val state: MutableState<T?> = remember { mutableStateOf(value) }

    DisposableEffect(this, lifecycleOwner) {
        val observer = Observer<T> { newValue ->
            state.value = newValue
        }
        observe(lifecycleOwner, observer)

        onDispose {
            removeObserver(observer)
        }
    }

    return state
}



@Composable
fun MainScreen(viewModel: MainViewModel, lifecycleOwner: LifecycleOwner) {
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

    // Observe the LiveData value in the ViewModel
    val isDrawerOpen by viewModel.isDrawerOpen.observeNullableAsStateWithLifecycle(lifecycleOwner)

    // Update the ViewModel when the drawer state changes
    LaunchedEffect(drawerState, isDrawerOpen) {
        if (drawerState.isOpen != isDrawerOpen) {
            if (drawerState.isOpen) {
                viewModel.openDrawer()
            } else {
                viewModel.closeDrawer()
            }
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
                    Home(
                        openDrawer = { openDrawer() },
                        navController = navController,
                        onAddButtonClicked = {
                            // Implement Action
                        },
                        onRemoveButtonClicked = {
                            // Implement Action
                        }
                    )
                }
                composable(NavRoutes.About.route) {
                    About(openDrawer = { openDrawer() }, navController)
                }
                composable(NavRoutes.List.route) {
                    List(openDrawer = { openDrawer() }, navController)
                }
                composable(NavRoutes.Help.route) {
                    Help(openDrawer = { openDrawer() }, navController)
                }
            }
        }
    }
}





