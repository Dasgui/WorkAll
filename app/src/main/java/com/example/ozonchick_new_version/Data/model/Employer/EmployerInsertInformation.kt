package com.example.ozonchick_new_version.Data.model.Employer

import kotlinx.serialization.Serializable

@Serializable
data class EmployerInsertInformation(
    val name : String,
    val phoneNumber : String,
    val email : String,
    val INN : String,
    val password : String,
)
