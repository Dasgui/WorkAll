package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeInfo(
    @SerialName("Description") val description : String,
    @SerialName("ExperienceID") val experienceId : Int
)

@Serializable
data class ResumeInfoUpdate (
    @SerialName("ResumeID") val resumeId : Int,
    @SerialName("Description") val description : String,
    @SerialName("ExperienceID") val experienceId : Int
)
