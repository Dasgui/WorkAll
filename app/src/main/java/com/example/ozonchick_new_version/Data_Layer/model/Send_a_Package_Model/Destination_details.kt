package com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model

import kotlinx.serialization.Serializable

@Serializable
data class Destination_details(
    val package_id: Int,
    val address: String,
    val state_country: String,
    val phone_number: String,
    val others: String
)
