package com.example.ozonchick_new_version.Data.model.Ktor.Authetication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CandidateInsertInformation(
    @SerialName("email") val email: String,
    @SerialName("name") val name: String,
    @SerialName("password") val password: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("status_id") val statusId: Int
)

@Serializable
data class EmployerInsertInformation(
    @SerialName("email") val email: String,
    @SerialName("inn") val inn: String,
    @SerialName("name_organization") val nameOrganization: String,
    @SerialName("password") val password: String,
    @SerialName("phoneNumber") val phoneNumber: String,
    @SerialName("status_id") val statusId: Int
)