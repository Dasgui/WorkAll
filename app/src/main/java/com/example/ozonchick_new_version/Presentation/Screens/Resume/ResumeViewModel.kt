package com.example.ozonchick_new_version.Presentation.Screens.Resume

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.AllExperience
import com.example.ozonchick_new_version.Data.model.Ktor.Experience
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseStatus
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeDataStatus
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeInfo
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeInfoUpdate
import com.example.ozonchick_new_version.Data.model.Resume.EducationGet
import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet
import com.example.ozonchick_new_version.Data.model.Resume.ResumeInformation
import com.example.ozonchick_new_version.Data.model.Resume.ResumePost
import com.example.ozonchick_new_version.Domain.AddResumeKtorUseCase
import com.example.ozonchick_new_version.Domain.GetAllExperienceKtorUseCase
import com.example.ozonchick_new_version.Domain.GetEducationUseCase
import com.example.ozonchick_new_version.Domain.GetExperienceUseCase
import com.example.ozonchick_new_version.Domain.GetInformationResumeUseCase
import com.example.ozonchick_new_version.Domain.GetResumeKtorUseCase
import com.example.ozonchick_new_version.Domain.UpdateResumeCandidateUseCase
import com.example.ozonchick_new_version.Domain.UpdateResumeKtorUseCase
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import com.example.ozonchick_new_version.Presentation.Models.ResumeUIModel
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.States.ScreenState.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ResumeViewModel(
    val getResumeUseCase : GetResumeKtorUseCase,
    val addResumeKtorUseCase: AddResumeKtorUseCase,
    val updateResumeCandidateUseCase: UpdateResumeKtorUseCase,
    val getExperienceUseCase: GetAllExperienceKtorUseCase
) : ViewModel() {
    val screenState : MutableStateFlow<ScreenState<ResponseStatus>> = MutableStateFlow(ScreenState.Loading)

    val stepScreen : MutableState<ResumeUIModel> = mutableStateOf(ResumeUIModel.Information)

    val informationMaxChars: MutableState<Int> = mutableIntStateOf(2000)
    val information: MutableState<String> = mutableStateOf("")


    val professionalMaxChars: MutableState<Int> = mutableIntStateOf(50)
    val professionName: MutableState<String> = mutableStateOf("")

    val enabledFieldsExperience: MutableState<Boolean> = mutableStateOf(false)
    val experienceList: MutableState<List<Experience>> =
        mutableStateOf(emptyList())
    val experienceField: MutableState<String> = mutableStateOf("")
    val selectedExperience : MutableState<Experience?> = mutableStateOf(null)


    val educationList: MutableState<List<EducationGet>> =
        mutableStateOf(emptyList())
    val educationField: MutableState<String> = mutableStateOf("")
    val selectedEducation : MutableState<EducationGet?> = mutableStateOf(null)

    val isFieldsError : MutableState <Boolean> = mutableStateOf(false)

    val localResume : MutableState<ResumeDataStatus> = mutableStateOf(ResumeDataStatus())

    fun loadData () {
        viewModelScope.launch(Dispatchers.IO) {
            val education = getExperienceUseCase()
            val resume = getResumeUseCase(localUserInformation.id)
//            if (education !is CustomResult.Success && resume !is CustomResult.Success) {
//                experienceList.value = (education as CustomResult.Success).data.data
//                information.value = (resume as CustomResult.Success).data.resumesInfo?.firstOrNull()?.description ?: ""
//                screenState.value = AwaitingChangeFromUser
//            }

            if (education is CustomResult.Success && resume is CustomResult.Success) {
                experienceList.value = education.data.data
                information.value = resume.data.resumesInfo?.firstOrNull()?.description ?: ""
                localResume.value = resume.data
                selectedExperience.value = resume.data.resumesInfo?.firstOrNull()?.experienceInfo
                screenState.value = ScreenState.AwaitingChangeFromUser
            } else {
                screenState.value = ScreenState.Failure(ExclusionOptions.NetworkError.id,
                    ExclusionOptions.NetworkError.message)
            }

//            when (val experience = getExperienceUseCase()) {
//                is CustomResult.Failure -> screenState.value =
//                    Failure(experience.code, experience.message)
//                is CustomResult.Success<AllExperience> -> {
//                    experienceList.value = experience.data.data
//                    when (val resume = getResumeUseCase(localUserInformation.id)) {
//                        is CustomResult.Failure -> screenState.value = ScreenState.Failure(resume.code, resume.message)
//                        is CustomResult.Success<ResumeDataStatus> -> {
//                            information.value = resume.data.resumesInfo?.firstOrNull()?.description ?: ""
//                            selectedExperience.value = resume.data.resumesInfo?.firstOrNull()?.experienceInfo
//                            screenState.value = ScreenState.AwaitingChangeFromUser
//                        }
//                    }
////                    selectedEducation.value = EducationGet(1, "d")
//
//                }
//            }

        }
    }

    fun postResume() {
        viewModelScope.launch(Dispatchers.IO) {
            if (localResume.value.resumesInfo == null) {
                val resume = ResumeInfo(
                    description = information.value,
                    experienceId = selectedExperience.value?.id ?: 0
                )
                when(val curResult = addResumeKtorUseCase(resume)) {
                    is CustomResult.Failure -> screenState.value = ScreenState.Failure(curResult.code, curResult.message)
                    is CustomResult.Success<ResponseStatus> -> screenState.value = ScreenState.Success(curResult.data)
                }
            } else {
                val resume = ResumeInfoUpdate(
                    description = information.value,
                    experienceId = selectedExperience.value?.id ?: 0,
                    resumeId = localResume.value.resumesInfo?.firstOrNull()?.id ?: 0
                )
                when(val curResult = updateResumeCandidateUseCase(resume)) {
                    is CustomResult.Failure -> screenState.value = ScreenState.Failure(curResult.code, curResult.message)
                    is CustomResult.Success<ResponseStatus> -> screenState.value = ScreenState.Success(curResult.data)
                }
            }

//            val resume = ResumePost(
//                experience_id = selectedExperience.value?.id ?: 0,
//                description = information.value,
//                professionName = professionName.value,
//                education = selectedEducation.value?.id ?: 0
//            )
//            val response = updateResumeCandidateUseCase(resume)
//            when (val localResponse = response) {
//                is CustomResult.Failure -> screenState.value = ScreenState.Failure(localResponse.code, localResponse.message)
//                is CustomResult.Success<Boolean> -> screenState.value = ScreenState.Success(localResponse.data)
//            }
        }
    }

    fun switchStep (step : ResumeUIModel = stepScreen.value,isBack : Boolean = false, navigateOnNextScreen : () -> Unit = {}, navigateOnPreviousScreen: () -> Unit = {}) {
        when (step) {
            ResumeUIModel.Education -> if (isBack) navigateOnPreviousScreen() else stepScreen.value = ResumeUIModel.Experience
            ResumeUIModel.Experience -> if (isBack) stepScreen.value = ResumeUIModel.Education else stepScreen.value = ResumeUIModel.Information
            ResumeUIModel.Information -> if (isBack) stepScreen.value = ResumeUIModel.Experience else navigateOnNextScreen()
        }
    }

    fun switchVisibleExperience() {
        enabledFieldsExperience.value = !enabledFieldsExperience.value
    }

    fun checkFields (step: ResumeUIModel = stepScreen.value) : Boolean {
        return when (step) {
            ResumeUIModel.Education -> {
                selectedEducation.value != null
            }
            ResumeUIModel.Experience -> {
                if (enabledFieldsExperience.value) {
                    professionName.value.isNotEmpty() && selectedExperience.value != null
                } else {
                    true
                }
            }
            ResumeUIModel.Information -> {
                information.value.isNotEmpty()
            }
        }
    }
}