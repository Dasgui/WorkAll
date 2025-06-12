package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
//Нужно попытаться сделать через один абстрактный класс
@Serializable
data class VacancyInfo(
    @SerialName("About") val about: String,
    @SerialName("Email") val email: String,
    @SerialName("ExperienceId") val experienceId: Int,
    @SerialName("IsVisible") val isVisible: Boolean,
    @SerialName("Location") val location: String,
    @SerialName("PhoneNumber") val phoneNumber: String,
    @SerialName("Price") val price: Int,
    @SerialName("VacancyName") val vacancyName: String
)

@Serializable
data class VacancyInfoExperience(
    @SerialName("AboutWork") val aboutWork: String,
    @SerialName("CreatedAt") val createdAt: String,
    @SerialName("Email") val email: String,
    @SerialName("Experience") val experience: Experience,
    @SerialName("ID") val id: Int,
    @SerialName("IsVisible") val isVisible: Boolean,
    @SerialName("Location") val location: String,
    @SerialName("Name") val name: String,
    @SerialName("PhoneNumber") val phoneNumber: String,
    @SerialName("Price") val price: Int,
    @SerialName("UpdatedAt") val updatedAt: String
)

@Serializable
data class VacancyInfoExperienceEmployer(
    @SerialName("AboutWork") val aboutWork: String,
    @SerialName("CreatedAt") val createdAt: String,
    @SerialName("Email") val email: String,
    @SerialName("EmployerName") val employerName: String,
    @SerialName("ExperienceInfo") val experience: Experience,
    @SerialName("ID") val id: Int,
    @SerialName("IsVisible") val isVisible: Boolean,
    @SerialName("Location") val location: String,
    @SerialName("Name") val name: String,
    @SerialName("PhoneNumber") val phoneNumber: String,
    @SerialName("Price") val price: Int,
    @SerialName("UpdatedAt") val updatedAt: String
)
