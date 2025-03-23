package com.example.ozonchick_new_version.Domain

import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Data.model.Authentication.Candidate.Candidate
import com.example.ozonchick_new_version.Data.model.VacancyDescriptionInsertModel
import com.example.ozonchick_new_version.Data.model.VacancyUpdateContact
import com.example.ozonchick_new_version.Data.model.VacancyUpdateInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.Vakans_otklik
import com.example.ozonchick_new_version.Data.network.Repository
import com.example.ozonchick_new_version.Presentation.Models.ChangeVacancyUIModel

class GetVacanciesUseCase (private val repository: Repository) {
    suspend operator fun invoke () : CustomResult<List<VakansInformation>> {
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

class GetContactInformationUseCase (private val repository: Repository) {
    suspend operator fun invoke (id : Int) : CustomResult<List<Vakans_contacts>> {
        var result = repository.getContactInformation(id)
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

class GetResponsesIdVacanciesUseCase(private val repository: Repository) {
    suspend operator fun invoke(userId : UserId) : CustomResult<List<Vakans_otklik>> {
        return repository.getResponsesIdVacancies(userId)
    }
}

class GetResponsesVacanciesUseCase(private val repository: Repository) {
    suspend operator fun invoke(idList : List<Int>) : CustomResult<List<VakansInformation>> {
        return repository.getResponsesVacancies(idList)
    }
}

class GetEmployerVacanciesUseCase(private val repository: Repository) {
    suspend operator fun invoke(userId: UserId) : CustomResult<List<VakansInformation>> {
        return repository.getEmployerVacancies(userId)
    }
}

class DeleteEmployerVacancyUseCase (private val repository: Repository) {
    suspend operator fun invoke(vacancyId: Int) : CustomResult<Boolean> {
        return repository.deleteVacancies(vacancyId)
    }
}

class UpdateEmployerVacancyUseCase (private val repository: Repository) {
    suspend operator fun invoke(vacancyId: Int, information: ChangeVacancyUIModel) : CustomResult<Boolean> {
        return repository.updateVacancy(vacancyId, information)
    }
}

class GetUsersForEmployerUseCase (private val repository: Repository) {
    suspend operator fun invoke(vacancyId : Int) : CustomResult<List<Candidate>> {
        return repository.getUsersForEmployer(vacancyId)
    }
}

class AcceptCandidateUseCase (private val repository: Repository) {
    suspend operator fun invoke(candidateId : Int) : CustomResult<Boolean> {
        return repository.acceptCandidate(candidateId)
    }
}

class RejectCandidateUseCase (private val repository: Repository) {
    suspend operator fun invoke(candidateId : Int) : CustomResult<Boolean> {
        return repository.rejectCandidate(candidateId)
    }
}

class AddVacancyUseCase (private val repository: Repository) {
    suspend operator fun invoke(vacancy : VacancyUpdateInformation, description : VacancyDescriptionInsertModel) : CustomResult<Boolean> {
        return repository.addVacancy(vacancy, description)
    }
}

class GetOrganizationNameUseCase(private val repository: Repository) {
    suspend operator fun invoke(userId: UserId) : CustomResult<String> {
        return repository.getOrganizationName(userId)
    }
}