package com.example.ozonchick_new_version.Screens.SignUp_LogIn.SignUp

import android.content.Context
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data_Layer.data.db.accessSharedRegistrait
import com.example.ozonchick_new_version.Data_Layer.data.dto.userType
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Background
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Red
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.User_information_registrait
import com.example.ozonchick_new_version.Data_Layer.network.supabase
import com.example.ozonchick_new_version.Domain.postRegistrait
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SignUpViewModel : ViewModel() {
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
        var responseRegistrait: Boolean
        viewModelScope.launch {
            supabase.from("user_information").insert(User_information_registrait(nameUser.value,phoneNumber.value,emailUser.value))
            {
                select(Columns.list("user_id"))
            }
//            responseRegistrait = postRegistrait().postRegistraitEmail(nameUser.value.capitalize(),
//                phoneNumber.value,
//                emailUser.value,
//                password.value,
//                userRole.value.type,
//                INN.value)
//            accessSharedRegistrait.value = responseRegistrait
        }
    }


    fun Proverka(): Boolean {
        return (nameUser.value.isNotEmpty() && phoneNumber.value.isNotEmpty()
                && emailUser.value.isNotEmpty() && password.value.isNotEmpty()
                && confirmPassword.value.isNotEmpty()
                && isMail() && password.value == confirmPassword.value)
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

    private fun isMail(): Boolean {
        val answer : Boolean = (emailUser.value == emailUser.value.toLowerCase()
                && emailUser.value.contains('@'))
        return answer
    }
}