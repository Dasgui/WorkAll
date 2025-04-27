package com.example.ozonchick_new_version.Domain

import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Data.model.Authentication.UserChangeableInformation
import com.example.ozonchick_new_version.Data.model.Resume.EducationGet
import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet
import com.example.ozonchick_new_version.Data.model.Resume.ResumeInformation
import com.example.ozonchick_new_version.Data.model.Resume.ResumePost
import com.example.ozonchick_new_version.Data.network.Repository
import com.example.ozonchick_new_version.Data.strings.AppErrors

class GetNameUseCase(private val repository: Repository) {
    suspend operator fun invoke(userId: UserId): CustomResult<String> {
        return repository.getName(userId)
    }
}

class UpdateUserInformationUseCase(private val repository: Repository) {
    suspend operator fun invoke(id: Int, information: UserChangeableInformation) : CustomResult<Boolean> {
        return repository.updateUserInformation(id, information)
    }
}

class GetUserChangeableInformationUseCase (private val repository: Repository) {
    suspend operator fun invoke (id : Int) : CustomResult<UserChangeableInformation> {
        return repository.getUserChangeableInformation(id)
    }
}

class GetInformationResumeUseCase (private val repository: Repository) {
    suspend operator fun invoke () : CustomResult<ResumeInformation> {
        val education = repository.getEducations()
        val experience = repository.getExperiences()
        return if (education is CustomResult.Success && experience is CustomResult.Success) {
//            val educationList = education.data.map { it.name }
//            val experienceList = experience.data.map { it.name }
//            Log.d("Опыт работы", experienceList.toString())
//            Log.d("Оброзование", educationList.toString())
            CustomResult.Success(ResumeInformation(education.data, experience.data))
        } else {
            CustomResult.Failure(0, AppErrors.INTERNET.description)
        }
    }
}

class GetEducationUseCase (private val repository: Repository) {
    suspend operator fun invoke () : CustomResult<List<EducationGet>> {
        return repository.getEducations()
    }
}

class GetExperienceUseCase (private val repository: Repository) {
    suspend operator fun invoke () : CustomResult<List<ExperienceGet>> {
        return repository.getExperiences()
    }
}

class UpdateResumeCandidateUseCase (private  val repository: Repository) {
    suspend operator fun invoke (resume : ResumePost) : CustomResult<Boolean> {
        return repository.updateCandidateResume(resume)
    }
}