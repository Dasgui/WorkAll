package com.example.ozonchick_new_version.Screens.SignUp_LogIn.SignUp

import android.util.Log
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data_Layer.data.dto.userType
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.backgroundColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.mainColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Red
import com.example.ozonchick_new_version.Domain.postRegistrait
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    var listGoodOtvet : MutableList<Boolean> = MutableList (4) {false}
    private val _countWrittenCorrectly : MutableStateFlow<Int> = MutableStateFlow(0)
    var countWrittenCorrectly : StateFlow<Int> = _countWrittenCorrectly.asStateFlow()

    fun setCountWrittenCorrectly(newValue: Int) {
        _countWrittenCorrectly.value = newValue
    }

    private val _isWrittenCorrectly : MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isWrittenCorrectly : StateFlow<Boolean> = _isWrittenCorrectly.asStateFlow()

    fun setIsWrittenCorrectly(newValue: Boolean) {
        _isWrittenCorrectly.value = newValue
    }

    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    var name: StateFlow<String> = _name.asStateFlow()

    fun setNameUserFieldValue(newValue: String)
    {
        _name.value = newValue
    }


    private val _phoneNumber: MutableStateFlow<String> = MutableStateFlow("")
    var phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

    fun setPhoneNumberFieldValue(newValue: String)
    {
        _phoneNumber.value = newValue
    }


    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    var email: StateFlow<String> = _email.asStateFlow()

    fun setEmailUserFieldValue(newValue: String)
    {
        _email.value = newValue
    }


    private val _password: MutableStateFlow<String> = MutableStateFlow("")
    var password: StateFlow<String> = _password.asStateFlow()

    fun setPasswordUserFieldValue(newValue: String)
    {
        _password.value = newValue
    }


    private val _confirmPassword: MutableStateFlow<String> = MutableStateFlow("")
    var confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    fun setConfirmPasswordFieldValue(newValue: String)
    {
        _confirmPassword.value = newValue
    }


    private val  _passwordVisibleFirst: MutableStateFlow<Boolean> = MutableStateFlow(false)
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


    private val _userRole:MutableStateFlow<userType> = MutableStateFlow(userType.soiskat)
    var userRole:StateFlow<userType> = _userRole.asStateFlow()

    fun setUserRoleFieldValue(newValue: userType)
    {
        _userRole.value = newValue
    }


    private val _INN:MutableStateFlow<String> = MutableStateFlow("")
    var INN:StateFlow<String> = _INN.asStateFlow()

    fun setInnFieldValue(newValue: String)
    {
        _INN.value = newValue
    }



    fun Registrait()
    {
        viewModelScope.launch {
            postRegistrait().postRegistraitEmail(name.value, phoneNumber.value, email.value, password.value)
        }
    }

    fun dataIsEnteredCorrectly() {
        if (listGoodOtvet.all { it == true }) {
            _isWrittenCorrectly.value = true
        } else {
            _isWrittenCorrectly.value = false
        }
//        _isWrittenCorrectly.value = _countWrittenCorrectly.value == 5
//        Log.d("Количество правильных данных", _countWrittenCorrectly.value.toString())
    }

    fun checkNameTextField() {
        if (name.value.isNotEmpty()) {
            listGoodOtvet[0] = true
//            _countWrittenCorrectly.value++
        } else {
            listGoodOtvet[0] = false
//            _countWrittenCorrectly.value--
        }
//        return (name.value.isNotEmpty() && phoneNumber.value.isNotEmpty()
//                && email.value.isNotEmpty() && password.value.isNotEmpty()
//                && confirmPassword.value.isNotEmpty()
//                && isMail() && password.value == confirmPassword.value)
    }

    fun checkPhoneNumberTextField() {
        if (phoneNumber.value.isNotEmpty()) {
            listGoodOtvet[1] = true
//            _countWrittenCorrectly.value++
        } else {
            listGoodOtvet[1] = false
//            _countWrittenCorrectly.value--
        }
    }

    fun checkEmailTextField() {
        if (_email.value.isNotEmpty() && isMail()) {
            listGoodOtvet[2] = true
//            _countWrittenCorrectly.value++
        } else {
            listGoodOtvet[2] = false
//            _countWrittenCorrectly.value--
        }
    }

    fun checkPasswordTextField() {
        if (_password.value.isNotEmpty()
            && _confirmPassword.value.isNotEmpty()
            && _password.value == _confirmPassword.value) {
            listGoodOtvet[3] = true
//            _countWrittenCorrectly.value++
        } else {
            listGoodOtvet[3] = false
//            _countWrittenCorrectly.value--
        }
    }

    @Composable
    fun getColorScheme() : TextFieldColors {
        val getColor : TextFieldColors
        if (isMail()) {
            getColor = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                focusedIndicatorColor = mainColor,
                unfocusedIndicatorColor = mainColor,
                focusedTextColor = mainColor,
                unfocusedTextColor = mainColor
            )
        } else {
            getColor = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                focusedIndicatorColor = Color_Red,
                unfocusedIndicatorColor = Color_Red,
                focusedTextColor = Color_Red,
                unfocusedTextColor = Color_Red,
            )
        }
        return getColor
    }

    private fun isMail(): Boolean {
        val answer : Boolean = (_email.value == _email.value.toLowerCase()
                && _email.value.contains('@'))
        return answer
    }
}