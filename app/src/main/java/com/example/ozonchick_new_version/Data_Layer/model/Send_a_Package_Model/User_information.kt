package com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model

import kotlinx.serialization.Serializable

@Serializable
data class User_information(
    val user_id: Int,
    val email: String
)