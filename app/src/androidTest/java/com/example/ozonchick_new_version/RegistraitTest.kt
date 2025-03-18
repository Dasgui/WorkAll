package com.example.ozonchick_new_version

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.ozonchick_new_version.Presentation.MainActivity
import org.junit.Rule
import org.junit.Test

class RegistraitTest {
    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun LogInCorrect_Test() : Unit = with(rule)
    {
        val nameField = "Василий"
        val phoneNumberField = "+7**********"
        val mailField = "vasili@mail.ru"
        val passwordField = "password"
        val confirmPasswordField = "confirm password"

        val name = "Ana"
        val phoneNumber = "+79514414145"
        val mail = "tana@mail.ru"
        val password = "123456"

        onNodeWithTag("Registrait").performClick()
        waitUntil(timeoutMillis = 3000) {
            onNodeWithText("Создать аккаунт").isDisplayed()
        }

        onNodeWithText(nameField).performTextInput(name)
        onNodeWithText(phoneNumberField).performTextInput(phoneNumber)
        onNodeWithText(mailField).performTextInput(mail)
        onNodeWithTag(passwordField).performTextInput(password)
        onNodeWithTag(confirmPasswordField).performTextInput(password)

        waitUntil(timeoutMillis = 3000) {
            onNodeWithText("Зарегистрироваться").isDisplayed()
        }
        onNodeWithText("Зарегистрироваться").performClick()

    }

    @Test
    fun LogInUnCorrect_Test() : Unit = with(rule)
    {
        val nameField = "Василий"
        val phoneNumberField = "+7**********"
        val mailField = "vasili@mail.ru"
        val passwordField = "password"
        val confirmPasswordField = "confirm password"

        val repeatname = "Тест"
        val repeatphoneNumber = "+79056554562"
        val repeatmail = "test06@inbox.ru"
        val repeatpassword = "1234567"

        onNodeWithTag("Registrait").performClick()
        waitUntil(timeoutMillis = 3000) {
            onNodeWithText("Создать аккаунт").isDisplayed()
        }

        onNodeWithText(nameField).performTextInput(repeatname)
        onNodeWithText(phoneNumberField).performTextInput(repeatphoneNumber)
        onNodeWithText(mailField).performTextInput(repeatmail)
        onNodeWithTag(passwordField).performTextInput(repeatpassword)
        onNodeWithTag(confirmPasswordField).performTextInput(repeatpassword)

        waitUntil(timeoutMillis = 10000) {
            onNodeWithText("Зарегистрироваться").isDisplayed()
        }

        onNodeWithText("Зарегистрироваться").performClick()
    }
}