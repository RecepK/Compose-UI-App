package com.kurban.app.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kurban.app.MainViewModel
import com.kurban.app.ui.screen.MainScreen
import com.kurban.app.ui.screen.SplashScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Destination.SPLASH_SCREEN.root
    ) {
        composable(route = Destination.SPLASH_SCREEN.root) {
            SplashScreen(
                viewModel = viewModel,
                navigate = {
                    navController.navigate(Destination.MAIN_SCREEN.root)
                })
        }
        composable(route = Destination.MAIN_SCREEN.root) {
            MainScreen(
                viewModel = viewModel,
                navigate = {
                    navController.popBackStack()
                })
        }
    }
}