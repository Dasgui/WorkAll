package com.example.ozonchick_new_version

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import com.example.ozonchick_new_version.Screens.MainActivity
import org.junit.Rule
import org.junit.Test

class RegistraitTest {
    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun LogIn_Test() : Unit = with(rule)
    {
        val mailText = "*******@mail.com"
        val passwordText = "***********"

        val mail = "test02@inbox"
        val password = "test02"
        onNodeWithText("Зарегистрироваться").performClick()
        waitUntil(timeoutMillis = 3000) {
            onNodeWithText("Создать аккаунт").isDisplayed()
        }

//        onRoot().printToLog("UI Test")
//        onNodeWithText(mailText).performTextInput(mail)
//        onNodeWithText(passwordText).performTextInput(password)
//        onNodeWithText("Войти").performClick()
//        waitUntil(timeoutMillis = 3000) {
//            onNodeWithText("Test02").isDisplayed()
//        }

    }
}