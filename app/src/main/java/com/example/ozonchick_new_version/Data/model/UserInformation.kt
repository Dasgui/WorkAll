package com.example.ozonchick_new_version.Data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInformation(
    val user_id : Int = 0,
    val name : String,
    val phoneNumber : String,
    var email : String,
    var resume_id : Int,
    var status_id : Int
)
