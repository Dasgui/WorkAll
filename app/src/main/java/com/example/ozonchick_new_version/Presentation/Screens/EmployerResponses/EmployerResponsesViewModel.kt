package com.example.ozonchick_new_version.Presentation.Screens.EmployerResponses

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.Authentication.Candidate.Candidate
import com.example.ozonchick_new_version.Data.model.Ktor.AllResponses
import com.example.ozonchick_new_version.Domain.AcceptCandidateKtorUseCase
import com.example.ozonchick_new_version.Domain.AcceptCandidateUseCase
import com.example.ozonchick_new_version.Domain.GetAllResponsesUseCase
import com.example.ozonchick_new_version.Domain.GetOtklikAllStatusUseCase
import com.example.ozonchick_new_version.Domain.RejectCandidateKtorUseCase
import com.example.ozonchick_new_version.Domain.RejectCandidateUseCase
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import com.example.ozonchick_new_version.Presentation.States.DialogStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EmployerResponsesViewModel(
    private val vacancyId : Int,
    private val getAllResponsesUseCase: GetAllResponsesUseCase,
    private val acceptCandidateUseCase: AcceptCandidateKtorUseCase,
    private val rejectCandidateUseCase: RejectCandidateKtorUseCase,
    private val getOtlikAllStatusUseCase : GetOtklikAllStatusUseCase
) : ViewModel() {
    val screenState : MutableStateFlow<ScreenState<AllResponses>> =
        MutableStateFlow(ScreenState.Loading)

    val dialogState : MutableState<DialogStates> = mutableStateOf(DialogStates.None)

    fun loadData () {
        screenState.value = ScreenState.Loading
        viewModelScope.launch(Dispatchers.IO) {

            when (val result = getAllResponsesUseCase(vacancyId)) {
                is CustomResult.Failure -> {
                    screenState.value = ScreenState.Failure(result.code, result.message)
                }
                is CustomResult.Success<AllResponses> -> {
                    if (result.data.responseInfo == null) {
                        Log.d("Пустой список", result.data.responseInfo.toString())
                        screenState.value = ScreenState.AwaitingChangeFromUser
                    } else {
                        screenState.value = ScreenState.Success(result.data)
                    }

                }
            }
        }
    }

    fun acceptCandidate(responseID : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            screenState.value = ScreenState.Loading
            val response = acceptCandidateUseCase(responseID)
            if (response !is CustomResult.Failure) {
                loadData()
            } else {
                screenState.value = ScreenState.Failure(1, "Ошибка подключения")
            }
        }
    }

    fun rejectCandidate(responseID : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            screenState.value = ScreenState.Loading
            val response = rejectCandidateUseCase(responseID)
            if (response !is CustomResult.Failure) {
                loadData()
            } else {
                screenState.value = ScreenState.Failure(1, "Ошибка подключения")
            }
        }
    }
}