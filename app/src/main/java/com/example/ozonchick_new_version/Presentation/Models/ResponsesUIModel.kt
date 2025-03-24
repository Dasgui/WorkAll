package com.example.ozonchick_new_version.Presentation.Models

import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.VacancyResponse

data class ResponsesUIModel(
    val information : List<VakansInformation>,
    val status : List<VacancyResponse>
)
