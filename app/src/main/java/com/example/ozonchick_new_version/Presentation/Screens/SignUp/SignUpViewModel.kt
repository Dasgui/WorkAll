package com.example.ozonchick_new_version.Presentation.Screens.SignUp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Data.model.Authentication.AllUserInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.AllTypeUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.CandidateInfo
import com.example.ozonchick_new_version.Data.strings.AuthenticationRegexPatterns
import com.example.ozonchick_new_version.Domain.RegistrationCandidateUseCase
import com.example.ozonchick_new_version.Domain.RegistrationEmployerUseCase
import com.example.ozonchick_new_version.Domain.RegistrationKtorCandidateUseCase
import com.example.ozonchick_new_version.Domain.RegistrationKtorEmployerUseCase
import com.example.ozonchick_new_version.Domain.RegistrationUseCase
import com.example.ozonchick_new_version.Presentation.States.DialogStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val registrationKtorCandidateUseCase : RegistrationKtorCandidateUseCase,
    private val registrationKtorEmployerUseCase: RegistrationKtorEmployerUseCase,
) : ViewModel() {

    private val _isWrittenCorrectly: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isWrittenCorrectly: StateFlow<Boolean> = _isWrittenCorrectly.asStateFlow()

    fun setIsWrittenCorrectly(newValue: Boolean) {
        _isWrittenCorrectly.value = newValue
    }

    val buttonEnabled : MutableState<Boolean> = mutableStateOf(true)

    val name : MutableState<String> = mutableStateOf("testmobile")
    val secondName : MutableState<String> = mutableStateOf("testmobile")

    val phoneNumber : MutableState<String> = mutableStateOf("+79087027174")

    val email : MutableState<String> = mutableStateOf("testmobile@mail.ru")

    val password : MutableState<String> = mutableStateOf("testmobile")

    val organizationName : MutableState<String> = mutableStateOf("") /*"ООО Воскресный"*/

    val confirmPassword : MutableState<String> = mutableStateOf("testmobile")


    private val _passwordVisibleFirst: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleFirst: StateFlow<Boolean> = _passwordVisibleFirst.asStateFlow()



    private val _passwordVisibleSecond: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleSecond: StateFlow<Boolean> = _passwordVisibleSecond.asStateFlow()

    val userRole : MutableState<UserType> = mutableStateOf(UserType.Candidate)

    val INN : MutableState<String> = mutableStateOf("") /*123456789012*/

    val screenState : MutableStateFlow<ScreenState<AllTypeUserInformation>> =
        MutableStateFlow(ScreenState.Loading)



    fun registration() {
        viewModelScope.launch(Dispatchers.IO) {
            buttonEnabled.value = false
            val singleName = "${secondName.value.trim()} ${name.value.trim()}"
            when (
                val curResult = if (userRole.value == UserType.Candidate) {
                    registrationKtorCandidateUseCase(singleName, email.value.trim(), password.value.trim(), phoneNumber.value.trim())
                } else {
                    registrationKtorEmployerUseCase(singleName, email.value.trim(), password.value.trim(), phoneNumber.value.trim(), INN.value.trim(), organizationName.value.trim())
                }
            ) {
                is CustomResult.Failure -> screenState.value = ScreenState.Failure(curResult.code, curResult.message)
                is CustomResult.Success -> screenState.value = ScreenState.Success(curResult.data)
            }
            buttonEnabled.value = true
        }
    }

//    fun registrationCandidate () {
//        viewModelScope.launch(Dispatchers.IO) {
//            val singleName = "${secondName.value.trim()} ${name.value.trim()}"
//            when (val curResult = registrationKtorCandidateUseCase(singleName, email.value.trim(), password.value.trim(), phoneNumber.value.trim())) {
//                is CustomResult.Failure -> signUpState.value = ScreenState.Failure(curResult.code, curResult.message)
//                is CustomResult.Success -> {
//                    signUpState.value = ScreenState.Success(data = curResult.data)
//                }
//            }
//        }
//    }
//
//    fun registrationEmployer () {
//        viewModelScope.launch (Dispatchers.IO) {
//            val singleName = "${secondName.value.trim()} ${name.value.trim()}"
//            when (val curResult = registrationEmployerUseCase(singleName, email.value, password.value, phoneNumber.value, INN.value, organizationName.value)) {
//                is CustomResult.Failure -> signUpState.value = ScreenState.Failure(curResult.code, curResult.message)
//                is CustomResult.Success -> {
//                    signUpState.value = ScreenState.Success(data = curResult.data)
//                }
//            }
//        }
//    }

    fun checkName () : Boolean {
        return name.value.trim().matches(Regex(AuthenticationRegexPatterns.name))
    }

    fun checkEmail () : Boolean {
        return email.value.trim().matches(Regex(AuthenticationRegexPatterns.email))
    }

    fun checkPhoneNumber () : Boolean {
        return phoneNumber.value.trim().matches(Regex(AuthenticationRegexPatterns.phoneNumber))
    }

    fun checkInn () : Boolean {
        return INN.value.trim().matches(Regex(AuthenticationRegexPatterns.inn))
    }

    fun checkPasswordMatch() : Boolean {
        return password.value.trim() == confirmPassword.value.trim()
    }
}