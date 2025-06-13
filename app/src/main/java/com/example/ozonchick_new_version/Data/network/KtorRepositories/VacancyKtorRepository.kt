package com.example.ozonchick_new_version.Data.network.KtorRepositories

import android.util.Log
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.AllExperience
import com.example.ozonchick_new_version.Data.model.Ktor.EmployerVacancies
import com.example.ozonchick_new_version.Data.model.Ktor.ErrorInfoStatus
import com.example.ozonchick_new_version.Data.model.Ktor.Experience
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseInfoStatus
import com.example.ozonchick_new_version.Data.model.Ktor.VacanciesResponse
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyCreateResponse
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyInfo
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyInformation
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyOtklikResponse
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyResponse
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyUpdateResponse
import com.example.ozonchick_new_version.Data.model.ListOfVacancies.StatesOfGetVacancies
import com.example.ozonchick_new_version.Data.network.client
import com.example.ozonchick_new_version.Data.network.json
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.util.network.UnresolvedAddressException

interface VacancyKtorRepository {
    suspend fun getVacancies(limit : Int, lastID : Int): CustomResult<VacanciesResponse>
    suspend fun createVacancy(information : VacancyInfo) : CustomResult<VacancyCreateResponse>
    suspend fun deleteVacancy (vacancyId : Int) : CustomResult<ResponseInfoStatus>
    suspend fun updateVacancyInformation (information: VacancyUpdateResponse) : CustomResult<ResponseInfoStatus>

    suspend fun getAllExperience() : CustomResult<AllExperience>
    suspend fun getEmployerVacancies() : CustomResult<EmployerVacancies>
    suspend fun getVacancyInformation(id : Int) : CustomResult<VacancyResponse>

    suspend fun addVacancyResponse(vacancyId : Int) : CustomResult<VacancyOtklikResponse>

    suspend fun searchVacancies (searchValue : String) : CustomResult<VacanciesResponse>
    suspend fun filterVacancies (information : StatesOfGetVacancies.Filter) : CustomResult<VacanciesResponse>
    suspend fun findVacancies (information : StatesOfGetVacancies.Filter) : CustomResult<VacanciesResponse>
}

class VacancyKtorRepositoryImpl : VacancyKtorRepository {
    override suspend fun getVacancies(page : Int, perPage : Int): CustomResult<VacanciesResponse> {
        return try {
            val response = client.get("vac") {
                url {
                    parameters.append("Page", page.toString())
                    parameters.append("PerPage", perPage.toString())
                }
            }
            val result = json.decodeFromString<VacanciesResponse>(response.bodyAsText())
            CustomResult.Success(result)
        } catch (ex: Exception) {
            Log.d("Получение всех вакансий", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun createVacancy(information: VacancyInfo): CustomResult<VacancyCreateResponse> {
        return try {
            val token = localUserInformation.token
            var response = client.post("vac") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                setBody(information)
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<VacancyCreateResponse>(response.bodyAsText())
                Log.d("Результат добав вакансии Ktor", result.toString())
                CustomResult.Success(result)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.NetworkError.id,
                    ExclusionOptions.NetworkError.message
                )
            }
        }
        catch (ex : Exception) {
            Log.d("Добавление вакансии", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun deleteVacancy(vacancyId: Int): CustomResult<ResponseInfoStatus> {
        return try {
            val token = localUserInformation.token
            var response = client.delete("vac") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                url {
                    parameters.append("VacancyID", vacancyId.toString())
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<ResponseInfoStatus>(response.bodyAsText())
                Log.d("Результат удаления вакансии Ktor", result.toString())
                CustomResult.Success(result)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.NetworkError.id,
                    ExclusionOptions.NetworkError.message
                )
            }
        }
        catch (ex : Exception) {
            Log.d("Удаление вакансии", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun updateVacancyInformation(information: VacancyUpdateResponse): CustomResult<ResponseInfoStatus> {
        return try {
            val token = localUserInformation.token
            var response = client.put("vac") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                setBody(information)
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<ResponseInfoStatus>(response.bodyAsText())
                Log.d("Результат обновления вакансии Ktor", result.toString())
                CustomResult.Success(result)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.NetworkError.id,
                    ""
                )
            }
        }
        catch (ex : Exception) {
            Log.d("Обновление вакансии", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun getAllExperience(): CustomResult<AllExperience> {
        return try {
            val token = localUserInformation.token
            val response = client.get("exp") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<AllExperience>(response.bodyAsText())
                CustomResult.Success(result)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.NetworkError.id,
                    ExclusionOptions.NetworkError.message
                )
            }
        }
        catch (ex : Exception) {
            Log.d("Ошибкаа получ опыта", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun getEmployerVacancies(): CustomResult<EmployerVacancies> {
        return try {
            val token = localUserInformation.token
            val response = client.get("vac/emp") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<EmployerVacancies>(response.bodyAsText())
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

    override suspend fun getVacancyInformation(id : Int): CustomResult<VacancyResponse> {
        return try {
            val token = localUserInformation.token
            val response = client.get("vac/info") {
                url {
                    parameters.append("VacancyID", id.toString())
                }
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<VacancyResponse>(response.bodyAsText())
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

    override suspend fun addVacancyResponse(vacancyId: Int): CustomResult<VacancyOtklikResponse> {
        return try {
            val token = localUserInformation.token
            val response = client.post("vac/response") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
                url {
                    parameters.append("VacancyID", vacancyId.toString())
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<VacancyOtklikResponse>(response.bodyAsText())
                Log.d("Отклик добавлен", result.toString())
                CustomResult.Success(result)
            } else {
                val result = json.decodeFromString<ErrorInfoStatus>(response.bodyAsText())
                CustomResult.Failure(
                    500,
                    result.error
                )
            }
        }
//        java.nio.channels.UnresolvedAddressException
        catch (ex : UnresolvedAddressException) {
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
        catch (ex : Exception) {
            Log.d("Отклик не добавлен", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun searchVacancies(searchValue: String): CustomResult<VacanciesResponse> {
        return try {
            val token = localUserInformation.token
            val response = client.get("vac/search") {
                url {
                    parameters.append("Text", searchValue)
                }
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<VacanciesResponse>(response.bodyAsText())
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

    override suspend fun filterVacancies(information: StatesOfGetVacancies.Filter): CustomResult<VacanciesResponse> {
        return try {
            val token = localUserInformation.token
            val response = client.get("vac/filter") {
                url {
                    if (information.filterExperience?.id != 0) parameters.append("ExpID", information.filterExperience?.id.toString())
                    if (information.filterSalary.isNotEmpty()) parameters.append("Min", information.filterSalary)
//                    parameters.append("Max", searchValue)
                }
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<VacanciesResponse>(response.bodyAsText())
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

    override suspend fun findVacancies(information: StatesOfGetVacancies.Filter): CustomResult<VacanciesResponse> {
        return try {
            val token = localUserInformation.token
            val response = client.get("vac/search") {
                url {
                    if (information.filterExperience != null) parameters.append("ExpID", information.filterExperience.id.toString())
                    if (information.filterSalary.isNotEmpty()) parameters.append("Min", information.filterSalary)
                    if (information.search.isNotEmpty()) parameters.append("Text", information.search)
                }
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (response.status.isSuccess()) {
                val result = json.decodeFromString<VacanciesResponse>(response.bodyAsText())
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