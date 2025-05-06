package com.example.ozonchick_new_version.Domain

import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Data.model.Authentication.Candidate.Candidate
import com.example.ozonchick_new_version.Data.model.Authentication.Employer.Employer
import com.example.ozonchick_new_version.Data.model.ListOfVacancies.StatesOfGetVacancies
import com.example.ozonchick_new_version.Data.model.Resume.ResumeDto
import com.example.ozonchick_new_version.Data.model.VacancyAllInformation
import com.example.ozonchick_new_version.Data.model.Employer.VacancyDescriptionInsertModel
import com.example.ozonchick_new_version.Data.model.Employer.VacancyUpdateInformation
import com.example.ozonchick_new_version.Data.model.VacancyAdditionalInformation
import com.example.ozonchick_new_version.Data.model.VacancyInformation
import com.example.ozonchick_new_version.Data.model.Candidate.VacancyResponse
import com.example.ozonchick_new_version.Data.network.Repository
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Presentation.Models.ChangeVacancyUIModel

class GetVacanciesUseCase(private val repository: Repository) {
    suspend operator fun invoke(): CustomResult<List<VacancyInformation>> {
        var result = repository.getVacancies()
        result = when (val curResult = result) {
            is CustomResult.Failure -> {
                CustomResult.Failure(curResult.code, curResult.message)
            }

            is CustomResult.Success -> {
                CustomResult.Success(curResult.data)
            }
        }
        return result
    }
}

class SearchVacanciesUseCase(private val repository: Repository) {
    suspend operator fun invoke(getStates: StatesOfGetVacancies.Filter): CustomResult<List<VacancyInformation>> {
        return repository.searchVacancies(getStates)
    }
}

class GetContactInformationUseCase(private val repository: Repository) {
    suspend operator fun invoke(id: Int): CustomResult<List<VacancyAdditionalInformation>> {
        return repository.getContactInformation(id)
    }
}

class GetVacancyAllInformationUseCase(private val repository: Repository) {
    suspend operator fun invoke(vacancyId: Int, employerId : Int,isAdmin: Boolean): CustomResult<VacancyAllInformation> {
        return if (isAdmin) {
            val contact = repository.getContactInformation(vacancyId)
            val employer = repository.getEmployerInformation(employerId)

            if (contact is CustomResult.Success && employer is CustomResult.Success) {
                CustomResult.Success(VacancyAllInformation(contact.data.first(), employer.data))
            } else {
                CustomResult.Failure(0, AppErrors.INTERNET.description)
            }
        } else {
            val contact = repository.getContactInformation(vacancyId)
            val experiences = repository.getExperiences()
            if (contact is CustomResult.Success && experiences is CustomResult.Success) {
                CustomResult.Success(VacancyAllInformation(contact.data.first(), experience = experiences.data))
            } else {
                CustomResult.Failure(0, AppErrors.INTERNET.description)
            }
        }
    }
}

class GetResponsesIdVacanciesUseCase(private val repository: Repository) {
    suspend operator fun invoke(userId: UserId): CustomResult<List<VacancyResponse>> {
        return repository.getResponsesIdVacancies(userId)
    }
}

class GetResponsesVacanciesUseCase(private val repository: Repository) {
    suspend operator fun invoke(idList: List<Int>): CustomResult<List<VacancyInformation>> {
        return repository.getResponsesVacancies(idList)
    }
}

class GetEmployerVacanciesUseCase(private val repository: Repository) {
    suspend operator fun invoke(userId: UserId): CustomResult<List<VacancyInformation>> {
        return repository.getEmployerVacancies(userId)
    }
}

class DeleteEmployerVacancyUseCase(private val repository: Repository) {
    suspend operator fun invoke(vacancyId: Int): CustomResult<Boolean> {
        return repository.deleteVacancies(vacancyId)
    }
}

class UpdateEmployerVacancyUseCase(private val repository: Repository) {
    suspend operator fun invoke(
        vacancyId: Int,
        information: ChangeVacancyUIModel
    ): CustomResult<Boolean> {
        return repository.updateVacancy(vacancyId, information)
    }
}

class GetUsersForEmployerUseCase(private val repository: Repository) {
    suspend operator fun invoke(vacancyId: Int): CustomResult<List<Candidate>> {
        return repository.getUsersForEmployer(vacancyId)
    }
}

class AcceptCandidateUseCase(private val repository: Repository) {
    suspend operator fun invoke(candidateId: Int): CustomResult<Boolean> {
        return repository.acceptCandidate(candidateId)
    }
}

class RejectCandidateUseCase(private val repository: Repository) {
    suspend operator fun invoke(candidateId: Int): CustomResult<Boolean> {
        return repository.rejectCandidate(candidateId)
    }
}

class AddVacancyUseCase(private val repository: Repository) {
    suspend operator fun invoke(
        vacancy: VacancyUpdateInformation,
        description: VacancyDescriptionInsertModel
    ): CustomResult<Boolean> {
        return repository.addVacancy(vacancy, description)
    }
}

class GetOrganizationNameUseCase(private val repository: Repository) {
    suspend operator fun invoke(userId: UserId): CustomResult<String> {
        return repository.getOrganizationName(userId)
    }
}

class AcceptVacancyUseCase(private val repository: Repository) {
    suspend operator fun invoke(candidateId: Int, vacancyId: Int): CustomResult<Boolean> {
        return repository.acceptVacancy(candidateId, vacancyId)
    }
}

class RejectVacancyUseCase(private val repository: Repository) {
    suspend operator fun invoke(candidateId: Int, vacancyId: Int): CustomResult<Boolean> {
        return repository.rejectVacancy(candidateId, vacancyId)
    }
}

class GetResumeCandidateUseCase(private val repository: Repository) {
    suspend operator fun invoke(resumeId: Int): CustomResult<ResumeDto> {
        if (resumeId == 0) {

        }
        val resume = repository.getResume(resumeId)
        return if (resume is CustomResult.Success) {
            val experience =
                repository.getExperience(resume.data.experience_id) as CustomResult.Success
            val education = repository.getEducation(resume.data.education) as CustomResult.Success

            val resumeDto = ResumeDto(
                id = resume.data.id,
                experience = experience.data.name,
                description = resume.data.description,
                professionName = resume.data.professionName,
                education = education.data.name
            )

            CustomResult.Success(resumeDto)
        } else {
            CustomResult.Failure(0, AppErrors.INTERNET.description)
        }
    }
}

class GetEmployerInformation(private val repository: Repository) {
    suspend operator fun invoke(id: Int): CustomResult<Employer> {
        return repository.getEmployerInformation(id)
    }
}