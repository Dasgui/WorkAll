package com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.Ktor.EmployerVacancies
import com.example.ozonchick_new_version.Data.model.VacancyInformation
import com.example.ozonchick_new_version.Domain.DeleteEmployerVacancyUseCase
import com.example.ozonchick_new_version.Domain.DeleteVacancyKtorUseCase
import com.example.ozonchick_new_version.Domain.GetEmployerVacanciesKtorUseCase
import com.example.ozonchick_new_version.Domain.GetEmployerVacanciesUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EmployerVacanciesViewModel(
    private val getEmployerVacanciesUseCase: GetEmployerVacanciesKtorUseCase,
    private val deleteVacancyUseCase: DeleteVacancyKtorUseCase
) : ViewModel() {
    val screenState: MutableStateFlow<ScreenState<EmployerVacancies>> =
        MutableStateFlow(ScreenState.Loading)
    val confirmDelete: MutableState<Boolean> = mutableStateOf(false)
    val vacancyId: MutableState<Int> = mutableIntStateOf(0)

    val visibleButtons: MutableState<Boolean> = mutableStateOf(true)

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {

            when (val curResult = getVacancies().await()) {
                is CustomResult.Failure -> {
                    Log.d("Ошибка получ вакансии", "")
                    screenState.value = ScreenState.Failure(curResult.code, curResult.message)
                }

                is CustomResult.Success<EmployerVacancies> -> {
                    if (curResult.data.vacanciesInfo == null) {
                        screenState.value = ScreenState.AwaitingChangeFromUser
                    } else {
                        screenState.value = ScreenState.Success(curResult.data)
                    }

                    Log.d("Вакансии работодателя", curResult.data.toString())
                }
            }
        }
    }

    suspend fun getVacancies(): Deferred<CustomResult<EmployerVacancies>> {
        return coroutineScope { async(Dispatchers.IO) { getEmployerVacanciesUseCase() } }
    }

    fun deleteVacancy(vacancyId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            screenState.value = ScreenState.Loading
            val deleteResult = deleteVacancyUseCase(vacancyId)
            val vacancies = getVacancies().await()
            if (deleteResult !is CustomResult.Failure && vacancies !is CustomResult.Failure) {
                screenState.value = ScreenState.Success((vacancies as CustomResult.Success).data)
            } else {
                screenState.value = ScreenState.Failure(1, "Ошибка подключения")
            }
        }
    }
}