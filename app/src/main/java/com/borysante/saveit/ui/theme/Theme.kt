package com.borysante.saveit.ui.theme

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.borysante.saveit.ui.components.ThemedText


private val LightColorScheme = lightColorScheme(
    primary = ColorProvider.PrimaryColor,
    onPrimary = ColorProvider.OnPrimaryColor,
    primaryContainer = ColorProvider.PrimaryContainerColor,
    onPrimaryContainer = ColorProvider.TextColor,

    secondary = ColorProvider.SecondaryColor,
    onSecondary = ColorProvider.OnSecondaryColor,
    secondaryContainer = ColorProvider.SecondaryContainerColor,
    onSecondaryContainer = ColorProvider.TextColor,

    background = ColorProvider.BackgroundColor,
    onBackground = ColorProvider.OnBackgroundColor,

    surface = ColorProvider.SurfaceColor,
    onSurface = ColorProvider.OnSurfaceColor,

    error = ColorProvider.ErrorColor,
    onError = ColorProvider.OnErrorColor
)

private val DarkColorScheme = darkColorScheme(
    primary = ColorProvider.DarkPrimaryColor,
    onPrimary = ColorProvider.DarkOnPrimaryColor,
    primaryContainer = ColorProvider.DarkPrimaryContainerColor,
    onPrimaryContainer = ColorProvider.DarkTextColor,

    secondary = ColorProvider.DarkSecondaryColor,
    onSecondary = ColorProvider.DarkOnSecondaryColor,
    secondaryContainer = ColorProvider.DarkSecondaryContainerColor,
    onSecondaryContainer = ColorProvider.DarkTextColor,

    background = ColorProvider.DarkBackgroundColor,
    onBackground = ColorProvider.DarkOnBackgroundColor,

    surface = ColorProvider.DarkSurfaceColor,
    onSurface = ColorProvider.DarkOnSurfaceColor,

    error = ColorProvider.DarkErrorColor,
    onError = ColorProvider.DarkOnErrorColor
)

@Composable
fun SaveItTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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

@Preview
@Composable
fun colorsPreview() {
    val colors = DarkColorScheme
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.DarkGray)
    ) {
        ThemedText("Primary: ${colors.primary}", color = colors.primary)
        ThemedText("On Primary: ${colors.onPrimary}", color = colors.onPrimary)
        ThemedText("Background: ${colors.background}", color = colors.background)
        ThemedText("On Background: ${colors.onBackground}", color = colors.onBackground)
        ThemedText("Surface: ${colors.surface}", color = colors.surface)
        ThemedText("On Surface: ${colors.onSurface}", color = colors.onSurface)
    }
}