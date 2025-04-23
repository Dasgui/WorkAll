package com.example.ozonchick_new_version.Presentation.Screens.Search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data.model.ListOfVacancies.StatesOfGetVacancies
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.network.supabase
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val search = mutableStateOf("")
    val minSalaryText = mutableStateOf("")

    val listExperience: MutableState<List<String>> = mutableStateOf(
        listOf(
            "Не требуется",
            "Более 3 лет",
            "Более 5 лет"
        )
    )

    val checkSalary: MutableState<Boolean> = mutableStateOf(false)
    val checkExperience : MutableState<Boolean> = mutableStateOf(false)

    val checkListExperience: MutableState<Boolean> = mutableStateOf(false)

    val experience: MutableState<String> = mutableStateOf(listExperience.value.firstOrNull() ?: "Не требуется")



    fun getStatesOfGetVacancies () : StatesOfGetVacancies {
        return StatesOfGetVacancies.Filter(
            search = search.value,
            filterSalary = if (checkSalary.value) minSalaryText.value else "",
            filterExperience = if (checkExperience.value) experience.value else ""
        )
    }

    fun loadFilters (localState : StatesOfGetVacancies.Filter) {
        if (localState.filterExperience.isNotEmpty()) {
           checkExperience.value = true
        }
        if (localState.filterSalary.isNotEmpty()) {
            checkSalary.value = true
        }
        search.value = localState.search
        experience.value = localState.filterExperience
        minSalaryText.value = localState.filterSalary
    }

    fun resetFilters () {
        checkExperience.value = false
        checkSalary.value = false
        minSalaryText.value = ""
        experience.value = ""
        search.value = ""
    }

    fun getSalaryVacancy() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = supabase.from("vakans").select {
                filter {
//                    rangeGte("vakans_price", minSalaryText to maxSalaryText)
                    gte("vakans_price", minSalaryText.value)
                }
            }.decodeList<VakansInformation>()
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
            }.decodeList<VakansInformation>()
            Log.d("Вакансии при поиске", response.toString())
        }
    }
}