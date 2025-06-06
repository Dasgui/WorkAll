package com.example.ozonchick_new_version.Data.model.Ktor.Authetication

import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Candidate.CandidateMainInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Employer.EmployerMainInformation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AllTypeUserInformation

@Serializable
data class EmployerInfo (
    @SerialName("Employee_Info") val employerInfo : EmployerMainInformation,
    @SerialName("status") val statusResponse: String,
    @SerialName("token") val token: String,
) : AllTypeUserInformation

@Serializable
data class CandidateInfo (
    @SerialName("candidate_Info") val candidateInfo : CandidateMainInformation,
    @SerialName("status") val statusResponse: String,
    @SerialName("token") val token: String,
) : AllTypeUserInformation