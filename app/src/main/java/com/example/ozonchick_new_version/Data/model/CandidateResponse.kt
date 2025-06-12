package com.example.ozonchick_new_version.Data.model

import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.Candidate.CandidateMainInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CandidateResponse(
    @SerialName("ID") val id : Int,
    @SerialName("CandidateInfo") val candidateInfo : CandidateMainInformation,
    @SerialName("CreatedAt") val createdAt : String,
    @SerialName("Status") val status : Status,
)
