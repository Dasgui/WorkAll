package com.example.ozonchick_new_version.Screens.navigation

import cafe.adriel.voyager.core.registry.screenModule
import com.example.ozonchick_new_version.Screens.Home.Profile_Screen
import com.example.ozonchick_new_version.Screens.Onboarding.Onboarding_Screen_1
import com.example.ozonchick_new_version.Screens.Onboarding.Onboarding_Screen_2
import com.example.ozonchick_new_version.Screens.Onboarding.Onboarding_Screen_3
import com.example.ozonchick_new_version.Screens.Send_a_Package.Send_a_Package_Receipt_Screen
import com.example.ozonchick_new_version.Screens.Send_a_Package.Send_a_Package_Screen
import com.example.ozonchick_new_version.Screens.Send_a_Package.Transaction_Screen
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.LogIn_Screen
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.SignUp_Screen
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.Home_Provider
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.Onboarding_Provider
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.Send_a_Package_Provider
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.SignUp_LogIn_Provider


val OnBoardingScreenHost = screenModule {
    register<Onboarding_Provider.Onboarding_1> { Onboarding_Screen_1() }
    register<Onboarding_Provider.Onboarding_2> { Onboarding_Screen_2() }
    register<Onboarding_Provider.Onboarding_3> { Onboarding_Screen_3() }
}
val SignUpLogInScreenHost = screenModule {
    register<SignUp_LogIn_Provider.SignUp> {SignUp_Screen()}
    register<SignUp_LogIn_Provider.LogIn> {LogIn_Screen()}
}
val HomeHost = screenModule {
    register<Home_Provider.Profile> {Profile_Screen()}
}
val Send_a_PackageHost = screenModule {
    register<Send_a_Package_Provider.Send_a_Package> {Send_a_Package_Screen()}
    register<Send_a_Package_Provider.Send_a_Package_Receipt> {Send_a_Package_Receipt_Screen()}
    register<Send_a_Package_Provider.Transaction> {Transaction_Screen()}
}

//val testhost = screenModule {
//    register<testprovider.test> {
//        tes()
//    }
//}
//
//class tes : Screen{
//    @Composable
//    override fun Content() {
//        Text(text = "gfff")
//    }
//}

/*
val OnBoardingScreenHost = screenModule {
    register<Onboardingscreen.Onboarding1> { OnboardingScreen1() }
    register<Onboardingscreen.Onboarding2> { OnBoardingScreen2() }
    register<Onboardingscreen.Onboarding3> { OnBoardingScreen3() }
}*/
