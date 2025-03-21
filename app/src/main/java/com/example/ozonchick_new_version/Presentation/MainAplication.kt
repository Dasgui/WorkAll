package com.example.ozonchick_new_version.Presentation

import android.app.Application
import android.util.Log
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.publicUserId

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
        publicUserId = Paper.book().read<UserId>("UserId") ?: UserId(0, UserType.None)
        Log.d("Id пользователя", publicUserId.toString())
    }
}