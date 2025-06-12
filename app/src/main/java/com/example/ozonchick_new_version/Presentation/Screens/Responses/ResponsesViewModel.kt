package com.example.ozonchick_new_version.Presentation.Screens.Responses

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.Ktor.CandidateResponses
import com.example.ozonchick_new_version.Data.model.VacancyInformation
import com.example.ozonchick_new_version.Domain.GetAllCandidateResponseKtorUseCase
import com.example.ozonchick_new_version.Domain.RejectCandidateResponseKtorUseCase
import com.example.ozonchick_new_version.Domain.RejectVacancyUseCase
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ResponsesViewModel(
    private val getAllCandidateResponse : GetAllCandidateResponseKtorUseCase,
//    private val getResponsesIdVacanciesUseCase: GetResponsesIdVacanciesUseCase,
//    private val getResponsesVacanciesUseCase: GetResponsesVacanciesUseCase,
    private val rejectVacancyUseCase: RejectCandidateResponseKtorUseCase
) : ViewModel() {
    val responseState : MutableStateFlow<ScreenState<CandidateResponses>> =
        MutableStateFlow(ScreenState.Loading)
    var listResponses : MutableState<List<VacancyInformation>> = mutableStateOf(listOf())

    init {
        responseState.value = ScreenState.Loading
        loadData()
    }
    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {

            when(val result = getAllCandidateResponse()) {
                is CustomResult.Failure -> {
                    if (result.code == ExclusionOptions.NullResponses.id) {
                        responseState.value = ScreenState.AwaitingResponse
                    } else {
                        responseState.value = ScreenState.Failure(result.code, result.message)
                    }

                }
                is CustomResult.Success<CandidateResponses> -> {
                    responseState.value = ScreenState.Success(result.data)
                }
            }
        }
    }

//    suspend fun getIdVacancies(userId : UserId) : Deferred<CustomResult<List<VacancyResponse>>> {
//        return coroutineScope { async(Dispatchers.IO) { getResponsesIdVacanciesUseCase(userId) } }
//    }
//
//    suspend fun getInformation(idList : List<Int>) : Deferred<CustomResult<List<VacancyInformation>>> {
//        return coroutineScope { async(Dispatchers.IO) { getResponsesVacanciesUseCase(idList) } }
//    }

    fun reject (vacancyId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            rejectVacancyUseCase.invoke(vacancyId)
            loadData()
        }
    }
}