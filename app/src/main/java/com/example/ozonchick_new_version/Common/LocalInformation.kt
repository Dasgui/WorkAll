package com.example.ozonchick_new_version.Common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts

var publicUserId : UserId = UserId(0, UserType.None)
var isLocalVersion : Boolean = false
var isLocalVacanciesCandidateList : List<VakansInformation> = emptyList()


var localListVacancies = mutableListOf<VakansInformation>(
    VakansInformation(
        vakans_id = 1,
        vakans_name = "Дизайнер",
        vakans_price = "100000",
        vakans_organization = "\"ООО\" Создатели",
        vakans_location = "Челябинск",
        vakans_experience = "Без опыта",
        vakans_mail = "gdem@inbox.ru",
        employer_id = 0
    ),
    VakansInformation(
        vakans_id = 2,
        vakans_name = "Программист",
        vakans_price = "50000",
        vakans_organization = "\"ООО\" Некронтиры",
        vakans_location = "Москва",
        vakans_experience = "1-3 года",
        vakans_mail = "gdem@inbox.ru",
        employer_id = 0
    ),
    VakansInformation(
        vakans_id = 3,
        vakans_name = "Программист 1С",
        vakans_price = "85000",
        vakans_organization = "\"ООО\" Строй",
        vakans_location = "Челябинск",
        vakans_experience = "Более 5 лет",
        vakans_mail = "gdem@inbox.ru",
        employer_id = 0
    ),
    VakansInformation(
        vakans_id = 4,
        vakans_name = "Тестировщик",
        vakans_price = "40000",
        vakans_organization = "\"ООО\" Wakfu",
        vakans_location = "Екатеренбург",
        vakans_experience = "3-5 лет",
        vakans_mail = "gdem@inbox.ru",
        employer_id = 0
    ),
    VakansInformation(
        vakans_id = 5,
        vakans_name = "Дизайнер интерьеров",
        vakans_price = "89000",
        vakans_organization = "\"ООО\" Механикус",
        vakans_location = "Челябинск",
        vakans_experience = "Без опыта",
        vakans_mail = "gdem@inbox.ru",
        employer_id = 0
    ),
    VakansInformation(
        vakans_id = 6,
        vakans_name = "Настройщик роутеров",
        vakans_price = "55000",
        vakans_organization = "\"ООО\" NetYou",
        vakans_location = "Москва",
        vakans_experience = "Без опыта",
        vakans_mail = "gdem@inbox.ru",
        employer_id = 0
    ),
)

var localListContacts = mutableListOf(
    Vakans_contacts(
        vakans_id = 1,
        vakans_number = "+7908707174",
        vakans_mail = "cja@mail.ru",
        vakans_raspolog = "Челябинск",
        vakans_aboutWork = "Обязанности:\n" +
                "\n" +
                "Дизайн макетов в CorelDraw, Photoshop\n" +
                "Взаимодействие с нашим производством\n" +
                "Взаимодействие с контрагентами по изготовлению сувенирной продукции\n" +
                "Требования:\n" +
                "\n" +
                "Обязательно: знание графических программ CorelDraw, Photoshop. Будьте готовы к выполнению тестового задания для проверки ваших навыков в CorelDraw\n" +
                "Работа в штате компании (полный рабочий день), не рассматриваем сотрудников на удалёнке и фриланс\n" +
                "Способность задавать вопросы и выявлять потребности\n" +
                "Активная жизненная позиция\n" +
                "Какие мы предлагаем условия:\n" +
                "\n" +
                "Стабильная зарплата два раза в месяц\n" +
                "Работаем комфортно! В светлом офисе с кондиционером, без поездок и командировок\n" +
                "Офис расположен по адресу Свердловский пр. д. 30 Б\n" +
                "График работы: с 9-00 до 18-00 (или с 10-00 до 19-00, на Ваш выбор), понедельник - пятница, суббота, воскресенье - выходные\n" +
                "Отсутствие дресс-кода! Самовыражение - это важно:)\n" +
                "Позитивный, дружный коллектив и адекватное руководство\n" +
                "Возможность карьерного роста – компания активно развивается\n" +
                "У нас интересные задачи и дружеская атмосфера! Присоединяйтесь к нашей команде, чтобы заниматься любимым делом и совершенствовать свое мастерство.\n" +
                "\n" +
                "Будем очень рады вашему отклику!"
    ),
    Vakans_contacts(
        vakans_id = 2,
        vakans_number = "+7301707174",
        vakans_mail = "progarammer24@mail.ru",
        vakans_raspolog = "Москва",
        vakans_aboutWork = "Обязанности:\n" +
                "\n" +
                "Знание языков программирования pyton, Php, java, 1с и т.д\n" +
                "\n" +
                "Работа с базами данных, программирование 1с\n" +
                "\n" +
                "Руководство проектами по внедрению информационных технологий, проработка концепций новых технологий, взаимодействие с отделами, сбор потребностей по автоматизации\n" +
                "\n" +
                "Организация работы отдела\n" +
                "\n" +
                "Требования:\n" +
                "Высшее образование, средне специальное образование\n" +
                "Опыт работы от 3 - х лет\n" +
                "Сертификаты, удостоверения по монтажным работам\n" +
                "Требования к знанию оборудования : 1с, работа с базами данных sql, взаимодействие с по через API\n" +
                "\n" +
                "Условия:\n" +
                "\n" +
                "График работы: 5/2, 8-ми часовой рабочий день\n" +
                "Оформление по ТК РФ, полный соц.пакет\n" +
                "Полная занятость на месте работодателя\n" +
                "Возможности для профессионального роста и обучения\n" +
                "Социальная поддержка сотрудников, включая помощь в решении жилищных вопросов и материальную помощь в обучении"
    ),
    Vakans_contacts(
        vakans_id = 3,
        vakans_number = "+7108057331",
        vakans_mail = "uchetu@mail.ru",
        vakans_raspolog = "Челябинск",
        vakans_aboutWork = "Вам предстоит выполнять:\n" +
                "\n" +
                "Настройка базовых процессов в программных продуктах 1С;\n" +
                "Программирование, администрирование и поддержка конфигурации 1С Бухгалтерия 3.О Корп;\n" +
                "Разработка / доработка правил обмена между конфигурациями;\n" +
                "Техническая поддержка и консультирование пользователей 1С;\n" +
                "Решение задач совместно с опытным программистом 1С.\n" +
                "Преимуществом является:\n" +
                "\n" +
                "Оконченное высшее профессиональное IT образование;\n" +
                "Прохождение дополнительных курсов, обучений;\n" +
                "Базовые знания и навыки программирования;\n" +
                "Готовность и желание развиваться в сфере IT и 1С.\n"
    ),
    Vakans_contacts(
        vakans_id = 4,
        vakans_number = "+7234053230",
        vakans_mail = "polano@mail.ru",
        vakans_raspolog = "Екатеренбург",
        vakans_aboutWork = "Чем предстоит заниматься:\n" +
                "\n" +
                "Проведение регрессионного и функционального тестирования веб-проектов, корпоративных порталов, мобильных приложений;\n" +
                "\n" +
                "Предметный анализ проектной документации;\n" +
                "\n" +
                "Оценка качества работы системы с точки зрения функциональности и соответствия ожиданиям заказчиков;\n" +
                "\n" +
                "Написание тест-кейсов, разработка тестовых спецификаций с использованием различных методик и инструментов тестирования;\n" +
                "\n" +
                "Проведение тестов согласно тестовым спецификациям;\n" +
                "\n" +
                "Анализ полученных результатов и подготовка отчетов для отдела разработки;\n" +
                "\n" +
                "Поиск причин ошибок на сайтах и их устранение;\n" +
                "\n" +
                "Что мы ждем от кандидата:\n" +
                "\n" +
                "Понимание веб-технологий;\n" +
                "\n" +
                "Понимание теоретических основ тестирования;\n" +
                "\n" +
                "Опыт работы в проектах по разработке ПО в качестве QA-инженера/тестировщика;\n" +
                "\n" +
                "Опыт программирования;\n" +
                "\n" +
                "Умение оценивать трудоемкость задач и укладываться в оговоренные сроки;\n" +
                "\n" +
                "Знания основ взаимодействия между клиентом и сервером."
    ),
    Vakans_contacts(
        vakans_id = 5,
        vakans_number = "+7233313131",
        vakans_mail = "disaneme@mail.ru",
        vakans_raspolog = "Челябинск",
        vakans_aboutWork = "Обязанности:\n" +
                "\n" +
                "Произведение замеров нового объекта\n" +
                "Составление планировочного решения с учетом всех технических моментов\n" +
                "Создание концепции будущего интерьера\n" +
                "Подача идей через коллажи\n" +
                "Составление подробного ТЗ для визуализатора и чертежника\n" +
                "Ведение проекта до самой сдачи\n" +
                "Изучение рынка материалов\n" +
                "Работа с аналогами\n" +
                "Общение с клиентами\n" +
                "Требования:\n" +
                "Знание Adobe Photoshop, Archicad\n" +
                "Опыт работы не менее 1-ого года на аналогичной должности\n" +
                "Дизайнерское/архитектурное высшее образование"
    ),
    Vakans_contacts(
        vakans_id = 6,
        vakans_number = "+7236033121",
        vakans_mail = "localconner@inbox.ru",
        vakans_raspolog = "Челябинск",
        vakans_aboutWork = "ЧЕМ ПРЕДСТОИТ ЗАНИМАТЬСЯ\n" +
                "подключать услуги домашнего интернета и ТВ: проводить монтаж оборудования в доме абонента, устранять неисправности при необходимости\n" +
                "помогать клиентам выбрать тариф, настроить роутер или ТВ приставку, показывать, как работает сервис."
    ),
)

var employerListVacancies = mutableListOf<VakansInformation>(
    localListVacancies[4],
    localListVacancies[5],
)

val candidateListVacancies = mutableListOf<VakansInformation>(
    localListVacancies[1]
)

var addCount : MutableState<Int> = mutableIntStateOf(6)
