package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacanciesResponse(
    @SerialName("Status") val status: String,
    @SerialName("VacancyInfo") val vacanciesInfo: List<VacancyInformation>?
)

@Serializable
data class VacancyResponse(
    @SerialName("Status") val status: String,
    @SerialName("VacancyInfo") val vacancyInfo: VacancyInformation
)
