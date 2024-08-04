package com.kurban.app.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kurban.app.ui.screen.MainScreen
import com.kurban.app.ui.screen.SplashScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.SPLASH_SCREEN.root
    ) {
        composable(route = Destination.SPLASH_SCREEN.root) {
            SplashScreen(navigate = {
                navController.navigate(Destination.MAIN_SCREEN.root)
            })
        }
        composable(route = Destination.MAIN_SCREEN.root) {
            MainScreen(navigate = {
                navController.navigate(Destination.SPLASH_SCREEN.root)
            })
        }
    }
}