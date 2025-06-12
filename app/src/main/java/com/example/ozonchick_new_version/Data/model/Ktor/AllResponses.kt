package com.example.ozonchick_new_version.Data.model.Ktor

import com.example.ozonchick_new_version.Data.model.CandidateResponse
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Candidate.CandidateMainInformation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllResponses(
    @SerialName("Responses") val responseInfo : List<CandidateResponse>? = emptyList(),
    @SerialName("Status") val status : String,
    @SerialName("VacancyInfo") val vacancyInfo : VacancyCreateInfo
)
