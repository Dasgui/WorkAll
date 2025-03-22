package com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.VakansInformation
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
    private val getEmployerVacanciesUseCase: GetEmployerVacanciesUseCase
) : ViewModel() {
    val screenState : MutableStateFlow<ScreenState<List<VakansInformation>>> =
        MutableStateFlow(ScreenState.Loading)
    init {
        viewModelScope.launch (Dispatchers.IO) {
            val vacancies = getVacancies(publicUserId).await()
            screenState.value = ScreenState.Success((vacancies as CustomResult.Success).data)
            Log.d("Вакансии работодателя", vacancies.toString())
        }
    }
    suspend fun getVacancies(userId: UserId) : Deferred<CustomResult<List<VakansInformation>>> {
        return coroutineScope { async(Dispatchers.IO) {getEmployerVacanciesUseCase(userId)} }
    }
}