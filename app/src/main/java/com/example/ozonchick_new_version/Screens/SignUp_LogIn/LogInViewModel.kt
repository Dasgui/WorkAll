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

class LogInViewModel() : ViewModel() {
    private val _accessSharedAuth: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val accessSharedAuth: StateFlow<Boolean> = _accessSharedAuth.asStateFlow()



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




