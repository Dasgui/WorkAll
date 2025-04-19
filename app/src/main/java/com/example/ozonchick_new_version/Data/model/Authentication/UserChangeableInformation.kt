package com.example.ozonchick_new_version.Data.model.Authentication

import kotlinx.serialization.Serializable

@Serializable
data class UserChangeableInformation(
    val email : String,
    val password : String,
    val name : String,
    val phoneNumber : String,
)
