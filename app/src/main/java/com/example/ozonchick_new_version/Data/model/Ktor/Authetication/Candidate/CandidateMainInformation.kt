package com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Candidate

import com.example.ozonchick_new_version.Data.model.Ktor.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CandidateMainInformation(
    @SerialName("ID") val id: Int,
    @SerialName("Name") val name: String,
    @SerialName("PhoneNumber") val phoneNumber: String,
    @SerialName("Email") val email: String,
    @SerialName("Password") val password: String,
    @SerialName("StatusInfo") val statusInfo: Status,
    @SerialName("CreatedAt") val createdAt: String,
    @SerialName("UpdatedAt") val updatedAt: String,
)