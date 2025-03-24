package com.example.ozonchick_new_version.Presentation.Screens.SignUp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Data.model.Authentication.AllUserInsertInformation
import com.example.ozonchick_new_version.Domain.RegistrationCandidateUseCase
import com.example.ozonchick_new_version.Domain.RegistrationEmployerUseCase
import com.example.ozonchick_new_version.Domain.RegistrationUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val registrationCandidateUseCase: RegistrationCandidateUseCase,
    private val registrationEmployerUseCase: RegistrationEmployerUseCase,
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    private val _isWrittenCorrectly: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isWrittenCorrectly: StateFlow<Boolean> = _isWrittenCorrectly.asStateFlow()

    fun setIsWrittenCorrectly(newValue: Boolean) {
        _isWrittenCorrectly.value = newValue
    }

    val name : MutableState<String> = mutableStateOf("") /*"Еми"*/
    val secondName : MutableState<String> = mutableStateOf("") /*"Эльмирович"*/

    val phoneNumber : MutableState<String> = mutableStateOf("") /*"+79087027174"*/

    val email : MutableState<String> = mutableStateOf("") /*"gaisovich@inbox.ru"*/

    val password : MutableState<String> = mutableStateOf("") /*"123"*/

    val organizationName : MutableState<String> = mutableStateOf("") /*"ООО Воскресный"*/

    val confirmPassword : MutableState<String> = mutableStateOf("") /*"123"*/


    private val _passwordVisibleFirst: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleFirst: StateFlow<Boolean> = _passwordVisibleFirst.asStateFlow()



    private val _passwordVisibleSecond: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleSecond: StateFlow<Boolean> = _passwordVisibleSecond.asStateFlow()

    val userRole : MutableState<UserType> = mutableStateOf(UserType.Candidate)

    val INN : MutableState<String> = mutableStateOf("") /*123456789012*/

    val signUpState : MutableStateFlow<ScreenState<List<AllUserInsertInformation>>> =
        MutableStateFlow(ScreenState.Loading)


    fun registration() {
        viewModelScope.launch(Dispatchers.IO) {
            val singleName = "${secondName.value.trim()} ${name.value.trim()}"
            when (
                val curResult = if (userRole.value == UserType.Employer) {
                    registrationUseCase(singleName, email.value, password.value, phoneNumber.value, INN.value, organizationName.value)
                } else {
                    registrationUseCase(singleName, email.value.trim(), password.value.trim(), phoneNumber.value.trim())
                }
            ) {
                is CustomResult.Failure -> signUpState.value = ScreenState.Failure(curResult.code, curResult.message)
                is CustomResult.Success -> signUpState.value = ScreenState.Success(data = curResult.data)
            }
        }
    }

    fun registrationCandidate () {
        viewModelScope.launch(Dispatchers.IO) {
            val singleName = "${secondName.value.trim()} ${name.value.trim()}"
            when (val curResult = registrationCandidateUseCase(singleName, email.value.trim(), password.value.trim(), phoneNumber.value.trim())) {
                is CustomResult.Failure -> signUpState.value = ScreenState.Failure(curResult.code, curResult.message)
                is CustomResult.Success -> {
                    signUpState.value = ScreenState.Success(data = curResult.data)
                }
            }
        }
    }

    fun registrationEmployer () {
        viewModelScope.launch (Dispatchers.IO) {
            val singleName = "${secondName.value.trim()} ${name.value.trim()}"
            when (val curResult = registrationEmployerUseCase(singleName, email.value, password.value, phoneNumber.value, INN.value, organizationName.value)) {
                is CustomResult.Failure -> signUpState.value = ScreenState.Failure(curResult.code, curResult.message)
                is CustomResult.Success -> {
                    signUpState.value = ScreenState.Success(data = curResult.data)
                }
            }
        }
    }

    fun checkName () : Boolean {
        return name.value.trim().matches(Regex("[А-Яа-яЁёa-zA-Z]{3,21}"))
    }

    fun checkEmail () : Boolean {
        return email.value.trim().matches(Regex("^[a-z-а-я-0-9]+@[a-z-а-я-0-9]+.[a-z-а-я]{2,}$"))
    }

    fun checkPhoneNumber () : Boolean {
        return phoneNumber.value.trim().matches(Regex("^\\+\\d{11}"))
    }

    fun checkInn () : Boolean {
        return INN.value.trim().matches(Regex("\\d{12}"))
    }

    fun checkPasswordMatch() : Boolean {
        return password.value.trim() == confirmPassword.value.trim()
    }
}