package com.example.ozonchick_new_version.Data.network.KtorRepositories

import android.util.Log
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.CustomResult.Failure
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Data.model.Authentication.CandidateChangeableInformation
import com.example.ozonchick_new_version.Data.model.Ktor.AllTypeUserDefaultResponse
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.AllTypeUserInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.CandidateInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.EmployerInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.DefaultCandidateResponse
import com.example.ozonchick_new_version.Data.model.Ktor.DefaultEmployerResponse
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseInfoStatus
import com.example.ozonchick_new_version.Data.network.client
import com.example.ozonchick_new_version.Data.network.json
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess

interface HomeKtorRepository {
    suspend fun getUserInformation(
        id: Int,
        userType: UserType
    ): CustomResult<AllTypeUserDefaultResponse>
    suspend fun updateUserInformation(information: AllTypeUserInsertInformation, endpoint: String) : CustomResult<ResponseInfoStatus>
}

class HomeKtorRepositoryImpl : HomeKtorRepository {

    override suspend fun getUserInformation(
        id: Int,
        userType: UserType
    ): CustomResult<AllTypeUserDefaultResponse> {
        return try {
            val token = localUserInformation.token
            val (endpoint, paramName) = when (userType) {
                is UserType.Employer -> Pair("emp", "EmployerID")
                is UserType.Candidate -> Pair("user", "CandidateID")
                UserType.Admin -> Pair("", "")
                UserType.None -> Pair("", "")
            }

            val response = client.get(endpoint) {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                url {
                    parameters.append(paramName, id.toString())
                }
            }

            if (response.status.isSuccess()) {
                val result = when (userType) {
                    is UserType.Employer ->
                        json.decodeFromString<DefaultEmployerResponse>(response.bodyAsText())
                    is UserType.Candidate ->
                        json.decodeFromString<DefaultCandidateResponse>(response.bodyAsText())

                    UserType.Admin -> TODO()
                    UserType.None -> TODO()
                }
                Log.d("Успешное получение информации", result.toString())
                CustomResult.Success(result)
            } else {
                Log.d("Ошибка при получении информации", response.status.toString())
                CustomResult.Failure(0, AppErrors.UNKNOWN.description)
            }
        } catch (ex: Exception) {
            Log.d("Ошибка при выполнении запроса", ex.toString())
            CustomResult.Failure(ExclusionOptions.NetworkError.id, ExclusionOptions.NetworkError.message)
        }
    }

    override suspend fun updateUserInformation(
        information: AllTypeUserInsertInformation,
        endpoint: String
    ): CustomResult<ResponseInfoStatus> {
        return try {
            Log.d("Тело обновления", information.toString())
            Log.d("EndPoint", endpoint)
            val token = localUserInformation.token
            val response = client.put(endpoint) {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                setBody(information)
            }

            if (response.status.isSuccess()) {
                val result = json.decodeFromString<ResponseInfoStatus>(response.bodyAsText())
                Log.d("Результат обновления Ktor", result.toString())
                CustomResult.Success(result)
            } else {
                Log.d("Ошибка обновления Ktor", response.body())
                Failure(0, AppErrors.USER_INFORMATION_BUSY.description)
            }
        } catch (ex: Exception) {
            Log.d("Ошибка обновления", ex.toString())
            Failure(0, AppErrors.UNKNOWN.description)
        }
    }


}