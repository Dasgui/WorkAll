package com.example.ozonchick_new_version.Data.model.Authentication

import kotlinx.serialization.Serializable

sealed interface AllUserInsertInformation {
    @Serializable
    data class UserInsertInformation(
        val name : String,
        val phoneNumber : String,
        val email : String,
        val password : String
    ) : AllUserInsertInformation
    @Serializable
    data class EmployerInsertInformation(
        val name : String,
        val phoneNumber : String,
        val email : String,
        val INN : String,
        val password : String,
    ) : AllUserInsertInformation
}