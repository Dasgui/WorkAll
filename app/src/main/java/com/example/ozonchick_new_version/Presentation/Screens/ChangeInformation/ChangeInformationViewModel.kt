package com.example.ozonchick_new_version.Presentation.Screens.ChangeInformation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ozonchick_new_version.Data.strings.AuthenticationRegexPatterns

class ChangeInformationViewModel : ViewModel() {
    val email : MutableState<String> = mutableStateOf("asdsd@as")
    val password : MutableState<String> = mutableStateOf("123")
    val name : MutableState<String> = mutableStateOf("123213")
    val phoneNumber : MutableState<String> = mutableStateOf("+790")

    fun saveChanges() {
        if (checkName() && checkEmail() && checkPhoneNumber()) {
            Log.d("Изменение информации о пользователе", "Всё успешно измененно")
        } else {
            Log.d("Изменение информации о пользователе", "Всё пошло на перекосяк")
        }
    }

    fun checkEmail () : Boolean {
        return email.value.trim().matches(Regex(AuthenticationRegexPatterns.email))
    }

    fun checkPhoneNumber () : Boolean {
        return phoneNumber.value.trim().matches(Regex(AuthenticationRegexPatterns.phoneNumber))
    }

    fun checkName () : Boolean {
        return name.value.trim().matches(Regex(AuthenticationRegexPatterns.name))
    }

}