package com.example.ozonchick_new_version.Presentation_Layer.Models

import android.content.Context
import android.util.Log
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

class SignUp_ViewModel() : ViewModel() {
    private val _accessSharedAuth: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val accessSharedAuth: StateFlow<Boolean> = _accessSharedAuth.asStateFlow()

    private val _nameUser: MutableStateFlow<String> = MutableStateFlow("")
    var nameUser: StateFlow<String> = _nameUser.asStateFlow()

    private val _phoneNumber: MutableStateFlow<String> = MutableStateFlow("")
    var phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

    private val _emailUser: MutableStateFlow<String> = MutableStateFlow("")
    var emailUser: StateFlow<String> = _emailUser.asStateFlow()

    private val _passwordUser: MutableStateFlow<String> = MutableStateFlow("")
    var passwordUser: StateFlow<String> = _passwordUser.asStateFlow()

    private val _confirmPassword: MutableStateFlow<String> = MutableStateFlow("")
    var confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    private val _userRole:MutableStateFlow<userType> = MutableStateFlow(userType.soiskat)
    var userRole:StateFlow<userType> = _userRole.asStateFlow()

    private val _INN:MutableStateFlow<String> = MutableStateFlow("")
    var INN:StateFlow<String> = _INN.asStateFlow()

    private val _rememberUser:MutableStateFlow<Boolean> = MutableStateFlow(false)
    var rememberUser:StateFlow<Boolean> = _rememberUser.asStateFlow()


    fun setNameUserFieldValue(newValue: String)
    {
        _nameUser.value = newValue
    }

    fun setPhoneNumberFieldValue(newValue: String)
    {
        _phoneNumber.value = newValue
    }

    fun setEmailUserFieldValue(newValue: String)
    {
        _emailUser.value = newValue
    }

    fun setPasswordUserFieldValue(newValue: String)
    {
        _passwordUser.value = newValue
    }

    fun setConfirmPasswordFieldValue(newValue: String)
    {
        _confirmPassword.value = newValue
    }

    fun setUserRoleFieldValue(newValue: userType)
    {
        _userRole.value = newValue
    }

    fun setInnFieldValue(newValue: String)
    {
        _INN.value = newValue
    }

    fun setRememberUserCheckBoxValue(newValue: Boolean)
    {
        _rememberUser.value = newValue
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
    fun ClearParametres()
    {
        var Peremens = arrayListOf(nameUser.value, phoneNumber.value, emailUser.value, passwordUser.value, confirmPassword.value)
        Log.d("Проверка очистки", Peremens.toString())
        for (i in 0 until Peremens.size)
        {
            Peremens[i] = ""
        }
        Log.d("Проверка очистки", Peremens.toString())
    }

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
        if (nameUser.value.isNotEmpty() && phoneNumber.value.isNotEmpty()
            && emailUser.value.isNotEmpty() && passwordUser.value.isNotEmpty()
            && EnableCheck.value && confirmPassword.value.isNotEmpty()
            && ProverkaMail() && passwordUser.value == confirmPassword.value) {
            return true
        }
        return false
    }

    fun ProverkaMail(): Boolean {
        return emailUser.value == emailUser.value.toLowerCase()
    }
}




