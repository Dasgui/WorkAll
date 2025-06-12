package com.example.ozonchick_new_version.Data.model.Ktor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Experience(
    @SerialName("CreatedAt") val createdAt: String,
    @SerialName("ID") val id: Int,
    @SerialName("Name") val name: String
)
