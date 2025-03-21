package com.example.ozonchick_new_version.Data.model.Candidate

import kotlinx.serialization.Serializable

@Serializable
data class UserInsertInformation(
    val name : String,
    val phoneNumber : String,
    val email : String,
    val password : String
)