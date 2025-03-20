package com.example.ozonchick_new_version.Presentation.ViewModels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Data.data.dto.userType
import com.example.ozonchick_new_version.Domain.postRegistrait
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _isWrittenCorrectly: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var isWrittenCorrectly: StateFlow<Boolean> = _isWrittenCorrectly.asStateFlow()

    fun setIsWrittenCorrectly(newValue: Boolean) {
        _isWrittenCorrectly.value = newValue
    }

    val name : MutableState<String> = mutableStateOf("")

    val phoneNumber : MutableState<String> = mutableStateOf("")

    val email : MutableState<String> = mutableStateOf("")

    val password : MutableState<String> = mutableStateOf("")

    val confirmPassword : MutableState<String> = mutableStateOf("")

    private val _passwordVisibleFirst: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleFirst: StateFlow<Boolean> = _passwordVisibleFirst.asStateFlow()



    private val _passwordVisibleSecond: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var passwordVisibleSecond: StateFlow<Boolean> = _passwordVisibleSecond.asStateFlow()

    val userRole : MutableState<UserType> = mutableStateOf(UserType.Candidate)

    val INN : MutableState<String> = mutableStateOf("")



    fun Registrait() {
        viewModelScope.launch {
            postRegistrait().postRegistraitEmail(
                name.value,
                phoneNumber.value,
                email.value,
                password.value
            )
        }
    }

    @Composable
    fun getErrorDisplay(value: Boolean): TextDecoration {
        val answer: TextDecoration
        if (value) {
            answer = TextDecoration.Companion.None
        } else {
            answer = TextDecoration.Companion.Underline
        }
        return answer
    }


}