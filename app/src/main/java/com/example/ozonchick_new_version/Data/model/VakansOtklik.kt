package com.example.ozonchick_new_version.Data.model

import kotlinx.serialization.Serializable

@Serializable
data class VakansOtklik(
    val otklik_id : Int,
    val vakans_id : Int,
    val otvet : String,
    val user_long_id : Int,
)
