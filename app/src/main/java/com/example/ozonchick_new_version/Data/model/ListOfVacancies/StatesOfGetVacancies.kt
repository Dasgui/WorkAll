package com.example.ozonchick_new_version.Data.model.ListOfVacancies

import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet

sealed interface StatesOfGetVacancies {
    data object Normal : StatesOfGetVacancies
    data class Filter(
        val search: String,
        val filterSalary: String,
        val filterExperience: ExperienceGet,
        val filterExperienceList: List<ExperienceGet>
    ) : StatesOfGetVacancies
}