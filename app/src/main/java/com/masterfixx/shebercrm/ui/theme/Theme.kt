package com.masterfixx.shebercrm.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = TealPrimary,
    primaryContainer = TealPrimaryContainer,
    secondary = OrangeSecondary,
    secondaryContainer = OrangeSecondaryContainer,
    background = BackgroundLight,
    surface = SurfaceLight,
    onBackground = OnLight,
    onSurface = OnLight
)

private val DarkColors = darkColorScheme(
    primary = TealPrimaryDark,
    primaryContainer = TealPrimaryContainerDark,
    secondary = OrangeSecondaryDark,
    secondaryContainer = OrangeSecondaryContainerDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    onBackground = OnDark,
    onSurface = OnDark
)

@Composable
fun SheberCRMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
