package com.example.ozonchick_new_version.Screens.SignUp_LogIn

import android.content.Context
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data_Layer.data.db.accessSharedRegistrait
import com.example.ozonchick_new_version.Domain.postAuth
import com.example.ozonchick_new_version.Domain.postRegistrait
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.example.ozonchick_new_version.Data_Layer.data.dto.userType
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Background
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Red

class SignUpViewModel() : ViewModel() {
    private val _accessSharedAuth: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val accessSharedAuth: StateFlow<Boolean> = _accessSharedAuth.asStateFlow()


    private val _nameUser: MutableStateFlow<String> = MutableStateFlow("")
    var nameUser: StateFlow<String> = _nameUser.asStateFlow()

    fun setNameUserFieldValue(newValue: String)
    {
        _nameUser.value = newValue
    }


    private val _phoneNumber: MutableStateFlow<String> = MutableStateFlow("")
    var phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

    fun setPhoneNumberFieldValue(newValue: String)
    {
        _phoneNumber.value = newValue
    }


    private val _emailUser: MutableStateFlow<String> = MutableStateFlow("")
    var emailUser: StateFlow<String> = _emailUser.asStateFlow()

    fun setEmailUserFieldValue(newValue: String)
    {
        _emailUser.value = newValue
    }


    private val _passwordUser: MutableStateFlow<String> = MutableStateFlow("")
    var passwordUser: StateFlow<String> = _passwordUser.asStateFlow()

    fun setPasswordUserFieldValue(newValue: String)
    {
        _passwordUser.value = newValue
    }


    private val _confirmPassword: MutableStateFlow<String> = MutableStateFlow("")
    var confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    fun setConfirmPasswordFieldValue(newValue: String)
    {
        _confirmPassword.value = newValue
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


    private val _rememberUser:MutableStateFlow<Boolean> = MutableStateFlow(false)
    var rememberUser:StateFlow<Boolean> = _rememberUser.asStateFlow()

    fun setRememberUserCheckBoxValue(newValue: Boolean)
    {
        _rememberUser.value = newValue
    }



    private val  _passwordVisibleFirst:MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleFirst:StateFlow<Boolean> = _passwordVisibleFirst.asStateFlow()

    fun setPasswordVisibleFirst(newValue: Boolean) {
        if (newValue && _passwordVisibleFirst.value) {
            _passwordVisibleFirst.value = false
        } else if (newValue && !_passwordVisibleFirst.value) {
            _passwordVisibleFirst.value = true
        }
    }


    private val _passwordVisibleSecond:MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleSecond:StateFlow<Boolean> = _passwordVisibleSecond.asStateFlow()

    fun setPasswordVisibleSecond(newValue: Boolean) {
        if (newValue && _passwordVisibleSecond.value) {
            _passwordVisibleSecond.value = false
        } else if (newValue && !_passwordVisibleSecond.value) {
            _passwordVisibleSecond.value = true
        }
    }



    fun Auth(/*contextAuth:Context*/)
    {
        viewModelScope.launch {
            _accessSharedAuth.value = postAuth(/*contextAuth*/).postAuthEmail(emailUser.value,passwordUser.value, rememberUser.value)
        }
    }

    fun Registrait(context: Context)
    {
        var responseRegistrait: Boolean
        runBlocking {
            delay(1000)
            responseRegistrait = postRegistrait(context).postRegistraitEmail(nameUser.value.capitalize(),phoneNumber.value,emailUser.value,passwordUser.value, userRole.value.type, INN.value)
            delay(100)
            accessSharedRegistrait.value = responseRegistrait
        }
    }



    var EnableCheck: MutableState<Boolean> = mutableStateOf(false)

    fun ConstructProverka(firstLine:String, secondLine:String = ""):Boolean
    {
        if(firstLine.isNotEmpty() && secondLine.isNotEmpty())
        {
            return true
        }
        else if (firstLine.isNotEmpty() && secondLine == null)
        {
            return true
        }
        return false
    }

    fun Proverka(): Boolean {
        return (nameUser.value.isNotEmpty() && phoneNumber.value.isNotEmpty()
                && emailUser.value.isNotEmpty() && passwordUser.value.isNotEmpty()
                && EnableCheck.value && confirmPassword.value.isNotEmpty()
                && isMail() && passwordUser.value == confirmPassword.value)
    }

    private fun isMail(): Boolean {
        val answer : Boolean = (emailUser.value == emailUser.value.toLowerCase()
                && emailUser.value.contains('@'))
        return answer
    }

    @Composable
    fun getColorScheme() : TextFieldColors {
        val getColor : TextFieldColors
        if (isMail()) {
            getColor = TextFieldDefaults.colors(
                focusedContainerColor = Color_Background.value,
                unfocusedContainerColor = Color_Background.value,
                focusedIndicatorColor = Color_MainText.value,
                unfocusedIndicatorColor = Color_MainText.value,
                focusedTextColor = Color_MainText.value,
                unfocusedTextColor = Color_MainText.value
            )
        } else {
            getColor = TextFieldDefaults.colors(
                focusedContainerColor = Color_Background.value,
                unfocusedContainerColor = Color_Background.value,
                focusedIndicatorColor = Color_Red,
                unfocusedIndicatorColor = Color_Red,
                focusedTextColor = Color_Red,
                unfocusedTextColor = Color_Red,
            )
        }
        return getColor
    }
}




