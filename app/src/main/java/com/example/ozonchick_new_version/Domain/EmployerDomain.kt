package com.example.ozonchick_new_version.Domain

import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.AllResponses
import com.example.ozonchick_new_version.Data.model.Ktor.AllStatus
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.CandidateInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.EmployerInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.ResponseInfoStatus
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeData
import com.example.ozonchick_new_version.Data.network.KtorRepositories.EmployerKtorRepository

class GetAllResponsesUseCase(private val repository: EmployerKtorRepository) {
    suspend operator fun invoke (vacancyId: Int) : CustomResult<AllResponses> {
        return repository.getAllResponses(vacancyId)
    }
}

class GetCandidateResumeUseCase(private val repository: EmployerKtorRepository) {
    suspend operator fun invoke (candidateId: Int) : CustomResult<ResumeData> {
        return repository.getCandidateResume(candidateId)
    }
}

class GetOtklikAllStatusUseCase(private val repository: EmployerKtorRepository) {
    suspend operator fun invoke () : CustomResult<AllStatus> {
        return repository.getOtklikAllStatus()
    }
}

class RejectCandidateKtorUseCase(private val repository: EmployerKtorRepository) {
    suspend operator fun invoke (responseId : Int) : CustomResult<ResponseInfoStatus> {
        return repository.rejectCandidate(responseId)
    }
}

class AcceptCandidateKtorUseCase(private val repository: EmployerKtorRepository) {
    suspend operator fun invoke (responseId : Int) : CustomResult<ResponseInfoStatus> {
        return repository.acceptCandidate(responseId)
    }
}