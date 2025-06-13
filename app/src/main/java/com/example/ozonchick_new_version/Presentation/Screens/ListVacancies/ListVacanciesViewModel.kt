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
import com.example.ozonchick_new_version.Data.model.Ktor.VacanciesResponse
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyOtklikResponse
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyResponse
import com.example.ozonchick_new_version.Data.model.ListOfVacancies.StatesOfGetVacancies
import com.example.ozonchick_new_version.Data.model.VacancyInformation
import com.example.ozonchick_new_version.Domain.AcceptVacancyUseCase
import com.example.ozonchick_new_version.Domain.AddVacancyResponseKtorUseCase
import com.example.ozonchick_new_version.Domain.FilterVacanciesKtorUseCase
import com.example.ozonchick_new_version.Domain.FindVacanciesKtorUseCase
import com.example.ozonchick_new_version.Domain.GetVacanciesKtorUseCase
import com.example.ozonchick_new_version.Domain.GetVacanciesUseCase
import com.example.ozonchick_new_version.Domain.SearchVacanciesKtorUseCase
import com.example.ozonchick_new_version.Domain.SearchVacanciesUseCase
import com.example.ozonchick_new_version.Presentation.States.DialogStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListVacanciesViewModel(
    private val getVacanciesKtorUseCase: GetVacanciesKtorUseCase,
    private val addVacancyUseCase: AddVacancyResponseKtorUseCase,
    private val searchVacanciesUseCase: SearchVacanciesKtorUseCase,
    private val filterVacanciesUseCase: FilterVacanciesKtorUseCase,
    private val findVacanciesUseCase : FindVacanciesKtorUseCase
) : ViewModel() {
    val responseState : MutableStateFlow<ScreenState<VacanciesResponse>> = MutableStateFlow(ScreenState.Loading)
    val statesOfGetVacancies : MutableState<StatesOfGetVacancies> = mutableStateOf(
        StatesOfGetVacancies.Normal)

    val search = mutableStateOf("Тестировщик")

    val minSalaryText = mutableStateOf("40000")
    val maxSalaryText = mutableStateOf("0")

    val minSalary = mutableIntStateOf(0)
    val maxSalary = mutableIntStateOf(0)
    val currentSalary = mutableFloatStateOf(0f)

    var listVacancies : MutableState<VacanciesResponse> = mutableStateOf(VacanciesResponse("", emptyList()))

    val dialogStates : MutableState<DialogStates> = mutableStateOf(DialogStates.None)



    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val localState = statesOfGetVacancies.value) {
                is StatesOfGetVacancies.Filter -> {
//                    when (val response = if (localState.search.isEmpty()) filterVacanciesUseCase(localState) else searchVacanciesUseCase(localState.search) ) {
//                        is CustomResult.Failure -> responseState.value = ScreenState.Failure(response.code, response.message)
//                        is CustomResult.Success<VacanciesResponse> -> {
//                            responseState.value = ScreenState.Success(response.data)
//                            listVacancies.value = response.data
//                        }
//                    }

                    when (val response = findVacanciesUseCase(localState)) {
                        is CustomResult.Failure -> responseState.value = ScreenState.Failure(response.code, response.message)
                        is CustomResult.Success<VacanciesResponse> -> {
                            responseState.value = ScreenState.Success(response.data)
                            listVacancies.value = response.data
                        }
                    }

//                    val response = searchVacanciesUseCase(localState)
//                    when (response) {
//                        is CustomResult.Failure -> responseState.value = ScreenState.Failure(response.code, response.message)
//                        is CustomResult.Success<List<VacancyInformation>> -> {
//                            responseState.value = ScreenState.Success(response.data)
//                            listVacancies.value = response.data
//                        }
//                    }
//                    Log.d("Вакансии при поиске", response.toString())
                }
                StatesOfGetVacancies.Normal -> {
                    when (val curResult = getVacanciesKtorUseCase(1, 56)) {
                        is CustomResult.Failure -> responseState.value = ScreenState.Failure(curResult.code, curResult.message)
                        is CustomResult.Success<VacanciesResponse> -> {
                            responseState.value = ScreenState.Success(curResult.data)
                            listVacancies.value = curResult.data
                        }
                    }
                    Log.d("Получение на откликнувшиеся вакансии", responseState.value.toString())
                }
            }
        }
    }


    fun addVacancy(vacancyId : Int) {
        viewModelScope.launch {
            when(val response = addVacancyUseCase(vacancyId)) {
                is CustomResult.Failure -> {
                    if (response.code == 500) {
                        dialogStates.value = DialogStates.Error
                    } else {
                        responseState.value = ScreenState.Failure(response.code, response.message)
                    }
                }
                is CustomResult.Success<VacancyOtklikResponse> -> {
                    Log.d("В ListVacanciesViewModel", "Okay")
                    dialogStates.value = DialogStates.Okay
                }
            }
        }
    }
}