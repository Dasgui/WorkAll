package com.example.ozonchick_new_version.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Common.isLocalVersion
import com.example.ozonchick_new_version.Presentation.Screens.LogIn.LogInScreen
import com.example.ozonchick_new_version.Presentation.ui.components.CustomButton
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor

class VersionScreen : Screen {
    @Composable
    override fun Content() {
        VersionConstruct()
    }
}

@Composable
fun VersionConstruct(modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.current
    LazyColumn (
        modifier = Modifier.fillMaxSize().background(
            BackgroundColor
        )
    ) {
        item {
            Column (
                modifier = Modifier.padding(top = 400.dp, bottom = 80.dp, start = 15.dp, end = 15.dp).fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomButton(
                    onClick = {
                        isLocalVersion = true
                        navigator?.replaceAll(LogInScreen())
                    },
                    placeText = "Локальная версия"
                )
                CustomButton(
                    onClick = {
                        navigator?.replaceAll(LogInScreen())
                    },
                    placeText = "Версия с интернетом"
                )
            }
        }
    }
}