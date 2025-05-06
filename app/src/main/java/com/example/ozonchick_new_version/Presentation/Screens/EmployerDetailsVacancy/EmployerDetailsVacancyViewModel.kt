package com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet
import com.example.ozonchick_new_version.Data.model.VacancyAllInformation
import com.example.ozonchick_new_version.Data.model.Employer.VacancyInsertModel
import com.example.ozonchick_new_version.Data.model.Employer.VacancyUpdateContact
import com.example.ozonchick_new_version.Data.model.VacancyInformation
import com.example.ozonchick_new_version.Domain.FieldValidators
import com.example.ozonchick_new_version.Domain.GetVacancyAllInformationUseCase
import com.example.ozonchick_new_version.Domain.UpdateEmployerVacancyUseCase
import com.example.ozonchick_new_version.Presentation.Models.ChangeVacancyUIModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesScreen
import com.example.ozonchick_new_version.Presentation.States.DialogStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EmployerDetailsVacancyViewModel(
    private val getVacancyAllInformation: GetVacancyAllInformationUseCase,
    private val updateEmployerVacancyUseCase: UpdateEmployerVacancyUseCase,
//    private val getEmployerInformation: GetEmployerInformation,
    private val description : VacancyInformation
) : ViewModel() {
    val specialization : MutableState<String> = mutableStateOf(description.vakans_name)
    val price : MutableState<String> = mutableStateOf(description.vakans_price)
    val aboutWork : MutableState<String> = mutableStateOf("")

    val phoneNumber : MutableState<String> = mutableStateOf("")
    val email : MutableState<String> = mutableStateOf(description.vakans_mail)
    val locationWork : MutableState<String> = mutableStateOf(description.vakans_location)

    val experience : MutableState<String> = mutableStateOf(description.vakans_experience)

    val screenState : MutableStateFlow<ScreenState<VacancyAllInformation>> =
        MutableStateFlow(ScreenState.Loading)

    val confirmCharge : MutableState<Boolean> = mutableStateOf(false)

    val dialogState : MutableState<DialogStates> = mutableStateOf(DialogStates.None)

    val inn : MutableState<String> = mutableStateOf("")
    val name : MutableState<String> = mutableStateOf("")
    val companyName : MutableState<String> = mutableStateOf("")
    val emailEmployer : MutableState<String> = mutableStateOf("")

    val selectedExperience : MutableState<ExperienceGet?> = mutableStateOf(null)
    val experienceList: MutableState<List<ExperienceGet>> =
        mutableStateOf(emptyList())

    fun loadDataForEmployer () {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getVacancyAllInformation(description.vakans_id, description.employer_id ,publicUserId.type is UserType.Admin)) {
                is CustomResult.Failure -> {
                    screenState.value = ScreenState.Failure(result.code, result.message)
                }
                is CustomResult.Success<VacancyAllInformation> -> {
                    aboutWork.value = result.data.contacts.vakans_aboutWork
                    phoneNumber.value = result.data.contacts.vakans_number
                    experienceList.value = result.data.experience
                    selectedExperience.value = experienceList.value.find { item ->
                        item.name == description.vakans_experience
                    }

                    inn.value = result.data.employer?.INN ?: ""
                    name.value = result.data.employer?.name ?: ""
                    companyName.value = result.data.employer?.companyName ?: ""
                    emailEmployer.value = result.data.employer?.email ?: ""

                    screenState.value = ScreenState.Success(result.data)
                }
            }
        }
    }


    fun updateDescriptionInformation(vacancyId: Int, navigator: Navigator?) {
        Log.d("Значение спец", specialization.value)
        val information: ChangeVacancyUIModel = ChangeVacancyUIModel(
            mainInformation = VacancyInsertModel(
                vakans_name = specialization.value,
                vakans_price = price.value,
                vakans_location = locationWork.value,
                vakans_experience = selectedExperience.value?.name ?: "Unknown",
                vakans_mail = email.value
            ),
            description = VacancyUpdateContact(
                vakans_number = phoneNumber.value,
                vakans_mail = email.value,
                vakans_raspolog = locationWork.value,
                vakans_aboutWork = aboutWork.value
            )
        )
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = updateEmployerVacancyUseCase(vacancyId, information)) {
                is CustomResult.Failure -> screenState.value = ScreenState.Failure(result.code, result.message)
                is CustomResult.Success<*> -> navigator?.popUntil{it is EmployerVacanciesScreen}
            }
        }
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