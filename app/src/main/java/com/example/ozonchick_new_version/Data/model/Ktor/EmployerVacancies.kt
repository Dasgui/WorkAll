package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployerVacancies(
    @SerialName("EmployerID") val employerId: Int,
    @SerialName("VacanciesInfo") val vacanciesInfo : List<VacancyCreateInfo>,
    @SerialName("Status") val status : String
)
