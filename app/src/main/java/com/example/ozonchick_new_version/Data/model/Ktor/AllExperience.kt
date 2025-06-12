package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllExperience(
    @SerialName("Data") val data : List<Experience>,
    @SerialName("Status") val status : String
)
