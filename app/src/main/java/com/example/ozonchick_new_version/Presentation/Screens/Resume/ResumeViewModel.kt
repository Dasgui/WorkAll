package com.example.ozonchick_new_version.Presentation.Screens.Resume

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.Resume.EducationGet
import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet
import com.example.ozonchick_new_version.Data.model.Resume.ResumeInformation
import com.example.ozonchick_new_version.Data.model.Resume.ResumePost
import com.example.ozonchick_new_version.Domain.GetEducationUseCase
import com.example.ozonchick_new_version.Domain.GetExperienceUseCase
import com.example.ozonchick_new_version.Domain.GetInformationResumeUseCase
import com.example.ozonchick_new_version.Domain.UpdateResumeCandidateUseCase
import com.example.ozonchick_new_version.Presentation.Models.ResumeUIModel
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ResumeViewModel(
    val updateResumeCandidateUseCase: UpdateResumeCandidateUseCase,
    val getInformationResumeUseCase: GetInformationResumeUseCase
) : ViewModel() {
    val screenState : MutableStateFlow<ScreenState<Boolean>> = MutableStateFlow(ScreenState.Loading)

    val stepScreen : MutableState<ResumeUIModel> = mutableStateOf(ResumeUIModel.Education)

    val informationMaxChars: MutableState<Int> = mutableIntStateOf(2000)
    val information: MutableState<String> = mutableStateOf("")


    val professionalMaxChars: MutableState<Int> = mutableIntStateOf(50)
    val professionName: MutableState<String> = mutableStateOf("")

    val enabledFieldsExperience: MutableState<Boolean> = mutableStateOf(false)
    val experienceList: MutableState<List<ExperienceGet>> =
        mutableStateOf(emptyList())
    val experienceField: MutableState<String> = mutableStateOf("")
    val selectedExperience : MutableState<ExperienceGet?> = mutableStateOf(null)


    val educationList: MutableState<List<EducationGet>> =
        mutableStateOf(emptyList())
    val educationField: MutableState<String> = mutableStateOf("")
    val selectedEducation : MutableState<EducationGet?> = mutableStateOf(null)

    val isFieldsError : MutableState <Boolean> = mutableStateOf(false)

    fun loadData () {
        viewModelScope.launch(Dispatchers.IO) {
            val education = getInformationResumeUseCase()
            when (val localResponse = education) {
                is CustomResult.Failure -> screenState.value = ScreenState.Failure(localResponse.code, localResponse.message)
                is CustomResult.Success<ResumeInformation> -> {
                    educationList.value = localResponse.data.education
                    experienceList.value = localResponse.data.experience
                    screenState.value = ScreenState.AwaitingChangeFromUser
                }
            }

        }
    }

    fun postResume() {
        viewModelScope.launch(Dispatchers.IO) {
            val resume = ResumePost(
                experience_id = selectedExperience.value?.id ?: 0,
                description = information.value,
                professionName = professionName.value,
                education = selectedEducation.value?.id ?: 0
            )
            val response = updateResumeCandidateUseCase(resume)
            when (val localResponse = response) {
                is CustomResult.Failure -> screenState.value = ScreenState.Failure(localResponse.code, localResponse.message)
                is CustomResult.Success<Boolean> -> screenState.value = ScreenState.Success(localResponse.data)
            }
//            val resume = supabase.from("Resume").insert(
//                ResumePost(
//                    experience_id = 1,
//                    description = information.value,
//                    professionName = professionName.value,
//                    education = 1,
//                )
//            ) {
//                select()
//            }.decodeSingle<ResumeGet>()
//            supabase.from("user_information").update(
//                {
//                    set("resume_id", resume.id)
//                }
//            ) {
//                filter {
//                    eq("user_id", publicUserId.id)
//                }
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