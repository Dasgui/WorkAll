package com.example.ozonchick_new_version.Data.network

import com.example.ozonchick_new_version.Data.model.DTOMessage
import com.example.ozonchick_new_version.Data.model.SignUp_LogIn_Model.UserInformationRegistration
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.CompletableDeferred

class RegistraitUser() {
    suspend fun RegisstraitEmail(name:String, phoneNumber:String, email:String, passwordUser : String/*, userStatus:String, INN:String*/) : DTOMessage
    {
        val info = CompletableDeferred<DTOMessage>()
        val user = supabase.from("user_information").select(columns = Columns.list("name, phoneNumber, email, password")) {
            filter {
                UserInformationRegistration::name eq name
                UserInformationRegistration::email eq email
                UserInformationRegistration::password eq passwordUser
            }
        }.decodeList<UserInformationRegistration>()

        if (user.isEmpty()) {
            supabase.from("user_information").insert(UserInformationRegistration(name, phoneNumber, email, passwordUser))
            {
                select(Columns.list("user_id"))
                info.complete(DTOMessage(true, "Успешная регистрация"))
            }
        } else {
            info.complete(DTOMessage(false, "Произошла ошибка"))
        }

        return info.await()
//        var Info = CompletableDeferred<DTOMessage>()
//        try {
//            CoroutineScope(Dispatchers.IO).launch {
//                supabase.auth.signUpWith(provider = Email)
//                {
//                    email = mail
//                    password = passwordUser
//                }
//                supabase.auth.sessionStatus.collect {
//                    when (it) {
//                        is SessionStatus.Authenticated -> {
//                            println("Received new authenticated session.")
//                            when (it.source) { //Check the source of the session
//                                SessionSource.External -> Log.d("Сесия", "External")
//                                is SessionSource.Refresh -> Log.d("Сесия", "Refresh")
//                                is SessionSource.SignIn -> {}
//
//                                is SessionSource.SignUp -> {
//                                    when (userStatus) {
//                                        "Соискатель" -> {
//                                            supabase.from("user_information").insert(User_information_registrait(name,phoneNumber,mail, passwordUser))
//                                            {
//                                                select(Columns.list("user_id"))
//                                            }
//                                        }
//                                        "Работодатель" -> {
//                                            supabase.from("employers_information").insert(Employer_information_registrait(name,phoneNumber,mail, INN))
//                                        }
//                                        "Администратор" -> {
//
//                                        }
//                                    }
//
////                                    supabase.from("user_balance").insert(User_balance(otvet.user_id,0))
//                                    Info.complete(DTOMessage(true, "Успешная регистрация"))
//                                }
//                                SessionSource.Storage -> Log.d("Сесия", "Storage")
//                                SessionSource.Unknown -> Log.d("Сесия", "Unknown")
//                                is SessionSource.UserChanged -> Log.d("Сесия", "UserChanged")
//                                is SessionSource.UserIdentitiesChanged -> Log.d("Сесия", "UserIdentitiesChanged")
//                            }
//                        }
//
//                        SessionStatus.LoadingFromStorage -> println("Loading from storage")
//                        SessionStatus.NetworkError ->  Info.complete(DTOMessage(true,"Нет соединение с сервером"))
//                        is SessionStatus.NotAuthenticated -> {
//                            if (it.isSignOut) {
//                                println("User signed out")
//                            } else {
//                                println("User not signed in")
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
//        catch (e : Exception){
//            Info.complete( DTOMessage(false,"Не коректные данные"))
//        }
//        return Info.await()
    }
}
