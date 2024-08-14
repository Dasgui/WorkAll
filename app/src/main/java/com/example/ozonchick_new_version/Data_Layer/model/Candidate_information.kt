package com.example.ozonchick_new_version.Data_Layer.model

import kotlinx.serialization.Serializable

@Serializable
data class Candidate_information(
    val otklik_id:String,
    val vakans_id:String,
    val otvet:String,
    val user_long_id:String
)