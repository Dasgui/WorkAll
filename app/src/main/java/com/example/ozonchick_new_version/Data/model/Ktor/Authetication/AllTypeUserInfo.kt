package com.example.ozonchick_new_version.Data.model.Ktor.Authetication

import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Candidate.CandidateMainInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Employer.EmployerMainInformation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface AllTypeUserInformation

@Serializable
data class AllTypeUserInfo (
    @SerialName("CandidateInfo") val candidateInfo : CandidateMainInformation? = null,
    @SerialName("EmployerInfo") val employerInfo : EmployerMainInformation? = null,
    @SerialName("Status") val status: String,
    @SerialName("Token") val token: String
) : AllTypeUserInformation

@Serializable
data class EmployerInfo (
    @SerialName("EmployerInfo") val employerInfo : EmployerMainInformation,
    @SerialName("Status") val statusResponse: String,
    @SerialName("Token") val token: String,
) : AllTypeUserInformation

@Serializable
data class CandidateInfo (
    @SerialName("CandidateInfo") val candidateInfo : CandidateMainInformation,
    @SerialName("Status") val statusResponse: String,
    @SerialName("Token") val token: String,
) : AllTypeUserInformation