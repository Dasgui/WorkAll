package com.example.ozonchick_new_version.Data.strings

enum class AppErrors(val description : String) {
    UNKNOWN("Неизвестная ошибка"),
    INTERNET("Возникла проблема с подключением. Попробуйте позже или проверьте своё соединение с интернетом."),
    UPDATE_INFORMATION("Не удалось обновить информацию"),
    INCORRECT_VALUES("Не все поля заполнены или заполнены некоректно. Проверить вписанные значения."),

    VACANCIES_ALREADY_EXIST("Вакансия уже добавлена в ваши отклики"),

    RESUME_NOT_EXIST("Изините, но у этого пользователя ещё нет резюме.")
}