package com.example.ozonchick_new_version.Screens.Home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.db.accessSharedRegistrait
import com.example.ozonchick_new_version.Data_Layer.data.db.emailSharedUser
import com.example.ozonchick_new_version.Data_Layer.data.db.idSharedUser
import com.example.ozonchick_new_version.Data_Layer.data.db.userAuthInformation
import com.example.ozonchick_new_version.Data_Layer.data.db.userSharedId
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.UserName
import com.example.ozonchick_new_version.Data_Layer.network.supabase
import com.example.ozonchick_new_version.Screens.SignUpLogIn.LogIn.LogInScreen
import io.github.jan.supabase.gotrue.auth
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

    var emailUser: MutableState<String> =
        if (emailSharedUser.value.isNotEmpty()) {
            mutableStateOf(emailSharedUser.value)
        } else {
            mutableStateOf(userAuthInformation[0].email)
        }

    suspend fun getName() {
        val result =
            supabase.from("user_information").select(columns = Columns.list("name")) {
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

    fun Exit(navigator: Navigator?)
    {
        viewModelScope.launch {
            userSharedId.value = 0
            accessSharedRegistrait.value = false
            userAuthInformation.clear()
            supabase.auth.signOut()
        }
        Paper.book().delete("User")
        navigator?.replaceAll(LogInScreen())
    }

}