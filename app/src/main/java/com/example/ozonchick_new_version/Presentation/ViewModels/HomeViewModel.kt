package com.example.ozonchick_new_version.Presentation.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data.data.db.accessSharedRegistrait
import com.example.ozonchick_new_version.Data.data.db.emailSharedUser
import com.example.ozonchick_new_version.Data.data.db.idSharedUser
import com.example.ozonchick_new_version.Data.data.db.userSharedId
import com.example.ozonchick_new_version.Data.model.SignUp_LogIn_Model.UserName
import com.example.ozonchick_new_version.Data.network.supabase
import com.example.ozonchick_new_version.Presentation.Screens.LogIn.LogInScreen
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.paperdb.Paper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    private val _nameUser: MutableStateFlow<String> = MutableStateFlow("")
    var nameUser: StateFlow<String> = _nameUser.asStateFlow()

//    var emailUser: MutableState<String> =
//        if (emailSharedUser.value.isNotEmpty()) {
//            mutableStateOf(emailSharedUser.value)
//        } else {
//            mutableStateOf(userAuthInformation[0].email)
//        }

    suspend fun getName() {
        val result =
            supabase.from("user_information").select(columns = Columns.Companion.list("name")) {
                filter {
                    eq("user_id", idSharedUser.value)
                }
            }.decodeList<UserName>()
        _nameUser.value = result[0].name
    }
    private fun setNameUserValue(newValue : String)
    {
        _nameUser.value = newValue
    }

//    fun Exit(navigator: Navigator?)
//    {
//        viewModelScope.launch {
//            userSharedId.value = 0
//            accessSharedRegistrait.value = false
//            userAuthInformation.clear()
//        }
//        Paper.book().delete("User")
//        navigator?.replaceAll(LogInScreen())
//    }

}