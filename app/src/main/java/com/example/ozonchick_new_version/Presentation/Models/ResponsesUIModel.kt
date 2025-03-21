package com.example.ozonchick_new_version.Presentation.Models

import com.example.ozonchick_new_version.Data.model.Candidate.UserInformation
import com.example.ozonchick_new_version.Data.model.Vakans_information
import com.example.ozonchick_new_version.Data.model.Vakans_otklik

data class ResponsesUIModel(
    val information : List<Vakans_information>,
    val status : List<Vakans_otklik>
)
