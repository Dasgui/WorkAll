package com.example.ozonchick_new_version.Presentation.Screens.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Presentation.Models.ProfileUIModel
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

    }
    fun loadData () {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getNameUseCase(publicUserId)) {
                is CustomResult.Failure -> {
                    informationProfileState.value = ScreenState.Failure(result.code, result.message)
                }
                is CustomResult.Success<String> -> {
                    informationProfileState.value = ScreenState.Success(
                        ProfileUIModel(result.data)
                    )
                }
            }
        }
    }
    val informationProfileState : MutableStateFlow<ScreenState<ProfileUIModel>> =
        MutableStateFlow(ScreenState.Loading)
}