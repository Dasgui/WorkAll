package com.example.ozonchick_new_version.Presentation.Screens.Profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.Ktor.AllTypeUserDefaultResponse
import com.example.ozonchick_new_version.Data.model.Ktor.DefaultCandidateResponse
import com.example.ozonchick_new_version.Data.model.Ktor.DefaultEmployerResponse
import com.example.ozonchick_new_version.Presentation.Models.ProfileUIModel
import com.example.ozonchick_new_version.Data.network.supabase
import com.example.ozonchick_new_version.Domain.GetNameUseCase
import com.example.ozonchick_new_version.Domain.GetUserInformationKtorUseCase
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
    private val getUserInformationKtorUseCase: GetUserInformationKtorUseCase
) : ViewModel() {
    //    ScreenState.Success(ProfileUIModel(result[0].name))
    init {

    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val id = localUserInformation.id
            val type = localUserInformation.type

            Log.d("ID пользователя", id.toString())
            Log.d("Имя пользователя", localUserInformation.name.toString())

            val response = getUserInformationKtorUseCase(id, type)
            when (response) {
                is CustomResult.Failure -> {
                    Log.d("Всё плохо", response.toString())
                    informationProfileState.value = ScreenState.Failure(response.code, response.message)
                }

                is CustomResult.Success<AllTypeUserDefaultResponse> -> {
                    informationProfileState.value = when (response.data) {
                        is DefaultCandidateResponse -> ScreenState.Success(response.data.candidateInfo.name)
                        is DefaultEmployerResponse -> ScreenState.Success(response.data.employerInfo.nameOrganization)
                    }
                }
            }
        }
    }

    val informationProfileState: MutableStateFlow<ScreenState<String>> =
        MutableStateFlow(ScreenState.Loading)
}