package com.example.ozonchick_new_version.Data.network

import com.example.ozonchick_new_version.Data.data.db.idSharedUser
import com.example.ozonchick_new_version.Data.model.DTOMessage
import com.example.ozonchick_new_version.Data.model.SignUp_LogIn_Model.UserInformationAuth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.CompletableDeferred

/**
 * Класс предназанчаен для регистрации пользователя
 * 24.03.2024
 *@param AuthEmail авторизация пользователя
 * @author Matvei
 *
 * */

class AuthUser() {
    suspend fun AuthEmail(email: String, password: String, rememberUser: Boolean): DTOMessage {
        var Info = CompletableDeferred<DTOMessage>()
        val user = supabase.from("user_information")
            .select(columns = Columns.list("user_id, email, password")) {
                filter {
                    UserInformationAuth::email eq email
                    UserInformationAuth::password eq password
                }
            }.decodeList<UserInformationAuth>()
        if (user.isEmpty()) {
            Info.complete(DTOMessage(false, "Ошибка входа в систему"))
        } else {
            idSharedUser.value = user[0].user_id
            Info.complete(DTOMessage(true, "Успешная авторизация"))
        }
        return Info.await()
    }
}
