package com.example.ozonchick_new_version.Presentation

import android.app.Application
import android.util.Log
import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.model.UserMainInformation
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.network.client

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
//        Paper.book().delete("UserId")
        publicUserId = Paper.book().read<UserId>("UserId") ?: UserId(0, UserType.None)
        localUserInformation = Paper.book().read<UserMainInformation>("UserLocalInformation") ?: UserMainInformation("",
            UserType.None, "", 0)
        Log.d("Информация о пользователе", localUserInformation.toString())
    }
}