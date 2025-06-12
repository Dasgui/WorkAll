package com.example.ozonchick_new_version.Data.network.KtorRepositories

import android.util.Log
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.AllResponses
import com.example.ozonchick_new_version.Data.model.Ktor.AllStatus
import com.example.ozonchick_new_version.Data.model.Ktor.CandidateResponses
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseInfoStatus
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseStatus
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeData
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeInfo
import com.example.ozonchick_new_version.Data.model.Ktor.UpdateResponse
import com.example.ozonchick_new_version.Data.model.Ktor.candidateStatuses
import com.example.ozonchick_new_version.Data.network.client
import com.example.ozonchick_new_version.Data.network.json
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import com.example.ozonchick_new_version.Presentation.States.ScreenState.Failure
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess

interface EmployerKtorRepository {
    suspend fun getAllResponses (vacancyId : Int) : CustomResult<AllResponses>

    suspend fun getCandidateResume (candidateId : Int) : CustomResult<ResumeData>
    suspend fun getOtklikAllStatus () : CustomResult<AllStatus>

    suspend fun rejectCandidate (responseId : Int) : CustomResult<ResponseInfoStatus>
    suspend fun acceptCandidate (responseId : Int) : CustomResult<ResponseInfoStatus>
}

class EmployerKtorRepositoryImpl : EmployerKtorRepository {
    override suspend fun getAllResponses(vacancyId: Int): CustomResult<AllResponses> {
        return try {
            val token = localUserInformation.token
            val response = client.get("vac/response") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                url {
                    parameters.append("VacancyID", vacancyId.toString())
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<AllResponses>(response.bodyAsText())
                if (result.responseInfo == null) {
                    CustomResult.Failure(ExclusionOptions.NullCandidates.id, ExclusionOptions.NullCandidates.message)
                }
                Log.d("Соискатели получены", result.toString())
                CustomResult.Success(result)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.NetworkError.id,
                    ""
                )
            }
        }
        catch (ex : Exception) {
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun getCandidateResume(candidateId: Int): CustomResult<ResumeData> {
        return try {
            val token = localUserInformation.token
            val response = client.get("user/resume") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                url {
                    parameters.append("CandidateID", candidateId.toString())
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<ResumeData>(response.bodyAsText())
                CustomResult.Success(result)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.NetworkError.id,
                    ""
                )
            }
        }
        catch (ex : Exception) {
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun getOtklikAllStatus(): CustomResult<AllStatus> {
        return try {
            val token = localUserInformation.token
            val response = client.get("status") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<AllStatus>(response.bodyAsText())
                result.data = result.data.filterNot { it.name != "Приглашение" && it.name != "Отказ" && it.name != "На рассмотрении" }
                Log.d("Статусы", result.data.toString())
                CustomResult.Success(result)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.NetworkError.id,
                    ""
                )
            }
        }
        catch (ex : Exception) {
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun rejectCandidate(responseId : Int): CustomResult<ResponseInfoStatus> {
        return try {
            val information = UpdateResponse(
                responseId, candidateStatuses.entries.firstOrNull {it.value == "Отказ"}?.key ?: 0
            )
            val token = localUserInformation.token
            val response = client.patch("vac/response") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                setBody(information)
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<ResponseInfoStatus>(response.bodyAsText())
                Log.d("Резюме созданно", result.toString())
                CustomResult.Success(result)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.NetworkError.id,
                    ""
                )
            }
        }
        catch (ex : Exception) {
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun acceptCandidate(responseId: Int): CustomResult<ResponseInfoStatus> {
        return try {
            val information = UpdateResponse(
                responseId, candidateStatuses.entries.firstOrNull {it.value == "Приглашение"}?.key ?: 0
            )
            val token = localUserInformation.token
            val response = client.patch("vac/response") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                setBody(information)
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<ResponseInfoStatus>(response.bodyAsText())
                Log.d("Резюме созданно", result.toString())
                CustomResult.Success(result)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.NetworkError.id,
                    ExclusionOptions.NetworkError.message
                )
            }
        }
        catch (ex : Exception) {
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

}