package com.example.ozonchick_new_version.Screens

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.ozonchick_new_version.Data_Layer.data.db.userAuthInformation
import com.example.ozonchick_new_version.Data_Layer.data.db.users
import com.example.ozonchick_new_version.Screens.navigation.HomeHost
import com.example.ozonchick_new_version.Screens.navigation.OnBoardingScreenHost
import com.example.ozonchick_new_version.Screens.navigation.Send_a_PackageHost
import com.example.ozonchick_new_version.Screens.navigation.SignUpLogInScreenHost
import io.paperdb.Paper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainAplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MainAplication)
            modules(AppModule)
        }
        ScreenRegistry{
            OnBoardingScreenHost()
            SignUpLogInScreenHost()
            HomeHost()
            Send_a_PackageHost()
        }
        Paper.init(this)

        users = Paper.book().read("Users",ArrayList()) ?: ArrayList()
        userAuthInformation = Paper.book().read("User", ArrayList()) ?: ArrayList()

    }
}