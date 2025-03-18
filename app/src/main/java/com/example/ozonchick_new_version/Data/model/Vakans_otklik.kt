package com.example.ozonchick_new_version.Data.model

import kotlinx.serialization.Serializable

@Serializable
data class Vakans_otklik(
    /*val user_id:Int,*/
    val user_long_id:Int,
    val vakans_id:Int,
    val otvet:String
)
