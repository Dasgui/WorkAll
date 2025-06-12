package com.example.ozonchick_new_version.Domain

import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.Ktor.CandidateResponses
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseInfoStatus
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseStatus
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeDataStatus
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeInfo
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeInfoUpdate
import com.example.ozonchick_new_version.Data.network.KtorRepositories.CandidateKtorRepository

class GetAllCandidateResponseKtorUseCase(private val repository : CandidateKtorRepository) {
    suspend operator fun invoke() : CustomResult<CandidateResponses> {
        return repository.getAllCandidateResponse()
    }
}

class AddResumeKtorUseCase(private val repository : CandidateKtorRepository) {
    suspend operator fun invoke(resumeInfo: ResumeInfo) : CustomResult<ResponseStatus> {
        return repository.addResume(resumeInfo)
    }
}

class UpdateResumeKtorUseCase(private val repository : CandidateKtorRepository) {
    suspend operator fun invoke(resumeInfo: ResumeInfoUpdate/*, candidateID : Int*/) : CustomResult<ResponseStatus> {
        return repository.updateResume(resumeInfo) /*when (val response = repository.getResume(candidateID)) {
            is CustomResult.Failure -> response
            is CustomResult.Success<ResumeDataStatus> -> {
                if (response.data.resumesInfo == null) {
                    repository.addResume(resumeInfo)
                } else {
                    repository.updateResume(resumeInfo)
                }
            }
        }*/
    }
}

class GetResumeKtorUseCase(private val repository : CandidateKtorRepository) {
    suspend operator fun invoke(candidateId: Int) : CustomResult<ResumeDataStatus> {
        return repository.getResume(candidateId)
    }
}

class RejectCandidateResponseKtorUseCase(private val repository : CandidateKtorRepository) {
    suspend operator fun invoke(vacancyId : Int) : CustomResult<ResponseInfoStatus> {
        return repository.rejectCandidateResponse(vacancyId)
    }
}