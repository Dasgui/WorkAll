package com.example.ozonchick_new_version.Screens.SignUp_LogIn.SignUp

import android.util.Log
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
    var isOkay : MutableState<Boolean> = mutableStateOf(false)

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


    fun Proverka() {
        if (name.value.isNotEmpty() && phoneNumber.value.isNotEmpty()
                && email.value.isNotEmpty() && password.value.isNotEmpty()
                && confirmPassword.value.isNotEmpty()
                && isMail() && password.value == confirmPassword.value) {
            isOkay.value = true
        } else {
            isOkay.value = false
        }
//        return (name.value.isNotEmpty() && phoneNumber.value.isNotEmpty()
//                && email.value.isNotEmpty() && password.value.isNotEmpty()
//                && confirmPassword.value.isNotEmpty()
//                && isMail() && password.value == confirmPassword.value)
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
        val answer : Boolean = (email.value == email.value.toLowerCase()
                && email.value.contains('@'))
        return answer
    }
}