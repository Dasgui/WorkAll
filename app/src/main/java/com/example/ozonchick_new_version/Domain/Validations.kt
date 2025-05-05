package com.example.ozonchick_new_version.Domain

import com.example.ozonchick_new_version.Data.strings.AuthenticationRegexPatterns

object FieldValidators {
    fun checkEmail (email : String) : Boolean {
        return email.trim().matches(Regex(AuthenticationRegexPatterns.email))
    }

    fun checkPhoneNumber (phoneNumber : String) : Boolean {
        return phoneNumber.trim().matches(Regex(AuthenticationRegexPatterns.phoneNumber))
    }

    fun checkName (name : String) : Boolean {
        return name.trim().matches(Regex(AuthenticationRegexPatterns.name))
    }

    fun checkPassword (password : String) : Boolean {
        return password.trim().matches(Regex(AuthenticationRegexPatterns.password))
    }

    fun checkDigit (password : String) : Boolean {
        return password.trim().matches(Regex(AuthenticationRegexPatterns.digits))
    }
}