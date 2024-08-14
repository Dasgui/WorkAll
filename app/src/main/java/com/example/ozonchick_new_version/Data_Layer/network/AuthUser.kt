package com.example.ozonchick_new_version.Data_Layer.network

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.example.ozonchick_new_version.Data_Layer.data.db.accessSharedAuth
import com.example.ozonchick_new_version.Data_Layer.data.db.userAuthInformation
import com.example.ozonchick_new_version.Data_Layer.model.DTOMessage
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.UserAuth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.SessionSource
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.paperdb.Paper
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Класс предназанчаен для регистрации пользователя
 * 24.03.2024
 *@param AuthEmail авторизация пользователя
 * @author Matvei
 *
 * */

class AuthUser() {

    suspend fun AuthEmail(mail:String, passwordUser : String, rememberUser : Boolean) : DTOMessage {
        var Info = CompletableDeferred<DTOMessage>()
        try {
            CoroutineScope(Dispatchers.IO).launch {
                supabase.auth.signInWith(Email) {
                    email = mail
                    password = passwordUser
                }
                supabase.auth.sessionStatus.collect {
                    when (it) {
                        is SessionStatus.Authenticated -> {
                            println("Received new authenticated session.")
                            when (it.source) { //Check the source of the session
                                SessionSource.External -> Log.d("Сесия", "External")
                                is SessionSource.Refresh -> Log.d("Сесия", "Refresh")
                                is SessionSource.SignIn -> {
                                    Log.d("Сесия", "Зашёл")
//                                    accessSharedAuth.value = true
                                    if (rememberUser)
                                    {
                                        userAuthInformation.add(UserAuth(mail,passwordUser))
                                        Paper.book().write("User", userAuthInformation)
                                        Info.complete(DTOMessage(true,"Успешная авторизация"))
                                    }
                                    else
                                    {
                                        Info.complete(DTOMessage(true,"Успешная авторизация"))
                                    }
/*                                    val toast = Toast.makeText(contextAuth,"Успешеый вход", Toast.LENGTH_LONG)
                                    toast.setGravity(Gravity.FILL_VERTICAL,0,0)
                                    toast.show()*/
//                                    Log.d("Сесия", "Зашёл")
//                                emailUser.value = user.email.toString()
//                                /*Log.d("Имя",emailUser.value)*/
//                                ITSOKAY.value = true
//                                    Info.complete(DTOMessage(true,"Успешная авторизация"))
                                }

                                is SessionSource.SignUp -> {
                                    /*Info.complete(DTOMessage(true,"Успешное авторизация"))*/
                                }
                                SessionSource.Storage -> Log.d("Сесия", "UserIdentitiesChanged")
                                SessionSource.Unknown -> Log.d("Сесия", "UserIdentitiesChanged")
                                is SessionSource.UserChanged -> Log.d("Сесия", "UserIdentitiesChanged")
                                is SessionSource.UserIdentitiesChanged -> Log.d("Сесия", "UserIdentitiesChanged")
                            }
                        }

                        SessionStatus.LoadingFromStorage -> println("Loading from storage")
                        SessionStatus.NetworkError ->  Info.complete(DTOMessage(true,"Нет соединение с сервером"))
                        is SessionStatus.NotAuthenticated -> {
                            if (it.isSignOut) {
                                Info.complete(DTOMessage(true,"Не получилось авторизоваться"))
                            } else {
                                Info.complete(DTOMessage(true,"Не получилось авторизоваться"))
                            }
                        }

                    }
                }
            }
        }
        catch (e : Exception){
            Info.complete( DTOMessage(false,"Не корректные данные"))
        }
        return Info.await()
    }



    fun AuthGoogle(){

    }

}
