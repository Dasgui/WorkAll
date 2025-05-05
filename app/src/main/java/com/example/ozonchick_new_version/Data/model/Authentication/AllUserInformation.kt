package com.example.ozonchick_new_version.Data.model.Authentication

import kotlinx.serialization.Serializable

sealed interface AllUserInformation {
    @Serializable
    data class Employer(
        val employer_id : Int = 0,
        val name : String,
        val phoneNumber : String,
        val email : String,
        val INN : String,
        val status_id : Int,
        val password : String,
    ) : AllUserInformation
    @Serializable
    data class Candidate(
        val user_id : Int = 0,
        val password: String,
        val name : String,
        val phoneNumber : String,
        var email : String,
        var resume_id : Int,
        var status_id : Int
    ) : AllUserInformation
    @Serializable
    data class Admin(
        val id : Int = 0,
        val email: String,
        val password : String,
        val name : String
    ) : AllUserInformation
}