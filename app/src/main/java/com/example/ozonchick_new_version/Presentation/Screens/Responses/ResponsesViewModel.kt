package com.example.ozonchick_new_version.Presentation.Screens.Responses

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.VacancyResponse
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Domain.GetResponsesIdVacanciesUseCase
import com.example.ozonchick_new_version.Domain.GetResponsesVacanciesUseCase
import com.example.ozonchick_new_version.Domain.RejectVacancyUseCase
import com.example.ozonchick_new_version.Presentation.Models.ResponsesUIModel
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ResponsesViewModel(
    private val getResponsesIdVacanciesUseCase: GetResponsesIdVacanciesUseCase,
    private val getResponsesVacanciesUseCase: GetResponsesVacanciesUseCase,
    private val rejectVacancyUseCase: RejectVacancyUseCase
) : ViewModel() {
    val responseState : MutableStateFlow<ScreenState<ResponsesUIModel>> =
        MutableStateFlow(ScreenState.Loading)
    var listResponses : MutableState<List<VakansInformation>> = mutableStateOf(listOf())

    init {
        responseState.value = ScreenState.Loading
        loadData()
    }
    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val idVacancies = getIdVacancies(publicUserId).await()
            Log.d("Вакансии с откликом", idVacancies.toString())
            if (idVacancies !is CustomResult.Failure) {
                val vacancies = getInformation((idVacancies as CustomResult.Success).data.map { it.vakans_id }).await()
                Log.d("Вакансии с откликом", vacancies.toString())
                if (vacancies !is CustomResult.Failure) {
                    responseState.value = ScreenState.Success(
                        ResponsesUIModel(
                            information = (vacancies as CustomResult.Success).data,
                            status = idVacancies.data
                        )
                    )
                    listResponses.value = vacancies.data
                } else {
                    responseState.value = ScreenState.Failure(vacancies.code, vacancies.message)
                }
                Log.d("Получение на откликнувшиеся вакансии", responseState.value.toString())
            } else {
                responseState.value = ScreenState.Failure(idVacancies.code, idVacancies.message)
            }
        }
    }

    suspend fun getIdVacancies(userId : UserId) : Deferred<CustomResult<List<VacancyResponse>>> {
        return coroutineScope { async(Dispatchers.IO) { getResponsesIdVacanciesUseCase(userId) } }
    }

    suspend fun getInformation(idList : List<Int>) : Deferred<CustomResult<List<VakansInformation>>> {
        return coroutineScope { async(Dispatchers.IO) { getResponsesVacanciesUseCase(idList) } }
    }

    fun reject (vacancyId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            rejectVacancyUseCase(publicUserId.id, vacancyId)
            loadData()
        }
    }
}