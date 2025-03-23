package com.example.ozonchick_new_version.Presentation.Screens.ListVacancies

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.localListVacancies
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Domain.AcceptVacancyUseCase
import com.example.ozonchick_new_version.Domain.GetVacanciesUseCase
import com.example.ozonchick_new_version.Presentation.States.DialogStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ListVacanciesViewModel(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val acceptVacancyUseCase: AcceptVacancyUseCase
) : ViewModel() {
    val responseState : MutableStateFlow<ScreenState<List<VakansInformation>>> = MutableStateFlow(ScreenState.Loading)
    val showOkDialog : MutableState<DialogStates> = mutableStateOf(DialogStates.None)

    var listVacancies : MutableState<List<VakansInformation>> = mutableStateOf(listOf())


    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val vacancies = getInformation().await()
            if (vacancies !is CustomResult.Failure) {
                responseState.value = ScreenState.Success((vacancies as CustomResult.Success).data)
                listVacancies.value = vacancies.data
            } else {
                responseState.value = ScreenState.Failure(vacancies.code, vacancies.message)
            }
            Log.d("Получение на откликнувшиеся вакансии", responseState.value.toString())
        }
    }

    fun addVacancy(vacancyId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = acceptVacancyUseCase(publicUserId.id, vacancyId)
            if (response !is CustomResult.Failure) {
                showOkDialog.value = DialogStates.Okay
            } else {
                showOkDialog.value = DialogStates.Error
            }
        }
    }

    fun loadLocalData () {
        responseState.value = ScreenState.Success(localListVacancies)
        listVacancies.value = localListVacancies
    }

    suspend fun getInformation() : Deferred<CustomResult<List<VakansInformation>>> {
        return coroutineScope { async(Dispatchers.IO) { getVacanciesUseCase() } }
    }
}