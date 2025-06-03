package com.coutinho.estereofinance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coutinho.estereofinance.ui.screens.auth.LoginScreen
import com.coutinho.estereofinance.ui.screens.auth.RegisterScreen
import com.coutinho.estereofinance.ui.screens.auth.StartScreen
import com.coutinho.estereofinance.ui.screens.home.HomeScreen

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Start.route
    ) {
        composable(Screen.Start.route) {
            StartScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(
                onSignUpClick = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("home") { popUpTo("login") { inclusive = true } } }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onLoginClick = { navController.popBackStack() }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onLogoutClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
