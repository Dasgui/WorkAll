package com.example.ozonchick_new_version.Data.model.SignUp_LogIn_Model

import kotlinx.serialization.Serializable

@Serializable
data class User_id_name_email(
    val user_id:Int,
    val user_name:String,
    val user_email:String,
)
