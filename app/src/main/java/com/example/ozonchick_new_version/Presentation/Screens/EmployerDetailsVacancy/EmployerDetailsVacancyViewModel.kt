package com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Domain.GetContactInformationUseCase
import com.example.ozonchick_new_version.Domain.UpdateEmployerVacancyUseCase
import com.example.ozonchick_new_version.Presentation.Models.ChangeVacancyUIModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesScreen
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EmployerDetailsVacancyViewModel(
    private val descriptionId : Int,
    private val getContactInformationUseCase: GetContactInformationUseCase,
    private val updateEmployerVacancyUseCase: UpdateEmployerVacancyUseCase
) : ViewModel() {
    val specialization : MutableState<String> = mutableStateOf("")
    val price : MutableState<String> = mutableStateOf("")
    val aboutWork : MutableState<String> = mutableStateOf("")

    val phoneNumber : MutableState<String> = mutableStateOf("")
    val email : MutableState<String> = mutableStateOf("")
    val locationWork : MutableState<String> = mutableStateOf("")

    val experience : MutableState<String> = mutableStateOf("")

    val screenState : MutableStateFlow<ScreenState<List<Vakans_contacts>>> =
        MutableStateFlow(ScreenState.Loading)

    val confirmCharge : MutableState<Boolean> = mutableStateOf(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val description = getDescriptionInformation(descriptionId).await()
            if (description !is CustomResult.Failure) {
                aboutWork.value = (description as CustomResult.Success).data[0].vakans_aboutWork
                phoneNumber.value = description.data[0].vakans_number
                screenState.value = ScreenState.Success(description.data)
            } else {
                screenState.value = ScreenState.Failure(description.code, description.message)
            }
        }
    }

    suspend fun getDescriptionInformation(descriptionId : Int) : Deferred<CustomResult<List<Vakans_contacts>>> {
        return coroutineScope { async (Dispatchers.IO) { getContactInformationUseCase(descriptionId) } }
    }

    fun updateDescriptionInformation(vacancyId: Int, information: ChangeVacancyUIModel, navigator: Navigator?) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = updateEmployerVacancyUseCase(vacancyId, information)
            if (result !is CustomResult.Failure) {
//                navigator?.replaceAll(EmployerVacanciesScreen())
            } else {
                screenState.value = ScreenState.Failure(result.code, result.message)
            }
        }
    }

    fun checkEmail () : Boolean {
        return email.value.trim().matches(Regex("^[a-z-а-я-0-9]+@[a-z-а-я-0-9]+.[a-z-а-я]{2,}$"))
    }

    fun checkPhoneNumber () : Boolean {
        return phoneNumber.value.trim().matches(Regex("^\\+\\d{11}"))
    }
}