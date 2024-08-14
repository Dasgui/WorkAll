package com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model

import kotlinx.serialization.Serializable

@Serializable
data class User_information_registrait(
    val user_name:String,
    val user_phone_number:String,
    val user_email:String,
)

@Serializable
data class Employer_information_registrait(
    val employer_name:String,
    val employer_number:String,
    val employer_email:String,
    val employer_INN:String,
)
