package com.example.ozonchick_new_version.Data.network

import android.util.Log
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.SigLogInModels.User
import com.example.ozonchick_new_version.Data.model.Candidate.UserInformation
import com.example.ozonchick_new_version.Data.model.Candidate.UserInsertInformation
import com.example.ozonchick_new_version.Data.model.Employer.EmployerInsertInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Data.model.Vakans_information
import io.github.jan.supabase.exceptions.HttpRequestException

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.selects.select

interface Repository {
    suspend fun Auth(email: String, password: String): CustomResult<User>
    suspend fun getVacancies() : CustomResult<List<Vakans_information>>
    suspend fun getContactInformation(id : Int) : CustomResult<List<Vakans_contacts>>
    suspend fun registrationCandidate(name : String, password: String, phoneNumber: String, email: String) : CustomResult<List<UserInsertInformation>>
    suspend fun registrationEmployer(name : String, password: String, phoneNumber: String, email: String, inn : String) : CustomResult<List<EmployerInsertInformation>>
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

    override suspend fun getVacancies(): CustomResult<List<Vakans_information>> {
        return try {
            val result = supabase.from("vakans").select(
                Columns.Companion.list(
                    "vakans_id",
                    "vakans_name",
                    "vakans_price",
                    "vakans_organization",
                    "vakans_location",
                    "vakans_experience",
                    "vakans_mail",
                    "employer_id"
                )
            ).decodeList<Vakans_information>()
            CustomResult.Success(result)
        } catch (ex : Exception) {
            CustomResult.Failure(1, "Проверьте своё соединение с интернетом")
        }
    }

    override suspend fun getContactInformation(id : Int): CustomResult<List<Vakans_contacts>> {
        return try {
            val result = supabase.from("vakans_contacts")
                .select()
                {
                    filter {
                        Vakans_contacts::vakans_id eq id
                    }
                }.decodeList<Vakans_contacts>()
            CustomResult.Success(result)
        } catch (ex : Exception) {
            CustomResult.Failure(1, "Проверьте своё соединение с интернетом")
        }
    }

    override suspend fun registrationCandidate(
        name: String,
        password: String,
        phoneNumber: String,
        email: String,
    ) : CustomResult<List<UserInsertInformation>>  {
         return try {
            val user = UserInsertInformation(
                name = name,
                phoneNumber = phoneNumber,
                email = email,
                password = password
            )
            val result = supabase.from("user_information").insert(user) {
                select()
            }.decodeList<UserInsertInformation>()
            CustomResult.Success(result)
        } catch (ex : Exception) {
             Log.d("Регистрация", ex.toString())
            CustomResult.Failure(1, ex.toString())
        }
    }

    override suspend fun registrationEmployer(
        name: String,
        password: String,
        phoneNumber: String,
        email: String,
        inn: String
    ): CustomResult<List<EmployerInsertInformation>> {
        return try {
            val user = EmployerInsertInformation(
                name = name,
                phoneNumber = phoneNumber,
                email = email,
                password = password,
                INN = inn
            )
            val result = supabase.from("employers_information").insert(user) {
                select()
            }.decodeList<EmployerInsertInformation>()
            CustomResult.Success(result)
        } catch (ex : Exception) {
            Log.d("Регистрация", ex.toString())
            CustomResult.Failure(1, ex.toString())
        }
    }
}