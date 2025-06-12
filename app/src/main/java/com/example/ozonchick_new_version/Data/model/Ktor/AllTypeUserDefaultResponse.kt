package com.example.ozonchick_new_version.Data.model.Ktor

import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Candidate.CandidateMainInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Employer.EmployerMainInformation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AllTypeUserDefaultResponse

@Serializable
data class DefaultEmployerResponse(
    @SerialName("EmployerInfo") val employerInfo : EmployerMainInformation,
    @SerialName("Status") val status : String
) : AllTypeUserDefaultResponse

@Serializable
data class DefaultCandidateResponse(
    @SerialName("CandidateInfo") val candidateInfo : CandidateMainInformation,
    @SerialName("Status") val status : String
) : AllTypeUserDefaultResponse