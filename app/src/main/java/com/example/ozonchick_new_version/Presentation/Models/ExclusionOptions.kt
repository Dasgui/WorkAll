package com.example.ozonchick_new_version.Presentation.Models

sealed class ExclusionOptions(val message : String, val id : Int) {
    data object NetworkError : ExclusionOptions("Проверьте своё соединение с интернетом", 0)
    data object UserExistsError : ExclusionOptions("Пожалуйста, проверьте свой пароль и почту и попробуйте снова.", 1)
    data object UnknownError : ExclusionOptions("Неизвестная ошибка. Попробуйте перезапустить приложение.", 2)
    data object VacancyIsAlreadyExists : ExclusionOptions("Вы уже добавили эту вакансию", 3)
    data object UserIsAlreadyExists : ExclusionOptions("Такой пользователь уже существует. Пожалуйста проверьте введенные данные.", 4)
}

sealed class TextFieldError {
    object get {
        fun getError (textFieldName : String, description : String) : String {
            return "Вы ошиблись в поле $textFieldName $description"
        }
    }

}