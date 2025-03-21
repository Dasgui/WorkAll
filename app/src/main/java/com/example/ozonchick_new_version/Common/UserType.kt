package com.example.ozonchick_new_version.Common

import com.example.ozonchick_new_version.Data.model.SigLogInModels.User

sealed interface UserType {
    object Candidate : UserType
    object Employer : UserType
    object None : UserType
}