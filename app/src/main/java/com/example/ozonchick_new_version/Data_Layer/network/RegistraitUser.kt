package com.example.ozonchick_new_version.Data_Layer.network

import android.util.Log
import com.example.ozonchick_new_version.Data_Layer.data.variables.secondType_OfRegistration
import com.example.ozonchick_new_version.Data_Layer.model.DTOMessage
import com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model.Main_details
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.Employer_information_registrait
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.User_balance
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.User_information_registrait
import io.github.jan.supabase.gotrue.SessionSource
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class RegistraitUser() {
/*    private val supabase = createSupabaseClient(
        supabaseUrl = "https://qxniznftwaxnvnksitua.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InF4bml6bmZ0d2F4bnZua3NpdHVhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDg4NDc1NTAsImV4cCI6MjAyNDQyMzU1MH0.7X_dAg3PvNrCsl14_N8z8YCfd0pxKWmV_dbNKczm_Lc"
    ) {
        install(Auth)
        install(Postgrest)
    }*/
    suspend fun RegisstraitEmail(name:String,phoneNumber:String,mail:String, passwordUser : String, userStatus:String, INN:String) : DTOMessage
    {
        var Info = CompletableDeferred<DTOMessage>()
        try {
            CoroutineScope(Dispatchers.IO).launch {
                supabase.auth.signUpWith(provider = Email)
                {
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
/*                                    Log.d("Сесия", "Зашёл")
                                    Info.complete(DTOMessage(true,"Успешная авторизация"))*/
                                }

                                is SessionSource.SignUp -> {
                                    when (userStatus) {
                                        "Соискатель" -> {
                                            val otvet = supabase.from("user_information").insert(User_information_registrait(name,phoneNumber,mail))
                                            {
                                                select(Columns.list("user_id"))
                                            }.decodeSingle<Main_details>()

                                        }
                                        "Работодатель" -> {
                                            supabase.from("employers_information").insert(Employer_information_registrait(name,phoneNumber,mail, INN))
                                        }
                                        "Администратор" -> {

                                        }
                                    }

//                                    supabase.from("user_balance").insert(User_balance(otvet.user_id,0))
                                    Info.complete(DTOMessage(true, "Успешная регистрация"))
                                }
                                SessionSource.Storage -> Log.d("Сесия", "Storage")
                                SessionSource.Unknown -> Log.d("Сесия", "Unknown")
                                is SessionSource.UserChanged -> Log.d("Сесия", "UserChanged")
                                is SessionSource.UserIdentitiesChanged -> Log.d("Сесия", "UserIdentitiesChanged")
                            }
                        }

                        SessionStatus.LoadingFromStorage -> println("Loading from storage")
                        SessionStatus.NetworkError ->  Info.complete(DTOMessage(true,"Нет соединение с сервером"))
                        is SessionStatus.NotAuthenticated -> {
                            if (it.isSignOut) {
                                println("User signed out")
                            } else {
                                println("User not signed in")
                            }
                        }

                    }
                }
            }
        }
        catch (e : Exception){
            Info.complete( DTOMessage(false,"Не коректные данные"))
        }
        return Info.await()
    }
}
