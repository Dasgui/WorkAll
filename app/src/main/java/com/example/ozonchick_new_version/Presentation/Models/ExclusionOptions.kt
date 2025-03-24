package com.example.ozonchick_new_version.Presentation.Models

sealed class ExclusionOptions(val message : String, val id : Int) {
    data object NetworkError : ExclusionOptions("Проверьте своё соединение с интернетом", 0)
    data object UserExistsError : ExclusionOptions("Такой пользователь уже существует. Пожалуйста проверьте введенные данные.", 1)
    data object UnknownError : ExclusionOptions("Неизвестная ошибка. Попробуйте перезапустить приложение.", 2)
    data object VacancyIsAlreadyExists : ExclusionOptions("Вы уже добавили эту вакансию", 3)
}

open class TextFieldError {
    fun getError (textFieldName : String, description : String) : String {
        return "Вы ошиблись в поле $textFieldName $description"
    }
}