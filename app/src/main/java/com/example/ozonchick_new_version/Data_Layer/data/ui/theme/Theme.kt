package com.example.ozonchick_new_version.Data_Layer.data.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.ozonchick_new_version.Data_Layer.data.db.isDark

private val DarkColorScheme = darkColorScheme(
    primary = BackgroundTop_Color_DarkMode,
    secondary = Gray_Color_Passive_Secondary,
    tertiary = Pink80,
    background = Background_Color_DarkMode,
    onPrimary = Text_Color_Active_DarkMode,
    onSecondaryContainer = Gray_Color_Active_SideText,
    primaryContainer = BackgroundTop_Color_DarkMode,
    onSecondary =Color.White,
    tertiaryContainer = BackgroundTop_Color_DarkMode,
    onTertiaryContainer = Primary_Color
)

private val LightColorScheme = lightColorScheme(
    primary = Color_Background.value,
    secondary = Gray_Color_Passive_Secondary,
    tertiary = Pink40,
    background = Color_Background.value,
    onPrimary = Text_Color_Active,
    onSecondaryContainer = Gray_Color_Active_SideText,
    primaryContainer = Color.White,
    onSecondary =Gray_Color_Passive_Secondary,
    tertiaryContainer = Primary_Color,
    onTertiaryContainer = Primary_Color




    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Ozonchick_new_versionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}