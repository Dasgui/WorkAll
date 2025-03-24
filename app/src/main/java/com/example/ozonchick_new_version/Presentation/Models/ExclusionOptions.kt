package com.example.ozonchick_new_version.Presentation.Models

sealed class ExclusionOptions(val message : String, val id : Int) {
    data object NetworkError : ExclusionOptions("Проверьте своё соединение с интернетом", 0)
    data object UserExistsError : ExclusionOptions("Такой пользователь уже существует. Пожалуйста проверьте введенные данные.", 1)
    data object UnknownError : ExclusionOptions("Неизвестная ошибка. Попробуйте перезапустить приложение.", 3)
}
