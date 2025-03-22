package com.example.ozonchick_new_version.Presentation.Models

import com.example.ozonchick_new_version.Data.model.VacancyUpdateContact
import com.example.ozonchick_new_version.Data.model.VacancyUpdateInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts

data class ChangeVacancyUIModel(
    val mainInformation : VacancyUpdateInformation,
    val description : VacancyUpdateContact
)
