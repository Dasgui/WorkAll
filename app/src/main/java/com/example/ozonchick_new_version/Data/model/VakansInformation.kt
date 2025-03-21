package com.example.ozonchick_new_version.Data.model

import kotlinx.serialization.Serializable

@Serializable
data class VakansInformation(
    val vakans_id:Int,
    val vakans_name:String,
    val vakans_price:String,
    val vakans_organization:String,
    val vakans_location:String,
    val vakans_experience:String,
    val vakans_mail:String,
    val employer_id:Int
)