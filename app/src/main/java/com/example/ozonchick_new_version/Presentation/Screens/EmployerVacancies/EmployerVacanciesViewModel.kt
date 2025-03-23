package com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.employerListVacancies
import com.example.ozonchick_new_version.Common.isLocalVersion
import com.example.ozonchick_new_version.Common.localListVacancies
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Domain.DeleteEmployerVacancyUseCase
import com.example.ozonchick_new_version.Domain.GetEmployerVacanciesUseCase
import com.example.ozonchick_new_version.Presentation.Models.ProfileUIModel
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EmployerVacanciesViewModel(
    private val getEmployerVacanciesUseCase: GetEmployerVacanciesUseCase,
    private val deleteEmployerVacancyUseCase: DeleteEmployerVacancyUseCase
) : ViewModel() {
    val screenState : MutableStateFlow<ScreenState<List<VakansInformation>>> =
        MutableStateFlow(ScreenState.Loading)
    val confirmDelete : MutableState<Boolean> = mutableStateOf(false)
    val vacancyId : MutableState<Int> = mutableIntStateOf(0)

    fun loadData() {
        viewModelScope.launch (Dispatchers.IO) {
            val vacancies = getVacancies(publicUserId).await()
            if (vacancies !is CustomResult.Failure) {
                screenState.value = ScreenState.Success((vacancies as CustomResult.Success).data)
            } else {
                screenState.value = ScreenState.Failure(vacancies.code, vacancies.message)
            }

            Log.d("Вакансии работодателя", vacancies.toString())
        }
    }

    fun loadLocalData() {
        val listVacancies = employerListVacancies
        screenState.value = ScreenState.Success(listVacancies)
    }

    suspend fun getVacancies(userId: UserId) : Deferred<CustomResult<List<VakansInformation>>> {
        return coroutineScope { async(Dispatchers.IO) {getEmployerVacanciesUseCase(userId)} }
    }

    fun deleteVacancy(vacancyId : Int) {
        if (isLocalVersion == true) {
            val idEmployer = employerListVacancies.indexOfFirst { it.vakans_id == vacancyId }
            employerListVacancies.removeAt(idEmployer)
            val idVacancies = localListVacancies.indexOfFirst { it.vakans_id == vacancyId }
            localListVacancies.removeAt(idVacancies)
            screenState.value = ScreenState.Success(employerListVacancies)
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                screenState.value = ScreenState.Loading
                val deleteResult = deleteEmployerVacancyUseCase(vacancyId)
                val vacancies = getVacancies(publicUserId).await()
                if (deleteResult !is CustomResult.Failure && vacancies !is CustomResult.Failure) {
                    screenState.value = ScreenState.Success((vacancies as CustomResult.Success).data)
                } else {
                    screenState.value = ScreenState.Failure(1, "Ошибка подключения")
                }
            }
        }
    }
}