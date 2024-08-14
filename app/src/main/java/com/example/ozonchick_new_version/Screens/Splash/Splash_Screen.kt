package com.example.ozonchick_new_version.Screens.Splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.db.userAuthInformation
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.Home.Profile_Screen
import com.example.ozonchick_new_version.Screens.Home.Spisok_Predlogenii_Screen
import com.example.ozonchick_new_version.Screens.Onboarding.Onboarding_Screen_1
import com.example.ozonchick_new_version.Screens.Onboarding.Onboarding_Screen_2
import com.example.ozonchick_new_version.Screens.Send_a_Package.Send_a_Package_Receipt_Screen
import com.example.ozonchick_new_version.Screens.Send_a_Package.Send_a_Package_Screen
import com.example.ozonchick_new_version.Screens.Send_a_Package.Transaction_Screen
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.LogIn_Screen
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.SignUp_Screen
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.SignUp_LogIn_Provider
import io.github.jan.supabase.gotrue.SessionSource
import io.paperdb.Paper
import kotlinx.coroutines.delay

class Splash_Screen : Screen {
    @Composable
    override fun Content() {
        Splash()
    }
}

@Composable
fun Splash() {
    val navigator = LocalNavigator.current

    LaunchedEffect(key1 = true) {
//        Paper.book().delete("User")
        delay(600)
        if(userAuthInformation.isNotEmpty())
        {
            navigator?.replaceAll(Spisok_Predlogenii_Screen())
        }
        else
        {
            navigator?.replaceAll(LogIn_Screen())
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