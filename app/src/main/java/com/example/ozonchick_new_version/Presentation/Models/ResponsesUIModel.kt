package com.example.ozonchick_new_version.Presentation.Models

import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.Vakans_otklik

data class ResponsesUIModel(
    val information : List<VakansInformation>,
    val status : List<Vakans_otklik>
)
