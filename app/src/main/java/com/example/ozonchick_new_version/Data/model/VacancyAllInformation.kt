package com.example.ozonchick_new_version.Data.model

import com.example.ozonchick_new_version.Data.model.Authentication.Employer.Employer

data class VacancyAllInformation(
    val contacts : Vakans_contacts,
    val employer : Employer? = null
)
