package com.example.ozonchick_new_version.Data.model.Resume

import kotlinx.serialization.Serializable

@Serializable
data class ResumeDto(
    val id : Int,
    val experience : String,
    val description : String,
    val professionName : String,
    val education : String
)
