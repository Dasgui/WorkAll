package com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model

import kotlinx.serialization.Serializable

@Serializable
data class Package_details(
    val package_id: Int,
    val package_item: String,
    val weight_item: String,
    val worth_item :String
)
