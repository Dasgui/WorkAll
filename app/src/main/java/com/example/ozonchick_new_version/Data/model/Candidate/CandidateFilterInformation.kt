package com.example.ozonchick_new_version.Data.model.Candidate

import kotlinx.serialization.Serializable

@Serializable
data class CandidateFilterInformation(
    val phoneNumber : String,
    var email : String,
)
