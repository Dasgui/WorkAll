package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacanciesResponse(
    val status: String,
    @SerialName("vacancy_info") val vacancyInfo: List<VacancyInformation>
)
