package com.example.ozonchick_new_version.Data.model.Resume

import kotlinx.serialization.Serializable

@Serializable
data class ResumePost(
    val experience_id : Int,
    val description : String,
    val professionName : String,
    val education : Int
)