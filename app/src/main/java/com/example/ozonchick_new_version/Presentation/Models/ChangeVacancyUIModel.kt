package com.example.ozonchick_new_version.Presentation.Models

import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts

data class ChangeVacancyUIModel(
    val mainInformation : VakansInformation,
    val description : Vakans_contacts
)
