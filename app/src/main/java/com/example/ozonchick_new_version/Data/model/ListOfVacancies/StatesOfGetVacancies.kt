package com.example.ozonchick_new_version.Data.model.ListOfVacancies

sealed interface StatesOfGetVacancies {
    data object Normal : StatesOfGetVacancies
    data class Filter (val search : String, val filterSalary : String, val filterExperience : String) : StatesOfGetVacancies
}