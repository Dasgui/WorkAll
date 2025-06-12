package com.example.ozonchick_new_version.Data.model.Ktor

import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Employer.EmployerMainInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.EmployerInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacancyCreateResponse(
    @SerialName("EmployerInfo") val employerInfo : EmployerMainInformation,
    @SerialName("Status") val status : String,
    @SerialName("VacancyInfo") val vacancyInfo : VacancyCreateInfo
)

@Serializable
data class VacancyCreateInfo(
    @SerialName("AboutWork") val aboutWork: String,
    @SerialName("CreatedAt") val createdAt: String,
    @SerialName("Email") val email: String,
    @SerialName("Experience") val experience: Experience,
    @SerialName("ID") val id: Int,
    @SerialName("IsVisible") var isVisible: Boolean,
    @SerialName("Location") val location: String,
    @SerialName("Name") val name: String,
    @SerialName("PhoneNumber") val phoneNumber: String,
    @SerialName("Price") val price: Int,
    @SerialName("UpdatedAt") val updatedAt: String
)