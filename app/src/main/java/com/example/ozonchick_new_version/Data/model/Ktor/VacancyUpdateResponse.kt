package com.example.ozonchick_new_version.Data.model.Ktor

import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Employer.EmployerMainInformation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacancyUpdateResponse(
    @SerialName("About") val about: String,
    @SerialName("Email") val email: String,
    @SerialName("ExperienceId") val experienceId: Int,
    @SerialName("ID") val id: Int,
    @SerialName("IsVisible") val isVisible: Boolean,
    @SerialName("Location") val location: String,
    @SerialName("PhoneNumber") val phoneNumber: String,
    @SerialName("Price") val price: Int,
    @SerialName("VacancyName") val vacancyName: String
)
