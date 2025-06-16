package com.example.ozonchick_new_version.Presentation.States

sealed interface ColorThemeStates {
    object BlueTheme : ColorThemeStates
    object DarkTheme : ColorThemeStates
    object GreenTheme : ColorThemeStates
}