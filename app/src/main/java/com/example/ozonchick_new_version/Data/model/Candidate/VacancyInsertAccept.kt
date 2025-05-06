package com.example.ozonchick_new_version.Data.model.Candidate

import kotlinx.serialization.Serializable

@Serializable
data class VacancyInsertAccept(
    val vakans_id:Int,
    val user_long_id:Int,
)