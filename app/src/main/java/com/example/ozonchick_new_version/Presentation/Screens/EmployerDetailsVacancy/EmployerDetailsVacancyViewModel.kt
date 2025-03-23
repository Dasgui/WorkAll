package com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.isLocalVersion
import com.example.ozonchick_new_version.Common.localListContacts
import com.example.ozonchick_new_version.Common.localListVacancies
import com.example.ozonchick_new_version.Data.model.VacancyInsertModel
import com.example.ozonchick_new_version.Data.model.VacancyUpdateContact
import com.example.ozonchick_new_version.Data.model.VacancyUpdateInformation
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Domain.GetContactInformationUseCase
import com.example.ozonchick_new_version.Domain.UpdateEmployerVacancyUseCase
import com.example.ozonchick_new_version.Presentation.Models.ChangeVacancyUIModel
import com.example.ozonchick_new_version.Presentation.Models.EmployerDetailsVacancy
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesScreen
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployerDetailsVacancyViewModel(
    private val descriptionId : Int,
    private val getContactInformationUseCase: GetContactInformationUseCase,
    private val updateEmployerVacancyUseCase: UpdateEmployerVacancyUseCase,
    description : VakansInformation
) : ViewModel() {
    val specialization : MutableState<String> = mutableStateOf(description.vakans_name)
    val price : MutableState<String> = mutableStateOf(description.vakans_price)
    val aboutWork : MutableState<String> = mutableStateOf("")

    val phoneNumber : MutableState<String> = mutableStateOf("")
    val email : MutableState<String> = mutableStateOf(description.vakans_mail)
    val locationWork : MutableState<String> = mutableStateOf(description.vakans_location)

    val experience : MutableState<String> = mutableStateOf(description.vakans_experience)

    val screenState : MutableStateFlow<ScreenState<List<Vakans_contacts>>> =
        MutableStateFlow(ScreenState.Loading)

    val confirmCharge : MutableState<Boolean> = mutableStateOf(false)


    init {
        if (isLocalVersion == true) {
            loadLocalData()

        } else {
            loadData ()
        }
    }
    fun loadData () {
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

    fun loadLocalData () {
        val item = localListContacts.find { it.vakans_id == descriptionId }
        aboutWork.value = item?.vakans_aboutWork ?: ""
        phoneNumber.value = item?.vakans_number ?: ""
    }

    suspend fun getDescriptionInformation(descriptionId : Int) : Deferred<CustomResult<List<Vakans_contacts>>> {
        return coroutineScope { async (Dispatchers.IO) { getContactInformationUseCase(descriptionId) } }
    }

    fun updateDescriptionInformation(vacancyId: Int, navigator: Navigator?) {
        if (isLocalVersion == true) {
            var item = localListContacts.find { it.vakans_id == descriptionId }
            localListVacancies[(item?.vakans_id ?: 0) - 1].apply {
                vakans_mail = email.value
                vakans_price = price.value
                vakans_location = locationWork.value
                vakans_name = specialization.value
                vakans_experience = experience.value
            }
            localListContacts[(item?.vakans_id ?: 0) - 1].apply {
                vakans_number = phoneNumber.value
                vakans_aboutWork = aboutWork.value
                vakans_mail = email.value
                vakans_raspolog = locationWork.value
            }

            navigator?.popUntil{it is EmployerVacanciesScreen}
        } else {
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
                val result = updateEmployerVacancyUseCase(vacancyId, information)
                Log.d("Результат update", information.toString())
                if (result !is CustomResult.Failure) {
                    withContext(Dispatchers.Main) { navigator?.popUntil{it is EmployerVacanciesScreen} }
                } else {
                    screenState.value = ScreenState.Failure(result.code, result.message)
                }
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