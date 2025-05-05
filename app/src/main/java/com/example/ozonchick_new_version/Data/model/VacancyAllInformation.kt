package com.example.ozonchick_new_version.Data.model

import com.example.ozonchick_new_version.Data.model.Authentication.Employer.Employer
import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet

data class VacancyAllInformation(
    val contacts : Vakans_contacts,
    val employer : Employer? = null,
    val experience: List<ExperienceGet> = emptyList<ExperienceGet>()
)
