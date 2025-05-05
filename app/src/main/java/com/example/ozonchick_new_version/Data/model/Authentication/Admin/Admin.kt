package com.example.ozonchick_new_version.Data.model.Authentication.Admin

import com.example.ozonchick_new_version.Data.model.Authentication.AllUserInformation
import kotlinx.serialization.Serializable

@Serializable
data class Admin(
    val id : Int,
    val email: String,
    val password : String,
    val name : String
)