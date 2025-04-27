package com.example.ozonchick_new_version.Presentation.Screens.EmployerCandidateInformation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.Resume.EducationGet
import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet
import com.example.ozonchick_new_version.Data.model.Resume.ResumeDto
import com.example.ozonchick_new_version.Data.model.Resume.ResumeGet
import com.example.ozonchick_new_version.Data.network.supabase
import com.example.ozonchick_new_version.Domain.GetResumeCandidateUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class EmployerCandidateInformationViewModel(
    private val resumeId : Int,
    private val getResumeCandidateUseCase: GetResumeCandidateUseCase
) : ViewModel() {
    val screenState : MutableStateFlow<ScreenState<ResumeDto>> = MutableStateFlow(ScreenState.Loading)

    val educationField : MutableState<String> = mutableStateOf("")

    fun getResume () {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getResumeCandidateUseCase(resumeId)
            when (val localResponse = response) {
                is CustomResult.Failure -> screenState.value = ScreenState.Failure(localResponse.code, localResponse.message)
                is CustomResult.Success<ResumeDto> -> {
                    screenState.value = ScreenState.Success(localResponse.data)
                }
            }

        }
    }
}