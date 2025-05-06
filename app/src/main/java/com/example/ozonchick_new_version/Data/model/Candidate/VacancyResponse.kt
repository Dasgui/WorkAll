package com.example.ozonchick_new_version.Data.model.Candidate

import kotlinx.serialization.Serializable

@Serializable
data class VacancyResponse(
    var otklik_id : Int,
    val vakans_id:Int,
    val otvet:String,
    val user_long_id:Int,
)