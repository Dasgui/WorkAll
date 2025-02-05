package com.example.ozonchick_new_version.Screens.SignUp_LogIn.LogIn

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.UserInformationAuth
import com.example.ozonchick_new_version.Data_Layer.network.supabase
import com.example.ozonchick_new_version.Domain.postAuth
import com.example.ozonchick_new_version.Presentation.ScreenState
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LogInViewModel() : ViewModel() {
    private val _userInformation:MutableStateFlow<List<UserInformationAuth>> = MutableStateFlow(emptyList())
    var userInformation:StateFlow<List<UserInformationAuth>> = _userInformation.asStateFlow()

    private val _accessSharedAuth: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val accessSharedAuth: StateFlow<Boolean> = _accessSharedAuth.asStateFlow()

    private val _passwordVisible : MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisible : StateFlow<Boolean> = _passwordVisible.asStateFlow()

    fun setPasswordVisible (newValue: Boolean) {
        _passwordVisible.value = newValue
    }


    private val _emailUser: MutableStateFlow<String> = MutableStateFlow("test07@inbox.ru")
    var emailUser: StateFlow<String> = _emailUser.asStateFlow()

    fun setEmailUserFieldValue(newValue: String)
    {
        _emailUser.value = newValue
    }


    private val _passwordUser: MutableStateFlow<String> = MutableStateFlow("test07")
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

    val logInState : MutableStateFlow<ScreenState<UserInformationAuth>> = MutableStateFlow(ScreenState.Loading)
    fun Auth()
    {
        viewModelScope.launch {
            try {
                val user = supabase.from("user_information")
                    .select(columns = Columns.list("user_id, email, password")) {
                        filter {
                            UserInformationAuth::email eq _emailUser.value
                            UserInformationAuth::password eq _passwordUser.value
                        }
                    }.decodeList<UserInformationAuth>()
                logInState.value = ScreenState.Success(UserInformationAuth(user[0].email, user[0].password, user[0].user_id))
                Log.d("ИНФОРМАЦИЯ О ПОЛЬЗОВАТЕЛЕ", user.toString())
            }
            catch (ex : Exception) {
                Log.d("ОШИБКА ВХОДА", ex.toString())
                logInState.value = ScreenState.Failure(122, ex.toString())
            }
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




