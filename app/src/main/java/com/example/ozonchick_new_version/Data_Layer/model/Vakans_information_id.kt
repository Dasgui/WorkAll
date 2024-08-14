package com.example.ozonchick_new_version.Data_Layer.model

import kotlinx.serialization.Serializable

@Serializable
data class Vakans_information_id(
    val vakans_id:Int,
    val employer_id:Int
)