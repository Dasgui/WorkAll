package com.example.ozonchick_new_version.Data.model.Ktor

import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Employer.EmployerMainInformation
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
@Serializable
data class VacancyInformation(
    @SerialName("AboutWork") val aboutWork: String?,
    @SerialName("CreatedAt") val createdAt: String?,
    @SerialName("Email") val email: String?,
    @SerialName("Employee") val employee: EmployerMainInformation?,
    @SerialName("Experience") val experience: ExperienceInformation?,
    @SerialName("ID") val id: Int?,
    @SerialName("IsVisible") val isVisible: Boolean?,
    @SerialName("Location") val location: String?,
    @SerialName("Name") val name: String?,
    @SerialName("PhoneNumber") val phoneNumber: String?,
    @SerialName("Price") val price: String?,
    @SerialName("UpdatedAt") val updatedAt: String?
)
