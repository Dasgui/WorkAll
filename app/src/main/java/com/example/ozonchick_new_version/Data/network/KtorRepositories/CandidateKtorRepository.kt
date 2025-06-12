package com.example.ozonchick_new_version.Data.network.KtorRepositories

import android.util.Log
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.CandidateResponses
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseInfoStatus
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseStatus
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeDataStatus
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeInfo
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeInfoUpdate
import com.example.ozonchick_new_version.Data.model.Ktor.VacanciesOtklikResponse
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyOtklikResponse
import com.example.ozonchick_new_version.Data.network.client
import com.example.ozonchick_new_version.Data.network.json
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess

interface CandidateKtorRepository {
    suspend fun getAllCandidateResponse () : CustomResult<CandidateResponses>
    suspend fun rejectCandidateResponse(vacancyId : Int) : CustomResult<ResponseInfoStatus>

    suspend fun addResume (resumeInfo : ResumeInfo) : CustomResult<ResponseStatus>
    suspend fun updateResume (resumeInfo : ResumeInfoUpdate) : CustomResult<ResponseStatus>
    suspend fun getResume (candidateId : Int) : CustomResult<ResumeDataStatus>
//    suspend fun addResume (resumeInfo : ResumeData) : CustomResult<ResponseInfoStatus>
}

class CandidateKtorRepositoryImpl : CandidateKtorRepository {
    override suspend fun getAllCandidateResponse(): CustomResult<CandidateResponses> {
        return try {
            val token = localUserInformation.token
            val response = client.get("user/response") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<CandidateResponses>(response.bodyAsText())
                if (result.responses == null) {
                    CustomResult.Failure(ExclusionOptions.NullResponses.id, ExclusionOptions.NullResponses.message)
                }
                Log.d("Отклики получены", result.toString())
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

    //Возможно изменение (пока не работает)
    override suspend fun rejectCandidateResponse(vacancyId: Int): CustomResult<ResponseInfoStatus> {
        return try {
            val token = localUserInformation.token
            val response = client.delete("vac/response") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                url {
                    parameters.append("VacancyID", vacancyId.toString())
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<ResponseInfoStatus>(response.bodyAsText())
                Log.d("Отклики получены", result.toString())
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

    override suspend fun addResume(resumeInfo: ResumeInfo): CustomResult<ResponseStatus> {
        return try {
            val token = localUserInformation.token
            val response = client.post("user/resume") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                setBody(resumeInfo)
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<ResponseStatus>(response.bodyAsText())
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

    override suspend fun updateResume(resumeInfo: ResumeInfoUpdate): CustomResult<ResponseStatus> {
        return try {
            val token = localUserInformation.token
            val response = client.put("user/resume") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                setBody(resumeInfo)
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<ResponseStatus>(response.bodyAsText())
                Log.d("Резюме обновленно", result.toString())
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

    override suspend fun getResume(candidateId: Int): CustomResult<ResumeDataStatus> {
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
                val result = json.decodeFromString<ResumeDataStatus>(response.bodyAsText())
                Log.d("Резюме полученно", result.toString())
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

}