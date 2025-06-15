package com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet
import com.example.ozonchick_new_version.Data.model.Employer.VacancyDescriptionInsertModel
import com.example.ozonchick_new_version.Data.model.Employer.VacancyUpdateInformation
import com.example.ozonchick_new_version.Data.model.Ktor.AllExperience
import com.example.ozonchick_new_version.Data.model.Ktor.AllTypeUserDefaultResponse
import com.example.ozonchick_new_version.Data.model.Ktor.Experience
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyInfo
import com.example.ozonchick_new_version.Domain.AddVacancyUseCase
import com.example.ozonchick_new_version.Domain.CreateVacancyKtorUseCase
import com.example.ozonchick_new_version.Domain.FieldValidators
import com.example.ozonchick_new_version.Domain.GetAllExperienceKtorUseCase
import com.example.ozonchick_new_version.Domain.GetExperienceUseCase
import com.example.ozonchick_new_version.Domain.GetOrganizationNameUseCase
import com.example.ozonchick_new_version.Domain.GetUserInformationKtorUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EmployerAddVacancyViewModel(
    private val createVacancyKtorUseCase: CreateVacancyKtorUseCase,
    private val getUserInformationUseCase: GetUserInformationKtorUseCase,
    private val getExperienceUseCase: GetAllExperienceKtorUseCase
) : ViewModel() {
    val specialization: MutableState<String> = mutableStateOf("") /*"Программист тестировщик"*/
    val price: MutableState<String> = mutableStateOf("") /*"60000"*/
    val aboutWork: MutableState<String> =
        mutableStateOf("") /*Нужно будет тестировать*/

    val phoneNumber: MutableState<String> = mutableStateOf("") /*+799999999999*/
    val email: MutableState<String> = mutableStateOf("") /*"testimvmeste@mail.ru"*/
    val locationWork: MutableState<String> = mutableStateOf("") /*"Екатеренбург"*/

    val experience: MutableState<String> = mutableStateOf("") /*"Более 5 лет"*/

    val screenState: MutableStateFlow<ScreenState<Boolean>> =
        MutableStateFlow(ScreenState.Loading)

    val confirmCharge: MutableState<Boolean> = mutableStateOf(false)

    val selectedExperience : MutableState<Experience?> = mutableStateOf(null)
    val experienceList: MutableState<List<Experience>> =
        mutableStateOf(emptyList())

    val unCorrectInformation : MutableState<Boolean> = mutableStateOf(false)

    fun loadData () {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getExperienceUseCase()) {
                is CustomResult.Failure -> {
                    Log.d("AddVacancy", "Ошибка")

                    screenState.value = ScreenState.Failure(result.code, result.message)
                }
                is CustomResult.Success<AllExperience> -> {
                    Log.d("AddVacancy", "Инфа подгрузилаась")
                    experienceList.value = result.data.data
                    screenState.value = ScreenState.AwaitingChangeFromUser
                }
            }
        }
    }

    fun addVacancy(navigator: Navigator?) {
        viewModelScope.launch(Dispatchers.IO) {
//            val information = getOrganizationName().await()
            val response = createVacancyKtorUseCase(
                VacancyInfo(
                    about = aboutWork.value.trim(),
                    email = email.value.trim(),
                    experienceId = selectedExperience.value?.id ?: 0,
                    isVisible = true,
                    location = locationWork.value.trim(),
                    phoneNumber = phoneNumber.value.trim(),
                    price = price.value.toInt(),
                    vacancyName = specialization.value
                )
            )
                if (response !is CustomResult.Failure) {
                    screenState.value = ScreenState.Success(true)
                } else {
                    screenState.value = ScreenState.Failure(0, "Ошибка добавления вакансии")
                }
//            if (information !is CustomResult.Failure) {
//
//                )
//
//            } else {
//                screenState.value = ScreenState.Failure(0, "Ошибка добавления вакансии")
//            }
        }
    }


    fun checkFields () : Boolean {
        return if (
            checkEmail(email.value) && checkPhoneNumber(phoneNumber.value) && checkPrice(price.value)
            && checkAboutWork(aboutWork.value)
            && checkLocationWork(locationWork.value) && checkPrice(price.value)
            && checkSpecialization(specialization.value) && checkExperience(selectedExperience.value?.name) == true
        ) {
            true
        }
        else {
            unCorrectInformation.value = true
            false
        }
    }

    fun checkSpecialization (specialization : String) : Boolean {
        return specialization.isNotEmpty()
    }

    fun checkAboutWork (aboutWork : String) : Boolean {
        return aboutWork.isNotEmpty()
    }

    fun checkLocationWork (locationWork : String) : Boolean {
        return locationWork.isNotEmpty()
    }

    fun checkExperience (nameExperience : String?) : Boolean? {
        return nameExperience?.isNotEmpty()
    }

    fun checkEmail (email : String) : Boolean {
        return FieldValidators.checkEmail(email)
    }

    fun checkPhoneNumber (phoneNumber: String) : Boolean {
        return FieldValidators.checkPhoneNumber(phoneNumber)
    }

    fun checkPrice (price : String) : Boolean {
        return FieldValidators.checkDigit(price)
    }
}