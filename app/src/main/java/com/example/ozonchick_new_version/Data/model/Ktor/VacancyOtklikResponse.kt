package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacancyOtklikResponse(
    @SerialName("ID") val id : Int,
    @SerialName("ResponseStatus") val responseStatus : Status,
    @SerialName("Status") val status : String,
    @SerialName("VacancyInfo") val vacancyInfo : VacancyInfoExperience,
)

@Serializable
data class VacanciesOtklikResponse(
    @SerialName("ID") val id : Int,
    @SerialName("StatusInfo") val responseStatus : Status,
    @SerialName("VacancyInfo") val vacancyInfo : VacancyInfoExperienceEmployer,
)

@Serializable
data class CandidateResponses(
    @SerialName("Responses") val responses : List<VacanciesOtklikResponse>? = null,
    @SerialName("Status") val status : String,
)
