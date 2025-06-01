package com.coutinho.estereofinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.coutinho.estereofinance.navigation.AppNavigation
import com.coutinho.estereofinance.ui.theme.EstereoFinanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_EstereoFinance_NoActionBar) // Força o tema limpo

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EstereoFinanceTheme {
                AppNavigation()
            }
        }
    }
}