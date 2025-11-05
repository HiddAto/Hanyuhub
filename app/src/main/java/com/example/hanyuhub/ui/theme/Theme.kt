package com.example.hanyuhub.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

private val LightColors = lightColorScheme(
    primary = Color(0xFF721313),
    onPrimary = Color.White,
    secondary = Color(0xFF34C759),
    onSecondary = Black,
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0B0B0B),
    surface = Color.White,
    onSurface = Color(0xFF0B0B0B)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF6CB6FF),
    onPrimary = Black,
    secondary = Color(0xFF30D158),
    onSecondary = Black
)

data class AppColors(val brand: Color, val onBrand: Color)
private val LightApp = AppColors(brand = Color(0xFF0A84FF), onBrand = Color.White)
private val DarkApp  = AppColors(brand = Color(0xFF6CB6FF), onBrand = Black)
private val LocalAppColors = staticCompositionLocalOf { LightApp }

/* No se usó
object AppTheme {
    val colors: AppColors @Composable get() = LocalAppColors.current
}
*/


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

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    // label y supportingText los dejamos como composables para máxima flexibilidad
    label: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    singleLine: Boolean = true,
    isPassword: Boolean = false,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
    keyboardType: KeyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Black,
        unfocusedTextColor = Black.copy(alpha = 0.9f),
        errorTextColor = Color.Red,
        cursorColor = MaterialTheme.colorScheme.primary,
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = Black.copy(alpha = 0.5f),
        errorBorderColor = MaterialTheme.colorScheme.error
    )
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = label,
        supportingText = supportingText,
        placeholder = placeholder,
        singleLine = singleLine,
        isError = isError,
        enabled = enabled,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        colors = colors
    )
}