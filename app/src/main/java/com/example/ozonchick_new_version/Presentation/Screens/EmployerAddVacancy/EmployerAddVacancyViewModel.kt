package com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.VacancyDescriptionInsertModel
import com.example.ozonchick_new_version.Data.model.VacancyUpdateContact
import com.example.ozonchick_new_version.Data.model.VacancyUpdateInformation
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Domain.AddVacancyUseCase
import com.example.ozonchick_new_version.Domain.GetOrganizationNameUseCase
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesScreen
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployerAddVacancyViewModel(
    private val addVacancyUseCase: AddVacancyUseCase,
    private val getOrganizationNameUseCase: GetOrganizationNameUseCase,
) : ViewModel() {
    val specialization: MutableState<String> = mutableStateOf("") /*"Программист станков"*/
    val price: MutableState<String> = mutableStateOf("") /*"60000"*/
    val aboutWork: MutableState<String> =
        mutableStateOf("") /*"Вам придстоит программировать станки и тд..."*/

    val phoneNumber: MutableState<String> = mutableStateOf("") /*"+74017321154"*/
    val email: MutableState<String> = mutableStateOf("") /*"chunki@mail.ru"*/
    val locationWork: MutableState<String> = mutableStateOf("") /*"Челябинская область"*/

    val experience: MutableState<String> = mutableStateOf("") /*"Более 5 лет"*/

    val screenState: MutableStateFlow<ScreenState<Boolean>> =
        MutableStateFlow(ScreenState.Loading)

    val confirmCharge: MutableState<Boolean> = mutableStateOf(false)

    fun addVacancy(navigator: Navigator?) {
        viewModelScope.launch(Dispatchers.IO) {
            val organizationName = getOrganizationName().await()
            if (organizationName !is CustomResult.Failure) {
                val response = addVacancyUseCase(
                    VacancyUpdateInformation(
                        vakans_name = specialization.value.trim(),
                        vakans_price = price.value.trim(),
                        vakans_location = locationWork.value.trim(),
                        vakans_experience = experience.value.trim(),
                        vakans_mail = email.value.trim(),
                        employer_id = publicUserId.id,
                        vakans_organization = (organizationName as CustomResult.Success).data.trim()
                    ),
                    VacancyDescriptionInsertModel(
                        vakans_id = 0,
                        vakans_number = phoneNumber.value,
                        vakans_mail = email.value,
                        vakans_raspolog = locationWork.value,
                        vakans_aboutWork = aboutWork.value
                    )
                )
                if (response !is CustomResult.Failure) {
                    screenState.value = ScreenState.Success(true)
                } else {
                    screenState.value = ScreenState.Failure(0, "Ошибка добавления вакансии")
                }
            } else {
                screenState.value = ScreenState.Failure(0, "Ошибка добавления вакансии")
            }
        }
    }

    suspend fun getOrganizationName() : Deferred<CustomResult<String>> {
        return coroutineScope { async(Dispatchers.IO) {getOrganizationNameUseCase(publicUserId)} }
    }

    fun checkEmail () : Boolean {
        return email.value.trim().matches(Regex("^[a-z-а-я-0-9]+@[a-z-а-я-0-9]+.[a-z-а-я]{2,}$"))
    }

    fun checkPhoneNumber () : Boolean {
        return phoneNumber.value.trim().matches(Regex("^\\+\\d{11}"))
    }

}