package com.example.ozonchick_new_version.Data.model.Ktor

import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Candidate.CandidateMainInformation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeData(
    @SerialName("CandidateInfo") val candidateInfo : CandidateMainInformation,
    @SerialName("ResumesInfo") val resumesInfo : List<ResumesInfo>? = emptyList(),
)

@Serializable
data class ResumeDataStatus(
    @SerialName("CandidateInfo") val candidateInfo : CandidateMainInformation? = null,
    @SerialName("ResumesInfo") var resumesInfo : List<ResumesInfo>? = emptyList(),
    @SerialName("Status") val status : String? = ""
)

@Serializable
data class ResumesInfo (
    @SerialName("CreatedAt") val createdAt : String,
    @SerialName("Description") val description : String,
    @SerialName("ExperienceInfo") val experienceInfo : Experience,
    @SerialName("ID") val id : Int,
    @SerialName("UpdatedAt") val updatedAt : String,
)
