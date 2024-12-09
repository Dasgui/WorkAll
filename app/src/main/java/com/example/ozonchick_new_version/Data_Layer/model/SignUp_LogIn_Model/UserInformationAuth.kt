package com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model

import kotlinx.serialization.Serializable

@Serializable
data class UserInformationAuth(
    var email : String,
    var password : String,
    var user_id: Int
)
