package com.coutinho.estereofinance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coutinho.estereofinance.ui.screens.auth.LoginScreen
import com.coutinho.estereofinance.ui.screens.auth.RegisterScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    // futuramente: object Home : Screen("home") etc.
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                onSignUpClick = { navController.navigate(Screen.Register.route) }
            )
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(
                onLoginClick = { navController.popBackStack() }
            )
        }
    }
}
