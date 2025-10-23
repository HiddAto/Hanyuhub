package com.example.hanyuhub.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF9C27B0),
    onPrimary = Color.White,
    secondary = Color(0xFF34C759),
    onSecondary = Color.Black,
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0B0B0B),
    surface = Color.White,
    onSurface = Color(0xFF0B0B0B)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF6CB6FF),
    onPrimary = Color.Black,
    secondary = Color(0xFF30D158),
    onSecondary = Color.Black
)

data class AppColors(val brand: Color, val onBrand: Color)
private val LightApp = AppColors(brand = Color(0xFF0A84FF), onBrand = Color.White)
private val DarkApp  = AppColors(brand = Color(0xFF6CB6FF), onBrand = Color.Black)
private val LocalAppColors = staticCompositionLocalOf { LightApp }

object AppTheme {
    val colors: AppColors @Composable get() = LocalAppColors.current
}

@Composable
fun HanyuHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val scheme = if (darkTheme) DarkColors else LightColors
    val extended = if (darkTheme) DarkApp else LightApp

    CompositionLocalProvider(LocalAppColors provides extended) {
        MaterialTheme(
            colorScheme = scheme,
            content = content
        )
    }
}