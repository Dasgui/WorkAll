package com.example.ozonchick_new_version.Presentation.Screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor

class Settings_Screen() : Screen {
    @Composable
    override fun Content() {
        Surface(
            color = BackgroundColor,
            modifier = Modifier.Companion.fillMaxSize()
        ) {
//            Settings_Screen_construct()
        }
    }
}