package com.coutinho.estereofinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.coutinho.estereofinance.navigation.AppNavigation
import com.coutinho.estereofinance.ui.theme.EstereoFinanceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Adiciona suporte à injeção de dependência do Hilt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        
        setTheme(R.style.Theme_EstereoFinance_NoActionBar)
        
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EstereoFinanceTheme {
                AppNavigation()
            }
        }
    }
}