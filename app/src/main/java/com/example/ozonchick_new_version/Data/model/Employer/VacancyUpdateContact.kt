package com.example.ozonchick_new_version.Data.model.Employer

import kotlinx.serialization.Serializable

@Serializable
data class VacancyUpdateContact(
    val vakans_number:String,
    val vakans_mail:String,
    val vakans_raspolog:String,
    val vakans_aboutWork:String
)