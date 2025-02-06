package com.example.ozonchick_new_version.Screens.SignUp_LogIn.LogIn

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.UserInformationAuth
import com.example.ozonchick_new_version.Data_Layer.network.supabase
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

    private val _isDataCorrect : MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isDataCorrect : StateFlow<Boolean> = _isDataCorrect.asStateFlow()

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
            catch (ex : io.github.jan.supabase.exceptions.HttpRequestException) {
                logInState.value = ScreenState.Failure(1, "Проверьте своё соединение с интернетом.")
            }
            catch (ex : java.lang.IndexOutOfBoundsException) {
                logInState.value = ScreenState.Failure(2, "Возможно, пользователя не существует. Проверьте введенные данные.")
            }
            catch (ex : Exception) {
                Log.d("ОШИБКА ВХОДА", ex.toString())
                logInState.value = ScreenState.Failure(3, "Неизвестная ошибка")
            }
        }
    }

    fun isDataValid()
    {
        _isDataCorrect.value = _emailUser.value.isNotEmpty() && _passwordUser.value.isNotEmpty() /*_emailUser.value.matches(Regex("[a-z-а-я-0-9]*@[a-z-а-я]{2,}")*/
        Log.d("ПРОВЕРКА ЗНАЧЕНИЙ", _isDataCorrect.value.toString())
    }
}




