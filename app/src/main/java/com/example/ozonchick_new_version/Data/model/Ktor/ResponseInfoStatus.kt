package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseInfoStatus(
    @SerialName("Info") val info : String,
    @SerialName("Status") val status : String,
)

@Serializable
data class ResponseStatus(
    @SerialName("Status") val status : String,
)
