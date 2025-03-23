package com.example.ozonchick_new_version.Data.model

import kotlinx.serialization.Serializable

@Serializable
data class Vakans_contacts(
    val vakans_id:Int,
    var vakans_number:String,
    var vakans_mail:String,
    var vakans_raspolog:String,
    var vakans_aboutWork:String
)