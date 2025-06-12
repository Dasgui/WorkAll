package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllStatus(
    @SerialName("Data") var data: List<StatusInfo>,
    @SerialName("Status") val status: String
)

@Serializable
data class StatusInfo(
    @SerialName("CreatedAt") var createdAt: String,
    @SerialName("ID") var id: Int,
    @SerialName("Name") var name: String
)
