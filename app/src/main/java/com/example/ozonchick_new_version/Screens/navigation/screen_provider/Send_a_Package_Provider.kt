package com.example.ozonchick_new_version.Screens.navigation.screen_provider

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class Send_a_Package_Provider:ScreenProvider {
    object Send_a_Package:Send_a_Package_Provider()
    object Send_a_Package_Receipt:Send_a_Package_Provider()
    object Transaction:Send_a_Package_Provider()
}