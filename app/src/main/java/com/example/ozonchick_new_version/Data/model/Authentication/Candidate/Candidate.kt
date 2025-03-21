package com.example.ozonchick_new_version.Data.model.Authentication.Candidate

import kotlinx.serialization.Serializable

@Serializable
data class Candidate(
    val user_id : Int = 0,
    val name : String,
    val password : String,
    val phoneNumber : String,
    var email : String,
    var resume_id : Int,
    var status_id : Int
)