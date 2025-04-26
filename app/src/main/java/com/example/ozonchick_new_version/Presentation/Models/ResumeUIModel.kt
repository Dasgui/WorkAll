package com.example.ozonchick_new_version.Presentation.Models

sealed interface ResumeUIModel {
    object Education : ResumeUIModel
    object Experience : ResumeUIModel
    object Information : ResumeUIModel
}