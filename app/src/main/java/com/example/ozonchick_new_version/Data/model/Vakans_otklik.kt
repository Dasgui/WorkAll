package com.example.ozonchick_new_version.Data.model

import kotlinx.serialization.Serializable

@Serializable
data class Vakans_otklik(
    var otklik_id : Int,
    val vakans_id:Int,
    val otvet:String,
    val user_long_id:Int,
)

@Serializable
data class VacancyInsertAccept(
    val vakans_id:Int,
    val user_long_id:Int,
)
