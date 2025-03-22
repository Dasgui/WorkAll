package com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Domain.GetContactInformationUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EmployerDetailsVacancyViewModel(
    private val descriptionId : Int,
    private val getContactInformationUseCase: GetContactInformationUseCase
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

    fun checkEmail () : Boolean {
        return email.value.trim().matches(Regex("^[a-z-а-я-0-9]+@[a-z-а-я-0-9]+.[a-z-а-я]{2,}$"))
    }

    fun checkPhoneNumber () : Boolean {
        return phoneNumber.value.trim().matches(Regex("^\\+\\d{11}"))
    }
}