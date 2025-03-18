package com.example.ozonchick_new_version.Data.network

import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.SigLogInModels.User
import io.github.jan.supabase.exceptions.HttpRequestException

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

interface Repository {
    suspend fun Auth(email: String, password: String): CustomResult<User>
}

class RepositoryImpl : Repository {
    override suspend fun Auth(
        email: String,
        password: String
    ): CustomResult<User> {
        return try {
            val result = supabase.from("user_information").select() {
                filter {
                    User::email eq email
                    User::password eq password
                }
            }.decodeList<User>()
            CustomResult.Success(result[0])
        } catch (ex: HttpRequestException) {
            CustomResult.Failure(1, "Проверьте своё соединение с интернетом.")
        } catch (ex : IndexOutOfBoundsException) {
            CustomResult.Failure(2, "Возможно, пользователя не существует. Проверьте введенные данные.")
        } catch (ex : Exception) {
            CustomResult.Failure(3, "Неизвестная ошибка")
        }
    }
}