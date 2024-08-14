package com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model

import kotlinx.serialization.Serializable

@Serializable
data class infoZakaz(
    var Address: String = "",
    var State_Country: String = "",
    var PhoneNumber: String = "",
    var Others: String = "",
)