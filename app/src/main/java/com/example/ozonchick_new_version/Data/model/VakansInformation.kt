package com.example.ozonchick_new_version.Data.model

import kotlinx.serialization.Serializable

@Serializable
data class VakansInformation(
    val vakans_id:Int,
    var vakans_name:String,
    var vakans_price:String,
    var vakans_organization:String,
    var vakans_location:String,
    var vakans_experience:String,
    var vakans_mail:String,
    val employer_id:Int
)