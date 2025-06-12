package com.example.ozonchick_new_version.Presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Presentation.Screens.ChangeInformation.ChangeInformationScreen
import com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy.EmployerAddVacancyScreen
import com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy.EmployerAddVacancyViewModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy.EmployerDetailsVacancyScreen
import com.example.ozonchick_new_version.Presentation.Screens.EmployerResponses.EmployerResponsesScreen
import com.example.ozonchick_new_version.Presentation.Screens.ListVacancies.ListVacanciesScreen
import com.example.ozonchick_new_version.Presentation.Screens.LogIn.LogInScreen
import com.example.ozonchick_new_version.Presentation.Screens.Profile.ProfileScreen
import com.example.ozonchick_new_version.Presentation.Screens.Resume.ResumeScreen
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.SignUpLogIn.SignUp.SignUpScreen

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Splash(
            onNavigateForLogin = {
                navigator?.replaceAll(LogInScreen())
            },
            onNavigateForHome = {
                navigator?.replaceAll(ProfileScreen())
//                navigator?.replaceAll(Settings_Screen())
            }
        )
    }
}

@Composable
fun Splash(onNavigateForLogin: () -> Unit, onNavigateForHome: () -> Unit) {

    LaunchedEffect(key1 = true) {
        if (localUserInformation.id != 0 && localUserInformation.type !is UserType.None) {
            onNavigateForHome()
        } else {
            onNavigateForLogin()
        }

    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.workall_logo),
            contentDescription = "Лого",
            modifier = Modifier.fillMaxSize(0.7f)
        )
    }
}