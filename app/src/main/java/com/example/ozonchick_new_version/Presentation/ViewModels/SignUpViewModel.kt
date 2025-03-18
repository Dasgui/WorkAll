package com.example.ozonchick_new_version.Presentation.ViewModels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data.data.dto.userType
import com.example.ozonchick_new_version.Domain.postRegistrait
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    val maxLengthPhoneNumber: Int = 13

    var numberCorrectFields: MutableList<Boolean> = MutableList(4) { false }
    private val _countWrittenCorrectly: MutableStateFlow<Int> = MutableStateFlow(0)

    fun setCountWrittenCorrectly(newValue: Int) {
        _countWrittenCorrectly.value = newValue
    }

    private val _isWrittenCorrectly: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isWrittenCorrectly: StateFlow<Boolean> = _isWrittenCorrectly.asStateFlow()

    fun setIsWrittenCorrectly(newValue: Boolean) {
        _isWrittenCorrectly.value = newValue
    }

    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    var name: StateFlow<String> = _name.asStateFlow()

    fun setNameUserFieldValue(newValue: String) {
        _name.value = newValue/*.trim().replace("\\s+".toRegex(), " ")*/
    }


    private val _phoneNumber: MutableStateFlow<String> = MutableStateFlow("")
    var phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

    fun setPhoneNumberFieldValue(newValue: String) {
        _phoneNumber.value = newValue
    }


    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    var email: StateFlow<String> = _email.asStateFlow()

    fun setEmailUserFieldValue(newValue: String) {
        _email.value = newValue
    }


    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    var password: StateFlow<String> = _password.asStateFlow()

    fun setPasswordUserFieldValue(newValue: String) {
        _password.value = newValue
    }


    private val _confirmPassword: MutableStateFlow<String> = MutableStateFlow("")
    var confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    fun setConfirmPasswordFieldValue(newValue: String) {
        _confirmPassword.value = newValue
    }


    private val _passwordVisibleFirst: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleFirst: StateFlow<Boolean> = _passwordVisibleFirst.asStateFlow()

    fun setPasswordVisibleFirst(newValue: Boolean) {
        if (newValue && _passwordVisibleFirst.value) {
            _passwordVisibleFirst.value = false
        } else if (newValue && !_passwordVisibleFirst.value) {
            _passwordVisibleFirst.value = true
        }
    }


    private val _passwordVisibleSecond: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleSecond: StateFlow<Boolean> = _passwordVisibleSecond.asStateFlow()

    fun setPasswordVisibleSecond(newValue: Boolean) {
        if (newValue && _passwordVisibleSecond.value) {
            _passwordVisibleSecond.value = false
        } else if (newValue && !_passwordVisibleSecond.value) {
            _passwordVisibleSecond.value = true
        }
    }


    private val _userRole: MutableStateFlow<userType> = MutableStateFlow(userType.soiskat)
    var userRole: StateFlow<userType> = _userRole.asStateFlow()

    fun setUserRoleFieldValue(newValue: userType) {
        _userRole.value = newValue
    }


    private val _INN: MutableStateFlow<String> = MutableStateFlow("")
    var INN: StateFlow<String> = _INN.asStateFlow()

    fun setInnFieldValue(newValue: String) {
        _INN.value = newValue
    }


    fun Registrait() {
        viewModelScope.launch {
            postRegistrait().postRegistraitEmail(
                name.value,
                phoneNumber.value,
                email.value,
                password.value
            )
        }
    }

    fun dataIsEnteredCorrectly() {
        if (numberCorrectFields.all { it == true }) {
            _isWrittenCorrectly.value = true
        } else {
            _isWrittenCorrectly.value = false
        }
    }

    fun checkNameTextField() {
        if (_name.value.matches(Regex("[А-Яа-яЁёa-zA-Z]{3,21}"))) {
            numberCorrectFields[0] = true
        } else {
            numberCorrectFields[0] = false
        }
    }

    fun checkPhoneNumberTextField() {
        if (_phoneNumber.value.matches(Regex("^\\+\\d{11}"))) {
            numberCorrectFields[1] = true
        } else {
            numberCorrectFields[1] = false
        }
    }

    fun checkEmailTextField() {
        if (_email.value.isNotEmpty() && isMail()) {
            numberCorrectFields[2] = true
        } else {
            numberCorrectFields[2] = false
        }
    }

    fun checkPasswordTextField() {
        if (_password.value.matches(Regex(".{6,20}"))
            && _confirmPassword.value.isNotEmpty()
            && _password.value == _confirmPassword.value
        ) {
            numberCorrectFields[3] = true
        } else {
            numberCorrectFields[3] = false
        }
    }

    @Composable
    fun getErrorDisplay(value: Boolean): TextDecoration {
        val answer: TextDecoration
        if (value) {
            answer = TextDecoration.Companion.None
        } else {
            answer = TextDecoration.Companion.Underline
        }
        return answer
    }

    @Composable
    fun isSecondaryElement(): Color {
        val answer: Color
        if (userRole.collectAsState().value == userType.soiskat) {
            answer = MainColor
        } else if (userRole.collectAsState().value == userType.nesoiskat) {
            answer = SecondColor
        } else {
            answer = SecondColor
        }
        return answer
    }

    private fun isMail(): Boolean {
        val answer: Boolean = (_email.value == _email.value/*.toLowerCase()*/
                && _email.value.contains('@'))
        return answer
    }
}