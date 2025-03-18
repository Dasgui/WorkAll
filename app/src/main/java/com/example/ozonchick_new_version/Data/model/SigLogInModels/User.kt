package com.example.ozonchick_new_version.Data.model.SigLogInModels

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val user_id : Int,
    val name : String,
    val phoneNumber : String,
    val email : String,
    val password : String,
)
