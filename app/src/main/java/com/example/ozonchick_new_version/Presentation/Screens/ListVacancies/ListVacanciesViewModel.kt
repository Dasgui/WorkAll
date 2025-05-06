package com.example.ozonchick_new_version.Presentation.Screens.ListVacancies

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.ListOfVacancies.StatesOfGetVacancies
import com.example.ozonchick_new_version.Data.model.VacancyInformation
import com.example.ozonchick_new_version.Domain.AcceptVacancyUseCase
import com.example.ozonchick_new_version.Domain.GetVacanciesUseCase
import com.example.ozonchick_new_version.Domain.SearchVacanciesUseCase
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
    private val acceptVacancyUseCase: AcceptVacancyUseCase,
    private val searchVacanciesUseCase: SearchVacanciesUseCase
) : ViewModel() {
    val responseState : MutableStateFlow<ScreenState<List<VacancyInformation>>> = MutableStateFlow(ScreenState.Loading)
    val statesOfGetVacancies : MutableState<StatesOfGetVacancies> = mutableStateOf(
        StatesOfGetVacancies.Normal)

    val search = mutableStateOf("Тестировщик")

    val minSalaryText = mutableStateOf("40000")
    val maxSalaryText = mutableStateOf("0")

    val minSalary = mutableIntStateOf(0)
    val maxSalary = mutableIntStateOf(0)
    val currentSalary = mutableFloatStateOf(0f)

    var listVacancies : MutableState<List<VacancyInformation>> = mutableStateOf(listOf())

    val dialogStates : MutableState<DialogStates> = mutableStateOf(DialogStates.None)



    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val localState = statesOfGetVacancies.value) {
                is StatesOfGetVacancies.Filter -> {
                    val response = searchVacanciesUseCase(localState)
                    when (response) {
                        is CustomResult.Failure -> responseState.value = ScreenState.Failure(response.code, response.message)
                        is CustomResult.Success<List<VacancyInformation>> -> {
                            responseState.value = ScreenState.Success(response.data)
                            listVacancies.value = response.data
                        }
                    }
                    Log.d("Вакансии при поиске", response.toString())
                }
                StatesOfGetVacancies.Normal -> {
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
        }
    }


    fun addVacancy(vacancyId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = acceptVacancyUseCase(publicUserId.id, vacancyId)
            if (response !is CustomResult.Failure) {
                Log.d("Всё окей", "Всё окей")
                dialogStates.value = DialogStates.Okay
            } else {
                Log.d("Всё плохо", "Всё плохо")
                dialogStates.value = DialogStates.Error
            }
        }
    }

    suspend fun getInformation() : Deferred<CustomResult<List<VacancyInformation>>> {
        return coroutineScope { async(Dispatchers.IO) { getVacanciesUseCase() } }
    }
}