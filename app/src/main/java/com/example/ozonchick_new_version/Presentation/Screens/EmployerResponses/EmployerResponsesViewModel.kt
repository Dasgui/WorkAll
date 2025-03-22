package com.example.ozonchick_new_version.Presentation.Screens.EmployerResponses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.Authentication.Candidate.Candidate
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Domain.AcceptCandidateUseCase
import com.example.ozonchick_new_version.Domain.GetUsersForEmployerUseCase
import com.example.ozonchick_new_version.Domain.RejectCandidateUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EmployerResponsesViewModel(
    private val vacancyId : Int,
    private val getUsersForEmployerUseCase: GetUsersForEmployerUseCase,
    private val acceptCandidateUseCase: AcceptCandidateUseCase,
    private val rejectCandidateUseCase: RejectCandidateUseCase
) : ViewModel() {
    val screenState : MutableStateFlow<ScreenState<List<Candidate>>> =
        MutableStateFlow(ScreenState.Loading)
    fun loadData () {
        viewModelScope.launch(Dispatchers.IO) {
            val users = getUsers(vacancyId).await()
            if (users !is CustomResult.Failure) {
                screenState.value = ScreenState.Success((users as CustomResult.Success).data)
            } else {
                screenState.value = ScreenState.Failure(users.code, users.message)
            }
        }
    }
    suspend fun getUsers(vacancyId : Int) : Deferred<CustomResult<List<Candidate>>> {
        return coroutineScope { async(Dispatchers.IO) { getUsersForEmployerUseCase(vacancyId) } }
    }
    fun acceptCandidate(candidateId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            screenState.value = ScreenState.Loading
            val response = acceptCandidateUseCase(candidateId)
            if (response !is CustomResult.Failure) {
                loadData()
            } else {
                screenState.value = ScreenState.Failure(1, "Ошибка подключения")
            }
        }
    }

    fun rejectCandidate(candidateId : Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            screenState.value = ScreenState.Loading
//            val response = rejectCandidate(candidateId)
//            if (response !is CustomResult.Failure) {
//                loadData()
//            } else {
//                screenState.value = ScreenState.Failure(1, "Ошибка подключения")
//            }
//        }
    }
}