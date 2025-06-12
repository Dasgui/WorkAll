package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorInfoStatus(
    @SerialName("Error") val error : String,
    @SerialName("Info") val info : String,
    @SerialName("Status") val status : String
)
