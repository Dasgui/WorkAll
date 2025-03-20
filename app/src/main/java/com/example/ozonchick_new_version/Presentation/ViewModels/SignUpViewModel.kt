package com.example.ozonchick_new_version.Presentation.ViewModels

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Data.data.db.publicUserId
import com.example.ozonchick_new_version.Data.data.dto.userType
import com.example.ozonchick_new_version.Data.model.SigLogInModels.User
import com.example.ozonchick_new_version.Data.model.UserInsertInformation
import com.example.ozonchick_new_version.Domain.RegistraitionCandidateUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondColor
import io.paperdb.Paper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.text.Regex

class SignUpViewModel(
    private val registraitionCandidateUseCase: RegistraitionCandidateUseCase
) : ViewModel() {

    private val _isWrittenCorrectly: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isWrittenCorrectly: StateFlow<Boolean> = _isWrittenCorrectly.asStateFlow()

    fun setIsWrittenCorrectly(newValue: Boolean) {
        _isWrittenCorrectly.value = newValue
    }

    val name : MutableState<String> = mutableStateOf("Еми")

    val phoneNumber : MutableState<String> = mutableStateOf("+79087027174")

    val email : MutableState<String> = mutableStateOf("gaisovich@inbox.ru")

    val password : MutableState<String> = mutableStateOf("123")

    val confirmPassword : MutableState<String> = mutableStateOf("123")

    private val _passwordVisibleFirst: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleFirst: StateFlow<Boolean> = _passwordVisibleFirst.asStateFlow()



    private val _passwordVisibleSecond: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleSecond: StateFlow<Boolean> = _passwordVisibleSecond.asStateFlow()

    val userRole : MutableState<UserType> = mutableStateOf(UserType.Candidate)

    val INN : MutableState<String> = mutableStateOf("123456789012")

    val signUpState : MutableStateFlow<ScreenState<List<UserInsertInformation>>> =
        MutableStateFlow(ScreenState.Loading)

    fun Registrait() {
        viewModelScope.launch (Dispatchers.IO) {
//            val user = registraitionCandidateUseCase(name.value, email.value, password.value, phoneNumber.value)
            when (val curResult = registraitionCandidateUseCase(name.value, email.value, password.value, phoneNumber.value)) {
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