package com.example.ozonchick_new_version.Screens.navigation.screen_provider

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class SignUp_LogIn_Provider:ScreenProvider {
    object SignUp: SignUp_LogIn_Provider()
    object LogIn: SignUp_LogIn_Provider()
}