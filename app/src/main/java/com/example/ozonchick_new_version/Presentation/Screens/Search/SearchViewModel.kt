package com.example.ozonchick_new_version.Presentation.Screens.Search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.ListOfVacancies.StatesOfGetVacancies
import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet
import com.example.ozonchick_new_version.Data.model.VacancyInformation
import com.example.ozonchick_new_version.Data.network.supabase
import com.example.ozonchick_new_version.Domain.GetExperienceUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getExperienceUseCase: GetExperienceUseCase
) : ViewModel() {

    val search = mutableStateOf("")
    val minSalaryText = mutableStateOf("")


    val checkSalary: MutableState<Boolean> = mutableStateOf(false)
    val checkExperience: MutableState<Boolean> = mutableStateOf(false)

    val checkListExperience: MutableState<Boolean> = mutableStateOf(false)

    val experienceList: MutableState<List<ExperienceGet>> =
        mutableStateOf(emptyList())
    val selectedExperience: MutableState<ExperienceGet?> = mutableStateOf(null)

    val screenState: MutableStateFlow<ScreenState<List<ExperienceGet>>> = MutableStateFlow(
        ScreenState.AwaitingChangeFromUser
    )


    fun getStatesOfGetVacancies(): StatesOfGetVacancies {
        return StatesOfGetVacancies.Filter(
            search = search.value,
            filterSalary = if (checkSalary.value) minSalaryText.value else "",
            filterExperience = selectedExperience.value ?: ExperienceGet(0, ""),
            filterExperienceList = experienceList.value
        )
    }

    fun loadExperience() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getExperienceUseCase()) {
                is CustomResult.Failure -> screenState.value = ScreenState.Failure(result.code, result.message)
                is CustomResult.Success<List<ExperienceGet>> -> {
                    screenState.value = ScreenState.AwaitingChangeFromUser
                    experienceList.value = result.data
                }
            }
        }
    }

    fun loadFilters(localState: StatesOfGetVacancies.Filter) {
        if (localState.filterExperience.name.isNotEmpty()) {
            checkExperience.value = true
        }
        if (localState.filterSalary.isNotEmpty()) {
            checkSalary.value = true
        }
        search.value = localState.search
        selectedExperience.value = localState.filterExperience
        experienceList.value = localState.filterExperienceList
        minSalaryText.value = localState.filterSalary
    }

    fun resetFilters() {
        checkExperience.value = false
        checkSalary.value = false
        minSalaryText.value = ""
        selectedExperience.value = null
        search.value = ""
    }

    fun getSalaryVacancy() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = supabase.from("vakans").select {
                filter {
//                    rangeGte("vakans_price", minSalaryText to maxSalaryText)
                    gte("vakans_price", minSalaryText.value)
                }
            }.decodeList<VacancyInformation>()
            Log.d("Вакансии по зарплате", response.toString())
        }
    }

    fun searchVacancy() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = supabase.from("vakans").select {
                filter {
                    ilike("vakans_name", "%${search.value}%")
                    gte("vakans_price", minSalaryText.value)
                }
            }.decodeList<VacancyInformation>()
            Log.d("Вакансии при поиске", response.toString())
        }
    }
}