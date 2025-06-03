package com.coutinho.estereofinance.ui.screens.home

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coutinho.estereofinance.data.utils.SessionManager
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home Screen",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            scope.launch {
                SessionManager(context).clearSession()
                onLogoutClick()
            }
        }) {
            Text(text = "Logout")
        }
    }
}
