package com.example.ozonchick_new_version.Data.model.Employer

import kotlinx.serialization.Serializable

@Serializable
data class Employer(
    val name : String,
    val phoneNumber : String,
    val email : String,
    val INN : String,
    val status_id : Int,
    val password : String,
)
