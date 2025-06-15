package com.example.ozonchick_new_version.Data.model.Ktor.Authetication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
sealed interface AllTypeUserInsertInformation

@Serializable
data class CandidateInsertInformation(
    @SerialName("Email") val email: String,
    @SerialName("Name") val name: String,
    @SerialName("Password") val password: String,
    @SerialName("PhoneNumber") val phoneNumber: String,
    @SerialName("StatusId") val statusId: Int
) : AllTypeUserInsertInformation

@Serializable
data class EmployerInsertInformation(
    @SerialName("Email") val email: String = "",
    @SerialName("INN") val inn: String = "",
    @SerialName("NameOrganization") val nameOrganization: String = "",
    @SerialName("Password") val password: String = "",
    @SerialName("PhoneNumber") val phoneNumber: String = "",
    @SerialName("StatusID") val statusId: Int = 0
) : AllTypeUserInsertInformation

@Serializable
data class EmployerUpdateInformation(
    @SerialName("Email") val email: String = "",
    @SerialName("NameOrganization") val nameOrganization: String = "",
    @SerialName("Password") val password: String = "",
    @SerialName("PhoneNumber") val phoneNumber: String = "",
    @SerialName("StatusID") val statusId: Int = 0
) : AllTypeUserInsertInformation