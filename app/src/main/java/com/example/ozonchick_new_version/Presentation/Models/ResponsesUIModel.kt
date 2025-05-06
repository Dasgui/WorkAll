package com.example.ozonchick_new_version.Presentation.Models

import com.example.ozonchick_new_version.Data.model.VacancyInformation
import com.example.ozonchick_new_version.Data.model.Candidate.VacancyResponse

data class ResponsesUIModel(
    val information : List<VacancyInformation>,
    val status : List<VacancyResponse>
)
