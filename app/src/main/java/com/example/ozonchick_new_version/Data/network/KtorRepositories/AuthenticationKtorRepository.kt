package com.example.ozonchick_new_version.Data.network.KtorRepositories

import android.util.Log
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.CustomResult.Failure
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.AllTypeUserInfo
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.AllTypeUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.CandidateInfo
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.CandidateInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.EmployerInfo
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.EmployerInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.VacanciesResponse
import com.example.ozonchick_new_version.Data.network.client
import com.example.ozonchick_new_version.Data.network.json
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Data.strings.userStatuses
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

interface AuthenticationKtorRepository {

    suspend fun auth(
        email: String,
        password: String,
        rememberUser: Boolean
    ): CustomResult<AllTypeUserInfo>

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

class AuthenticationKtorRepositoryImpl : AuthenticationKtorRepository {
    override suspend fun auth(
        email: String,
        password: String,
        rememberUser: Boolean
    ): CustomResult<AllTypeUserInfo> {
        return try {
            var candidateResponse = client.get("auth") {
                url {
                    parameters.append("Email", email)
                    parameters.append("Password", password)
                }
            }

            if (candidateResponse.status.isSuccess()) {
                var body: String = candidateResponse.body()
                Log.d("Результат входа работодат Ktor", body.toString())
                val result = json.decodeFromString<AllTypeUserInfo>(body)
                if (result.employerInfo?.status?.id == 9 || result.candidateInfo?.statusInfo?.id == 9) {
                    return Failure(ExclusionOptions.UserBlock.id, ExclusionOptions.UserBlock.message)
                }
                return CustomResult.Success(result)
            } else {
                Log.d("Результат входа работодат Ktor", candidateResponse.status.toString())
            }

//            val employerResponse = client.get("emp/auth") {
//                url {
//                    parameters.append("email", email)
//                    parameters.append("password", password)
//                }
//            }
//            if (employerResponse.status.isSuccess()) {
//                var body: String = employerResponse.body()
//                val result = json.decodeFromString<EmployerInfo>(body)
//                Log.d("Результат входа работодат Ktor", result.toString())
//                return CustomResult.Success(result)
//            } else {
//                Log.d("Результат входа работодат Ktor", employerResponse.status.toString())
//            }
//
//
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
            Failure(ExclusionOptions.NetworkError.id, ExclusionOptions.NetworkError.message)
        }
    }

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
            Failure(ExclusionOptions.NetworkError.id, ExclusionOptions.NetworkError.message)
        }
    }
}