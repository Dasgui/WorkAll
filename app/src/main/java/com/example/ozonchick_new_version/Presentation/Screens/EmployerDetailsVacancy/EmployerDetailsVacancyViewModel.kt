package com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.AllExperience
import com.example.ozonchick_new_version.Data.model.Ktor.Experience
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseInfoStatus
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyInfo
import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyInformation
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyResponse
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyUpdateResponse
import com.example.ozonchick_new_version.Domain.FieldValidators
import com.example.ozonchick_new_version.Domain.GetAllExperienceKtorUseCase
import com.example.ozonchick_new_version.Domain.GetVacancyInformationKtorUseCase
import com.example.ozonchick_new_version.Domain.UpdateVacancyInformationKtorUseCase
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesScreen
import com.example.ozonchick_new_version.Presentation.States.DialogStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EmployerDetailsVacancyViewModel(
    private val id : Int,
    private val getVacancyAllInformation: GetVacancyInformationKtorUseCase,
    private val getAllExperience: GetAllExperienceKtorUseCase,
    private val updateVacancyInformation : UpdateVacancyInformationKtorUseCase
) : ViewModel() {
    val specialization : MutableState<String> = mutableStateOf("")
    val price : MutableState<String> = mutableStateOf("")
    val aboutWork : MutableState<String> = mutableStateOf("")

    val phoneNumber : MutableState<String> = mutableStateOf("")
    val email : MutableState<String> = mutableStateOf("")
    val locationWork : MutableState<String> = mutableStateOf("")

    val experience : MutableState<String> = mutableStateOf("")

    val screenState : MutableStateFlow<ScreenState<VacancyResponse>> =
        MutableStateFlow(ScreenState.Loading)

    val confirmCharge : MutableState<Boolean> = mutableStateOf(false)

    val dialogState : MutableState<DialogStates> = mutableStateOf(DialogStates.None)

    val inn : MutableState<String> = mutableStateOf("")
    val name : MutableState<String> = mutableStateOf("")
    val companyName : MutableState<String> = mutableStateOf("")
    val emailEmployer : MutableState<String> = mutableStateOf("")

    val selectedExperience : MutableState<Experience?> = mutableStateOf(null)
    val experienceList: MutableState<List<Experience>> =
        mutableStateOf(emptyList())

    val vacancyVisible : MutableState<Boolean> = mutableStateOf(false)

    fun loadDataForEmployer () {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getVacancyAllInformation(id)

            when (result) {
                is CustomResult.Failure -> {
                    screenState.value = ScreenState.Failure(result.code, result.message)
                }
                is CustomResult.Success<VacancyResponse> -> {
                    val experience = getAllExperience()
                    when (experience) {
                        is CustomResult.Failure -> ScreenState.Failure(experience.code, experience.message)
                        is CustomResult.Success<AllExperience> -> {
                            specialization.value = result.data.vacancyInfo.name
                            price.value = result.data.vacancyInfo.price.toString()
                            email.value = result.data.vacancyInfo.email
                            locationWork.value = result.data.vacancyInfo.location
                            aboutWork.value = result.data.vacancyInfo.aboutWork
                            phoneNumber.value = result.data.vacancyInfo.phoneNumber
                            experienceList.value = experience.data.data
                            selectedExperience.value = experienceList.value.find { item ->
                                item.name == result.data.vacancyInfo.experience.name
                            }

                            inn.value = result.data.vacancyInfo.employee.inn
                            name.value = result.data.vacancyInfo.name
                            companyName.value = result.data.vacancyInfo.employee.nameOrganization
                            emailEmployer.value = result.data.vacancyInfo.email
                            vacancyVisible.value = result.data.vacancyInfo.isVisible
                            screenState.value = ScreenState.Success(result.data)
                        }
                    }
                }
            }
        }
    }


    fun updateDescriptionInformation(vacancyId: Int, navigator: Navigator?) {
        val information = VacancyUpdateResponse(
            about = aboutWork.value,
            email = email.value,
            experienceId = selectedExperience.value?.id ?: 0,
            isVisible = vacancyVisible.value,
            location = locationWork.value,
            phoneNumber = phoneNumber.value,
            price = price.value.toInt(),
            vacancyName = name.value,
            id = vacancyId
        )
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = updateVacancyInformation(information)) {
                is CustomResult.Failure -> screenState.value = ScreenState.Failure(result.code, result.message)
                is CustomResult.Success<ResponseInfoStatus> -> navigator?.popUntil{it is EmployerVacanciesScreen}
            }
        }
    }

    fun checkFields() : Boolean {
        return checkEmail() && checkPhoneNumber() && checkDigits()
                && aboutWork.value.isNotEmpty() && locationWork.value.isNotEmpty()
                && selectedExperience.value != null && name.value.isNotEmpty()
                && specialization.value.isNotEmpty()
    }

    fun checkEmail () : Boolean {
        return FieldValidators.checkEmail(email.value)
    }

    fun checkDigits () : Boolean {
        return FieldValidators.checkDigit(price.value)
    }

    fun checkPhoneNumber () : Boolean {
        return FieldValidators.checkPhoneNumber(phoneNumber.value)
    }
}