package com.example.ozonchick_new_version.Data.strings

data object AuthenticationRegexPatterns {
    val email = "^[a-z-а-я-0-9]+@[a-z-а-я-0-9]+.[a-z-а-я]{2,}$"
    val phoneNumber = "^\\+\\d{11}"
    val name = "[А-Яа-яЁёa-zA-Z]{3,21}"
    val inn = "\\d{12}"
    val password = "^\\S+$"
}