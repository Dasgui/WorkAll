package com.example.ozonchick_new_version.Presentation

import android.app.Application
import android.util.Log
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.ozonchick_new_version.Data.data.db.publicUserId

import io.paperdb.Paper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainAplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MainAplication)
            modules(AppModule)
        }
        ScreenRegistry{
/*            OnBoardingScreenHost()
            SignUpLogInScreenHost()
            HomeHost()
            Send_a_PackageHost()*/
        }
        Paper.init(this)
        Paper.book().delete("Users")
//        users = Paper.book().read("Users",ArrayList()) ?: ArrayList()
//        userAuthInformation = Paper.book().read("User", ArrayList()) ?: ArrayList()
        publicUserId = Paper.book().read<Int>("UserId") ?: 0
        Log.d("Id пользователя", publicUserId.toString())
    }
}