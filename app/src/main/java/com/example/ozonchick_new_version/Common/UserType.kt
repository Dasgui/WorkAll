package com.example.ozonchick_new_version.Common


sealed class UserType {
    object Candidate : UserType()
    object Employer : UserType()
    object Admin : UserType()
    object None : UserType()
}