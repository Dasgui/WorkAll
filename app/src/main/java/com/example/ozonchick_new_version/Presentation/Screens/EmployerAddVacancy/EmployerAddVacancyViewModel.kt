package com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.VacancyUpdateContact
import com.example.ozonchick_new_version.Data.model.VacancyUpdateInformation
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Domain.AddVacancyUseCase
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesScreen
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployerAddVacancyViewModel(
    private val addVacancyUseCase: AddVacancyUseCase
) : ViewModel() {
    val specialization: MutableState<String> = mutableStateOf("Программист станков")
    val price: MutableState<String> = mutableStateOf("60000")
    val aboutWork: MutableState<String> =
        mutableStateOf("Вам придстоит программировать станки и тд...")

    val phoneNumber: MutableState<String> = mutableStateOf("+74017321154")
    val email: MutableState<String> = mutableStateOf("chunki@mail.ru")
    val locationWork: MutableState<String> = mutableStateOf("Челябинская область")

    val experience: MutableState<String> = mutableStateOf("Более 5 лет")

    val screenState: MutableStateFlow<ScreenState<Boolean>> =
        MutableStateFlow(ScreenState.Loading)

    val confirmCharge: MutableState<Boolean> = mutableStateOf(false)

    fun addVacancy(navigator: Navigator?) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = addVacancyUseCase(
                VacancyUpdateInformation(
                    vakans_name = specialization.value,
                    vakans_price = price.value,
                    vakans_location = locationWork.value,
                    vakans_experience = experience.value,
                    vakans_mail = email.value
                ),
                VacancyUpdateContact(
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
        }
    }
}