package com.example.ozonchick_new_version.Common


sealed interface UserType {
    object Candidate : UserType
    object Employer : UserType
    object None : UserType
}