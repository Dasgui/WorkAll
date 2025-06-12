package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateResponse(
    @SerialName("ResponseID") val responseID : Int,
    @SerialName("StatusID") val statusID : Int,
)
