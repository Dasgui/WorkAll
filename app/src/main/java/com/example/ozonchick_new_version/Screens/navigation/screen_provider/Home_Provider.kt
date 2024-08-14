package com.example.ozonchick_new_version.Screens.navigation.screen_provider

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class Home_Provider:ScreenProvider {
    object Profile:Home_Provider()
}