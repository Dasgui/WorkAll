package com.example.ozonchick_new_version.Screens.Splash

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
import com.example.ozonchick_new_version.Data_Layer.data.db.userAuthInformation
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.SpisokPredlogenii.Spisok_Predlogenii_Screen
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.LogIn_Screen

class Splash_Screen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Splash(
            onNavigateForLogin = {
                navigator?.replaceAll(LogIn_Screen())
            },
            onNavigateForPredlogenii = {
                navigator?.replaceAll(Spisok_Predlogenii_Screen())
            }
        )
    }
}

@Composable
fun Splash(onNavigateForLogin: () -> Unit, onNavigateForPredlogenii: () -> Unit) {
    val navigator = LocalNavigator.current

    LaunchedEffect(key1 = true) {
//        Paper.book().delete("User")
        if (userAuthInformation.isNotEmpty()) {
            onNavigateForPredlogenii()
            /*navigator?.replaceAll(Spisok_Predlogenii_Screen())*/
        } else {
            onNavigateForLogin()
            /*navigator?.replaceAll(LogIn_Screen())*/
        }

    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.pro_dobro_logo),
            contentDescription = "Лого",
            modifier = Modifier.fillMaxSize(0.7f)
        )
    }
}