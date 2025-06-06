package com.example.ozonchick_new_version.Data.strings

data object AuthenticationRegexPatterns {
    val email = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    val phoneNumber = "^\\+\\d{11}"
    val name = "[А-Яа-яЁёa-zA-Z]{3,21}"
    val inn = "\\d{12}"
    val password = "^\\S+$"
    val digits = "^\\d+$"
}