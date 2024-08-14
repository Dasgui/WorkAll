package com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model

import kotlinx.serialization.Serializable

@Serializable
data class User_balance(
    val user_id:Int,
    val balance:Int,
    )
