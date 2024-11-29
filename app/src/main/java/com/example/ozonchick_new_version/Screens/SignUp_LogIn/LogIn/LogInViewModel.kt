package com.example.ozonchick_new_version.Screens.SignUp_LogIn.LogIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Domain.postAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LogInViewModel() : ViewModel() {
    private val _accessSharedAuth: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val accessSharedAuth: StateFlow<Boolean> = _accessSharedAuth.asStateFlow()

    private val _passwordVisible : MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisible : StateFlow<Boolean> = _passwordVisible.asStateFlow()

    fun setPasswordVisible (newValue: Boolean) {
        _passwordVisible.value = newValue
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


    private val _rememberUser:MutableStateFlow<Boolean> = MutableStateFlow(false)
    var rememberUser:StateFlow<Boolean> = _rememberUser.asStateFlow()

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
}




