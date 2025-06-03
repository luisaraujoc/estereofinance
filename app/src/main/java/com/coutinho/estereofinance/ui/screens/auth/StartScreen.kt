package com.coutinho.estereofinance.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.coutinho.estereofinance.data.utils.SessionManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun StartScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        val userId = SessionManager(context).getUserId().first()
        if (userId != null) {
            navController.navigate("home") {
                popUpTo("start") { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo("start") { inclusive = true }
            }
        }
        isLoading = false
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
