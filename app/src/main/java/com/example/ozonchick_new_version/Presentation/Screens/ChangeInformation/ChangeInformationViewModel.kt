package com.example.ozonchick_new_version.Presentation.Screens.ChangeInformation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Data.model.Authentication.CandidateChangeableInformation
import com.example.ozonchick_new_version.Data.model.Ktor.AllTypeUserDefaultResponse
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.CandidateInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.EmployerInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.DefaultCandidateResponse
import com.example.ozonchick_new_version.Data.model.Ktor.DefaultEmployerResponse
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseInfoStatus
import com.example.ozonchick_new_version.Domain.FieldValidators
import com.example.ozonchick_new_version.Domain.GetUserInformationKtorUseCase
import com.example.ozonchick_new_version.Domain.UpdateUserInformationKtorUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ChangeInformationViewModel(
    private val getUserInformationKtorUseCase: GetUserInformationKtorUseCase,
    private val updateUserInformationViewModel: UpdateUserInformationKtorUseCase
) : ViewModel() {
    val screenState: MutableStateFlow<ScreenState<Boolean>> =
        MutableStateFlow(ScreenState.Loading)

    val email : MutableState<String> = mutableStateOf("")
    val password : MutableState<String> = mutableStateOf("")
    val name : MutableState<String> = mutableStateOf("")
    val phoneNumber : MutableState<String> = mutableStateOf("")
    val statusId : MutableState<Int> = mutableIntStateOf(0)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUserInformationKtorUseCase(localUserInformation.id, localUserInformation.type)
            when (response) {
                is CustomResult.Failure -> screenState.value = ScreenState.Failure(response.code, response.message)
                is CustomResult.Success<AllTypeUserDefaultResponse> -> {
                    when (response.data) {
                        is DefaultCandidateResponse -> {
                            email.value = response.data.candidateInfo.email
                            password.value = response.data.candidateInfo.password
                            name.value = response.data.candidateInfo.name
                            phoneNumber.value = response.data.candidateInfo.phoneNumber

                            statusId.value = response.data.candidateInfo.statusInfo.id ?: 0
                        }
                        is DefaultEmployerResponse -> {
                            email.value = response.data.employerInfo.email
                            password.value = response.data.employerInfo.password
                            name.value = response.data.employerInfo.nameOrganization
                            phoneNumber.value = response.data.employerInfo.phoneNumber

                            statusId.value = response.data.employerInfo.status.id ?: 0
                        }
                    }
                    screenState.value = ScreenState.AwaitingChangeFromUser
                }
            }
        }
    }

    fun updateInformation () {
        if (checkChanges()) {
            viewModelScope.launch (Dispatchers.IO) {
                screenState.value = ScreenState.Loading
                val response = updateUserInformationViewModel.invoke(CandidateInsertInformation(email.value.trim(), name.value.trim(), password.value.trim(), phoneNumber.value.trim(), statusId.value),
                    EmployerInsertInformation(email.value.trim(), inn = "", name.value.trim(), password.value.trim(), phoneNumber.value.trim(), statusId.value))
                when (response) {
                    is CustomResult.Failure -> {
                        screenState.value = ScreenState.Failure(1, response.message);
                    }
                    is CustomResult.Success<ResponseInfoStatus> -> {
                        localUserInformation.name = name.value.trim()
                        screenState.value = ScreenState.Success(true)
                    }
                }
            }
        } else {
            screenState.value = ScreenState.Error
        }
    }

    fun checkChanges() : Boolean {
        return FieldValidators.checkName(name.value) && FieldValidators.checkEmail(email.value)
                && FieldValidators.checkPhoneNumber(phoneNumber.value) && FieldValidators.checkPassword(password.value)
//        if (checkName() && checkEmail() && checkPhoneNumber()) {
//            Log.d("Изменение информации о пользователе", "Всё успешно измененно")
//        } else {
//            Log.d("Изменение информации о пользователе", "Всё пошло на перекосяк")
//        }
    }

}