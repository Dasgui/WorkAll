package com.example.ozonchick_new_version.Domain

import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Data.model.Authentication.CandidateChangeableInformation
import com.example.ozonchick_new_version.Data.model.Ktor.AllTypeUserDefaultResponse
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.CandidateInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.EmployerInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseInfoStatus
import com.example.ozonchick_new_version.Data.model.Resume.EducationGet
import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet
import com.example.ozonchick_new_version.Data.model.Resume.ResumeInformation
import com.example.ozonchick_new_version.Data.model.Resume.ResumePost
import com.example.ozonchick_new_version.Data.network.KtorRepositories.HomeKtorRepository
import com.example.ozonchick_new_version.Data.network.Repository
import com.example.ozonchick_new_version.Data.strings.AppErrors

class GetNameUseCase(private val repository: Repository) {
    suspend operator fun invoke(userId: UserId): CustomResult<String> {
        return repository.getName(userId)
    }
}

class GetUserInformationKtorUseCase(private val repository: HomeKtorRepository) {
    suspend operator fun invoke(id : Int, type : UserType) : CustomResult<AllTypeUserDefaultResponse> {
        return repository.getUserInformation(id, type)
            /*when(type) {
            UserType.Admin -> CustomResult.Failure(0, AppErrors.UNKNOWN.description)
            UserType.Candidate -> repository.getCandidateInformation(id)
            UserType.Employer -> repository.getEmployerInformation(id)
            UserType.None -> CustomResult.Failure(0, AppErrors.UNKNOWN.description)
        }*/
    }
}

class UpdateUserInformationKtorUseCase(private val repository: HomeKtorRepository) {
    suspend operator fun invoke (candidateInformation : CandidateInsertInformation, employerInformation : EmployerInsertInformation) : CustomResult<ResponseInfoStatus> {
        val type = localUserInformation.type
        return if (type is UserType.Employer) {
            repository.updateUserInformation(employerInformation, "emp")
        } else {
            repository.updateUserInformation(candidateInformation, "user")
        }
    }
}

class UpdateUserInformationUseCase(private val repository: Repository) {
    suspend operator fun invoke(id: Int, information: CandidateChangeableInformation) : CustomResult<Boolean> {
        return repository.updateUserInformation(id, information)
    }
}

class GetUserChangeableInformationUseCase (private val repository: Repository) {
    suspend operator fun invoke (id : Int) : CustomResult<CandidateChangeableInformation> {
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