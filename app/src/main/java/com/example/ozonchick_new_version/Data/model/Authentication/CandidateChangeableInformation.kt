package com.example.ozonchick_new_version.Data.model.Authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CandidateChangeableInformation(
    @SerialName("Email") val email : String,
    @SerialName("Password") val password : String,
    @SerialName("Name") val name : String,
    @SerialName("PhoneNumber") val phoneNumber : String,
    @SerialName("StatusId") val statusID : Int
)
