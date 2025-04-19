package com.example.ozonchick_new_version.Presentation.Screens.ChangeInformation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.Authentication.UserChangeableInformation
import com.example.ozonchick_new_version.Domain.FieldValidators
import com.example.ozonchick_new_version.Domain.GetUserChangeableInformationUseCase
import com.example.ozonchick_new_version.Domain.UpdateUserInformationUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ChangeInformationViewModel(
    private val getUserChangeableInformation: GetUserChangeableInformationUseCase,
    private val updateUserInformationViewModel: UpdateUserInformationUseCase
) : ViewModel() {
    val screenState: MutableStateFlow<ScreenState<Boolean>> =
        MutableStateFlow(ScreenState.Loading)

    val email : MutableState<String> = mutableStateOf("")
    val password : MutableState<String> = mutableStateOf("")
    val name : MutableState<String> = mutableStateOf("")
    val phoneNumber : MutableState<String> = mutableStateOf("")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUserChangeableInformation(publicUserId.id)
            when (response) {
                is CustomResult.Failure -> screenState.value = ScreenState.Failure(response.code, response.message)
                is CustomResult.Success<UserChangeableInformation> -> {
                    email.value = response.data.email
                    password.value = response.data.password
                    name.value = response.data.name
                    phoneNumber.value = response.data.phoneNumber
                    screenState.value = ScreenState.AwaitingChangeFromUser
                }
            }
        }
    }

    fun updateInformation () {
        if (checkChanges()) {
            viewModelScope.launch (Dispatchers.IO) {
                screenState.value = ScreenState.Loading
                val response = updateUserInformationViewModel.invoke(publicUserId.id, UserChangeableInformation(email.value.trim(), password.value.trim(), name.value.trim(), phoneNumber.value.trim()))
                when (response) {
                    is CustomResult.Failure -> {
                        screenState.value = ScreenState.Failure(1, response.message);
                    }
                    is CustomResult.Success<Boolean> -> {
                        screenState.value = ScreenState.Success(response.data);
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