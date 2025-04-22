package com.example.ozonchick_new_version.Presentation.States

sealed interface ScaffoldStates {
    object OnSide : ScaffoldStates
    object Main : ScaffoldStates
    object EmployerVacancies : ScaffoldStates
    object Vacancies : ScaffoldStates
}