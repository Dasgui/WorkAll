package com.example.ozonchick_new_version.Presentation.Screens.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.ProfileUIModel
import com.example.ozonchick_new_version.Data.model.SignUp_LogIn_Model.UserName
import com.example.ozonchick_new_version.Data.network.supabase
import com.example.ozonchick_new_version.Domain.GetNameUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getNameUseCase: GetNameUseCase
) : ViewModel() {
//    ScreenState.Success(ProfileUIModel(result[0].name))
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val name = getName().await()
            if (name !is CustomResult.Failure) {
                informationProfileState.value = ScreenState.Success(ProfileUIModel((name as CustomResult.Success).data))
            } else {
                informationProfileState.value = ScreenState.Failure(name.code, name.message)
            }
        }
    }
    val informationProfileState : MutableStateFlow<ScreenState<ProfileUIModel>> =
        MutableStateFlow(ScreenState.Loading)
    suspend fun getName(userId: UserId = publicUserId) : Deferred<CustomResult<String>> {
        return coroutineScope { async (Dispatchers.IO) { getNameUseCase(userId) } }
    }
}