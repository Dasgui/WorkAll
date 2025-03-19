package com.example.ozonchick_new_version.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data.data.db.publicUserId
import com.example.ozonchick_new_version.Data.model.ProfileUIModel
import com.example.ozonchick_new_version.Data.model.SignUp_LogIn_Model.UserName
import com.example.ozonchick_new_version.Data.network.supabase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(/*userId : Int*/) : ViewModel() {
    init {
        getName(/*userId*/)
    }
    val informationProfileState : MutableStateFlow<ScreenState<ProfileUIModel>> =
        MutableStateFlow(ScreenState.Loading)
    fun getName(userId: Int = publicUserId) {
        viewModelScope.launch(Dispatchers.IO) {
            val result =
                supabase.from("user_information").select(columns = Columns.Companion.list("name")) {
                    filter {
                        eq("user_id", userId)
                    }
                }.decodeList<UserName>()
            informationProfileState.value = ScreenState.Success(ProfileUIModel(result[0].name))
        }
    }
}