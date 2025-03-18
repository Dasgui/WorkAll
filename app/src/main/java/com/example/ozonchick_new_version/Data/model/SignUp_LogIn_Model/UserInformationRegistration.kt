package com.example.ozonchick_new_version.Data.model.SignUp_LogIn_Model

import kotlinx.serialization.Serializable

@Serializable
data class UserInformationRegistration(
    val name : String,
    val phoneNumber : String,
    val email : String,
    val password : String,
)

@Serializable
data class EmployerInformationRegistration(
    val employer_name:String,
    val employer_number:String,
    val employer_email:String,
    val employer_INN:String,
)
