package com.example.ozonchick_new_version.Presentation.Screens.LogIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.AllUserInformation
import com.example.ozonchick_new_version.Data.model.SignUp_LogIn_Model.UserInformationAuth
import com.example.ozonchick_new_version.Domain.AuthUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import io.paperdb.Paper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LogInViewModel(private val auth : AuthUseCase) : ViewModel() {
    private val _userInformation: MutableStateFlow<List<UserInformationAuth>> =
        MutableStateFlow(emptyList())
    var userInformation: StateFlow<List<UserInformationAuth>> = _userInformation.asStateFlow()

    private val _accessSharedAuth: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val accessSharedAuth: StateFlow<Boolean> = _accessSharedAuth.asStateFlow()

    private val _passwordVisible : MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisible : StateFlow<Boolean> = _passwordVisible.asStateFlow()

    fun setPasswordVisible (newValue: Boolean) {
        _passwordVisible.value = newValue
    }


    private val _emailUser: MutableStateFlow<String> = MutableStateFlow("gaisich@inbox.ru") /*gaisich@inbox.ru*/ /*test07@inbox.ru*/
    var emailUser: StateFlow<String> = _emailUser.asStateFlow()

    fun setEmailUserFieldValue(newValue: String)
    {
        _emailUser.value = newValue
    }


    private val _passwordUser: MutableStateFlow<String> = MutableStateFlow("1234") /*1234*/ /*test07*/
    var passwordUser: StateFlow<String> = _passwordUser.asStateFlow()

    fun setPasswordUserFieldValue(newValue: String)
    {
        _passwordUser.value = newValue
    }


    private val _rememberUser: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var rememberUser: StateFlow<Boolean> = _rememberUser.asStateFlow()

    fun setRememberUserCheckBoxValue(newValue: Boolean)
    {
        _rememberUser.value = newValue
    }

    private val _isDataCorrect : MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isDataCorrect : StateFlow<Boolean> = _isDataCorrect.asStateFlow()

    val logInState : MutableStateFlow<ScreenState<AllUserInformation>> =
        MutableStateFlow(ScreenState.Loading)
    fun auth()
    {
        viewModelScope.launch (Dispatchers.IO) {
            when (val curResult = auth.invoke(_emailUser.value, _passwordUser.value)) {
                is CustomResult.Failure -> logInState.value = ScreenState.Failure(curResult.code, curResult.message)
                is CustomResult.Success -> {
                    logInState.value = ScreenState.Success(data = curResult.data)
                    if (_rememberUser.value) {
                        if (curResult.data is AllUserInformation.Employer) {
                            publicUserId.id = curResult.data.employer_id
                        } else if (curResult.data is AllUserInformation.Candidate) {
                            publicUserId.id = curResult.data.user_id
                        }
                        Paper.book().write("UserId", publicUserId)
                    } else {
                        if (curResult.data is AllUserInformation.Employer) {
                            publicUserId.id = curResult.data.employer_id
                        } else if (curResult.data is AllUserInformation.Candidate) {
                            publicUserId.id = curResult.data.user_id
                        }
                    }
                }
            }
        }
//        if (isDataValid()) {
//            logInState.value = ScreenState.Failure(4, "Проверьте адрес почты или пароль")
//            return
//        }
//        viewModelScope.launch {
//            try {
//                val user = supabase.from("user_information")
//                    .select(columns = Columns.Companion.list("user_id, email, password")) {
//                        filter {
//                            UserInformationAuth::email eq _emailUser.value
//                            UserInformationAuth::password eq _passwordUser.value
//                        }
//                    }.decodeList<UserInformationAuth>()
//                logInState.value = ScreenState.Success(
//                    UserInformationAuth(
//                        user[0].email,
//                        user[0].password,
//                        user[0].user_id
//                    )
//                )
//                Log.d("ИНФОРМАЦИЯ О ПОЛЬЗОВАТЕЛЕ", user.toString())
//            }
//            catch (ex : HttpRequestException) {
//                logInState.value = ScreenState.Failure(1, "Проверьте своё соединение с интернетом.")
//            }
//            catch (ex : IndexOutOfBoundsException) {
//                logInState.value = ScreenState.Failure(2, "Возможно, пользователя не существует. Проверьте введенные данные.")
//            }
//            catch (ex : Exception) {
//                Log.d("ОШИБКА ВХОДА", ex.toString())
//                logInState.value = ScreenState.Failure(3, "Неизвестная ошибка")
//            }
//        }
    }

    fun isDataValid() : Boolean
    {
        return _emailUser.value.trim().isNotEmpty() && _passwordUser.value.trim().isNotEmpty() && _emailUser.value.trim().matches(Regex("[a-zа-я0-9]+@[a-zа-я0-9]+.[a-zа-я]{2,}"))
    }
}