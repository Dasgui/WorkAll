package com.example.ozonchick_new_version.Data.network.KtorRepositories

import android.util.Log
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.CustomResult.Failure
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.AllTypeUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.CandidateInfo
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.CandidateInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.EmployerInfo
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.EmployerInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.VacanciesResponse
import com.example.ozonchick_new_version.Data.network.client
import com.example.ozonchick_new_version.Data.network.json
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

interface KtorRepository {
    suspend fun getVacancies(): CustomResult<VacanciesResponse>


    suspend fun auth(
        email: String,
        password: String,
        rememberUser: Boolean
    ): CustomResult<AllTypeUserInformation>

    suspend fun registrationCandidate(
        name: String,
        password: String,
        phoneNumber: String,
        email: String,
    ) : CustomResult<CandidateInfo>

    suspend fun registrationEmployer(
        name: String,
        password: String,
        phoneNumber: String,
        email: String,
        inn: String,
        organizationName: String
    ) : CustomResult<EmployerInfo>
}

class VacancyRepository : KtorRepository {
    override suspend fun getVacancies(): CustomResult<VacanciesResponse> {
        return try {
            val response = client.get("vac?limit=3&last_id=0")
            val body: String = response.body()
            Log.d("API_RESPONSE", body)
            val result = json.decodeFromString<VacanciesResponse>(body)
            CustomResult.Success(result)
        } catch (ex: Exception) {
            Log.d("Получение всех вакансий", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ex.toString()
            )
        }
    }


    //Нужно добавить запоминание не id, а token.
    //Также надо будет убрать лишние screenState. Чтоб начать убирать лишние остатки supabase.
    override suspend fun auth(
        email: String,
        password: String,
        rememberUser: Boolean
    ): CustomResult<AllTypeUserInformation> {
        return try {
            var candidateResponse = client.get("user/auth") {
                url {
                    parameters.append("email", email)
                    parameters.append("password", password)
                }
            }

            if (candidateResponse.status.isSuccess()) {
                var body: String = candidateResponse.body()
                val result = json.decodeFromString<CandidateInfo>(body)
                Log.d("Результат входа работодат Ktor", result.toString())
                return CustomResult.Success(result)
            } else {
                Log.d("Результат входа работодат Ktor", candidateResponse.status.toString())
            }

            val employerResponse = client.get("emp/auth") {
                url {
                    parameters.append("email", email)
                    parameters.append("password", password)
                }
            }
            if (employerResponse.status.isSuccess()) {
                var body: String = employerResponse.body()
                val result = json.decodeFromString<EmployerInfo>(body)
                Log.d("Результат входа работодат Ktor", result.toString())
//                return CustomResult.Success(result)
            } else {
                Log.d("Результат входа работодат Ktor", employerResponse.status.toString())
            }


            Failure(0, AppErrors.UNKNOWN_USER.description)

        } catch (ex: Exception) {
            Log.d("Ошибка при входе", ex.toString())
            Failure(
                0,
                AppErrors.UNKNOWN_USER.description
            )
        }
    }

    override suspend fun registrationCandidate(
        name: String,
        password: String,
        phoneNumber: String,
        email: String
    ): CustomResult<CandidateInfo> {
        return try {
            val candidate = CandidateInsertInformation(
                name = name,
                phoneNumber = phoneNumber,
                email = email,
                password = password,
                statusId = 5
            )
            val response = client.post("user") {
                setBody(candidate)
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<CandidateInfo>(response.bodyAsText())
                Log.d("Результат регистрации кандидата Ktor", result.toString())
                return CustomResult.Success(result)
            } else {
                Failure(0, AppErrors.USER_EXIST.description)
            }



        }  catch (ex: Exception) {
            Log.d("Регистрация", ex.toString())
            Failure(0, AppErrors.UNKNOWN_USER.description)
        }
    }

    //Нужно дождаться пока свойство поменяет размер своей буквы и написание, потому что пока не получатеся замапить данные. Не охото делать по три класса.
    override suspend fun registrationEmployer(
        name: String,
        password: String,
        phoneNumber: String,
        email: String,
        inn: String,
        organizationName: String
    ): CustomResult<EmployerInfo> {
        return try {
            val employer = EmployerInsertInformation(
                nameOrganization = organizationName,
                phoneNumber = phoneNumber,
                email = email,
                password = password,
                statusId = 5,
                inn = inn,
            )
            val response = client.post("emp") {
                setBody(employer)
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<EmployerInfo>(response.bodyAsText())
                Log.d("Результат регистрации работодателя Ktor", result.toString())
                return CustomResult.Success(result)
            } else {
                Log.d("Результат регистрации работодателя Ktor", response.body())
                Failure(0, AppErrors.USER_EXIST.description)
            }



        }  catch (ex: Exception) {
            Log.d("Регистрация", ex.toString())
            Failure(0, AppErrors.UNKNOWN_USER.description)
        }
    }
}