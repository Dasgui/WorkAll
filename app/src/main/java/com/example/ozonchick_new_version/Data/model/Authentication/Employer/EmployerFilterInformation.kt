package com.example.ozonchick_new_version.Data.model.Authentication.Employer

import kotlinx.serialization.Serializable

@Serializable
data class EmployerFilterInformation(
    val phoneNumber : String,
    val email : String,
    val INN : String
)
