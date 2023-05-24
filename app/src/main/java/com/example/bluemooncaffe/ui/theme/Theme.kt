package com.example.bluemooncaffe.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = primary,
    secondary = secondary,
    background = background,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    secondaryVariant = secondaryVariant,
    primaryVariant = primaryVariant,
    surface = surface,
    onBackground = onBackground,
    onSurface = onSurface
)

private val LightColorPalette = lightColors(
    primary= lightPrimary,
    secondary = lightSecondary,
    background = lightBackground,
    error = lightError,
    onPrimary = lightOnPrimary,
    onSecondary = lightOnSecondary,
    secondaryVariant = lightSecondaryVariant,
    primaryVariant = lightPrimaryVariant,
    surface = lightSurface,
    onBackground = lightOnBackground,
    onSurface = lightOnSurface
)

@Composable
fun BlueMoonCaffeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}