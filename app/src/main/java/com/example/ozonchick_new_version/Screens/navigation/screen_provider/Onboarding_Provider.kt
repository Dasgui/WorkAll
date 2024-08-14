package com.example.ozonchick_new_version.Screens.navigation.screen_provider

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class Onboarding_Provider:ScreenProvider {
    object Onboarding_1: Onboarding_Provider()
    object Onboarding_2: Onboarding_Provider()
    object Onboarding_3: Onboarding_Provider()
}
