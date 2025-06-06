package com.example.ozonchick_new_version.Presentation.Screens.LogIn

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.Authentication.AllUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.AllTypeUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.CandidateInfo
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.EmployerInfo
import com.example.ozonchick_new_version.Data.strings.AuthenticationRegexPatterns
import com.example.ozonchick_new_version.Domain.AuthKtorUseCase
import com.example.ozonchick_new_version.Domain.AuthUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import io.paperdb.Paper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LogInViewModel(/*private val auth : AuthUseCase, */private val authKtorUse : AuthKtorUseCase) : ViewModel() {

    private val _accessSharedAuth: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val accessSharedAuth: StateFlow<Boolean> = _accessSharedAuth.asStateFlow()

    private val _passwordVisible : MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisible : StateFlow<Boolean> = _passwordVisible.asStateFlow()

    fun setPasswordVisible (newValue: Boolean) {
        _passwordVisible.value = newValue
    }

    val buttonEnabled : MutableState<Boolean> = mutableStateOf(true)

    private val _emailUser: MutableStateFlow<String> = MutableStateFlow("") /*gaisich@inbox.ru*/ /*test07@inbox.ru*/ /*kol@inbox.ru*/
    var emailUser: StateFlow<String> = _emailUser.asStateFlow()

    fun setEmailUserFieldValue(newValue: String)
    {
        _emailUser.value = newValue
    }


    private val _passwordUser: MutableStateFlow<String> = MutableStateFlow("") /*1234*/ /*test07*/ /*123*/
    var passwordUser: StateFlow<String> = _passwordUser.asStateFlow()

    fun setPasswordUserFieldValue(newValue: String)
    {
        _passwordUser.value = newValue
    }

    val rememberUser : MutableState<Boolean> = mutableStateOf(false)
//    private val _rememberUser: MutableStateFlow<Boolean> = MutableStateFlow(false)
//    var rememberUser: StateFlow<Boolean> = _rememberUser.asStateFlow()

//    fun setRememberUserCheckBoxValue(newValue: Boolean)
//    {
//        _rememberUser.value = newValue
//    }

    private val _isDataCorrect : MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isDataCorrect : StateFlow<Boolean> = _isDataCorrect.asStateFlow()

//    val screenState : MutableStateFlow<ScreenState<AllUserInformation>> =
//        MutableStateFlow(ScreenState.AwaitingChangeFromUser)
//    fun auth()
//    {
//        viewModelScope.launch (Dispatchers.IO) {
//            buttonEnabled.value = false
//            when (val curResult = auth.invoke(_emailUser.value, _passwordUser.value, rememberUser.value)) {
//                is CustomResult.Failure -> screenState.value = ScreenState.Failure(curResult.code, curResult.message)
//                is CustomResult.Success -> {
//                    screenState.value = ScreenState.Success(data = curResult.data)
//                    when (curResult.data) {
//                        is AllUserInformation.Admin -> {
//                            publicUserId.id = curResult.data.id
//                            publicUserId.type = UserType.Admin
//                        }
//                        is AllUserInformation.Candidate -> {
//                            publicUserId.id = curResult.data.user_id
//                            publicUserId.type = UserType.Candidate
//                        }
//                        is AllUserInformation.Employer -> {
//                            publicUserId.id = curResult.data.employer_id
//                            publicUserId.type = UserType.Employer
//                        }
//                    }
//                    if (rememberUser.value) {
//                        Paper.book().write<UserId>("UserId",UserId(publicUserId.id, publicUserId.type))
//                    }
//                }
//            }
//            buttonEnabled.value = true
//        }
//    }


    val screenState : MutableStateFlow<ScreenState<AllTypeUserInformation>> =
        MutableStateFlow(ScreenState.AwaitingChangeFromUser)
    fun authKtor()
    {
        viewModelScope.launch (Dispatchers.IO) {
            buttonEnabled.value = false
            when (val curResult = authKtorUse.invoke(_emailUser.value, _passwordUser.value, rememberUser.value)) {
                is CustomResult.Failure -> screenState.value = ScreenState.Failure(curResult.code, curResult.message)
                is CustomResult.Success -> {
                    screenState.value = ScreenState.Success(data = curResult.data)
                    when (curResult.data) {
                        is CandidateInfo -> {
                            localUserInformation.token = "Bearer ${curResult.data.token}"
                            localUserInformation.type = UserType.Candidate
                            Log.d("Token", localUserInformation.token)
                        }
                        is EmployerInfo -> {
                            localUserInformation.token = "Bearer ${curResult.data.token}"
                            localUserInformation.type = UserType.Employer
                        }
                    }
                    if (rememberUser.value) {
                        Paper.book().write<UserId>("UserId",UserId(publicUserId.id, publicUserId.type))
                    }
                }
            }

            buttonEnabled.value = true
        }
    }

    fun isDataValid() : Boolean
    {
        return _emailUser.value.trim().isNotEmpty() && _passwordUser.value.trim().isNotEmpty() && _emailUser.value.trim().matches(Regex(
            AuthenticationRegexPatterns.email))
    }
}