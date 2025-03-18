package com.example.ozonchick_new_version.Data.model

import kotlinx.serialization.Serializable

@Serializable
data class Vakans_contacts(
    val vakans_id:Int,
    val vakans_number:String,
    val vakans_mail:String,
    val vakans_raspolog:String,
    val vakans_aboutWork:String
)