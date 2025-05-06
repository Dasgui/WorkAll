package com.example.ozonchick_new_version.Presentation.Models

import com.example.ozonchick_new_version.Data.model.Employer.VacancyInsertModel
import com.example.ozonchick_new_version.Data.model.Employer.VacancyUpdateContact

data class ChangeVacancyUIModel(
    val mainInformation : VacancyInsertModel,
    val description : VacancyUpdateContact
)
