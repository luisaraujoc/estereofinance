package com.coutinho.estereofinance.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Maize,
    onPrimary = Night,
    secondary = Pumpkin,
    onSecondary = White,
    tertiary = Vermilion,
    onTertiary = White,
    background = Night,
    onBackground = GhostWhite,
    surface = FrenchGray,
    onSurface = White
)

private val LightColorScheme = lightColorScheme(
    primary = Pumpkin,
    onPrimary = White,
    secondary = Xanthous,
    onSecondary = Night,
    tertiary = Vermilion,
    onTertiary = White,
    background = GhostWhite,
    onBackground = Night,
    surface = White,
    onSurface = Night
)

@Composable
fun EstereoFinanceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
