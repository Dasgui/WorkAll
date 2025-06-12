package com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Employer

import com.example.ozonchick_new_version.Data.model.Ktor.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployerMainInformation(
    @SerialName("CreatedAt") val createdAt: String,
    @SerialName("Email") val email: String,
    @SerialName("ID") val id: Int,
    @SerialName("INN") val inn: String,
    @SerialName("NameOrganization") val nameOrganization: String,
    @SerialName("Password") val password: String,
    @SerialName("PhoneNumber") val phoneNumber: String,
    @SerialName("Status") val status: Status,
    @SerialName("UpdatedAt") val updatedAt: String
)