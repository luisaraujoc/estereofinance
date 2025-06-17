// app/src/main/java/com/coutinho/estereofinance/MainActivity.kt

package com.coutinho.estereofinance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.coutinho.estereofinance.navigation.AppNavigation // Importe o AppNavigation
import com.coutinho.estereofinance.ui.theme.EstereoFinanceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EstereoFinanceTheme {
                // Uma superfície container usando a cor 'background' do tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation() // Chama o composable de navegação principal
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EstereoFinanceTheme {
        AppNavigation() // Preview do grafo de navegação
    }
}