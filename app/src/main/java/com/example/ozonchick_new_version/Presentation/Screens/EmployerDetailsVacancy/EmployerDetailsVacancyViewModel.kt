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
import com.example.ozonchick_new_version.Data.model.Authentication.Employer.Employer
import com.example.ozonchick_new_version.Data.model.VacancyAllInformation
import com.example.ozonchick_new_version.Data.model.VacancyInsertModel
import com.example.ozonchick_new_version.Data.model.VacancyUpdateContact
import com.example.ozonchick_new_version.Data.model.VacancyUpdateInformation
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Domain.FieldValidators
import com.example.ozonchick_new_version.Domain.GetContactInformationUseCase
import com.example.ozonchick_new_version.Domain.GetEmployerInformation
import com.example.ozonchick_new_version.Domain.GetVacancyAllInformationUseCase
import com.example.ozonchick_new_version.Domain.UpdateEmployerVacancyUseCase
import com.example.ozonchick_new_version.Presentation.Models.ChangeVacancyUIModel
import com.example.ozonchick_new_version.Presentation.Models.EmployerDetailsVacancy
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesScreen
import com.example.ozonchick_new_version.Presentation.States.DialogStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployerDetailsVacancyViewModel(
    private val getVacancyAllInformation: GetVacancyAllInformationUseCase,
    private val updateEmployerVacancyUseCase: UpdateEmployerVacancyUseCase,
//    private val getEmployerInformation: GetEmployerInformation,
    private val description : VakansInformation
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

    init {
        loadDataForEmployer()

    }

    fun loadDataForEmployer () {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getVacancyAllInformation(description.vakans_id, description.employer_id ,publicUserId.type is UserType.Admin)) {
                is CustomResult.Failure -> {
                    screenState.value = ScreenState.Failure(result.code, result.message)
                }
                is CustomResult.Success<VacancyAllInformation> -> {
                    aboutWork.value = result.data.contacts.vakans_aboutWork
                    phoneNumber.value = result.data.contacts.vakans_number
                    inn.value = result.data.employer?.INN ?: ""
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
                vakans_experience = experience.value,
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

    fun checkPhoneNumber () : Boolean {
        return FieldValidators.checkPhoneNumber(phoneNumber.value)
    }
}