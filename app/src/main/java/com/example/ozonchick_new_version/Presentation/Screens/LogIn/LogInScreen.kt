package com.example.ozonchick_new_version.Presentation.Screens.LogIn

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Presentation.Screens.ProfileScreen
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.States.TextFieldStates
import com.example.ozonchick_new_version.Presentation.ui.components.CustomOutlinedTextField
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.Button_Color_Blue
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Screens.SignUpLogIn.SignUp.SignUp_Screen
import org.koin.androidx.compose.koinViewModel

class LogInScreen : Screen {
    @Composable
    override fun Content() {
        LogInConstruct()
    }
}

@Composable
private fun LogInConstruct(
    viewModel: LogInViewModel = koinViewModel()
) {
    val navigator = LocalNavigator.current
    LazyColumn(
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.Companion.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                TextHead()
                MainBody(viewModel)
                BottomBody(viewModel)
                BottomText(
                    viewModel,
                    onNavigateForSignUp = { navigator?.replaceAll(SignUp_Screen()) })
            }
        }

    }

}

@Composable
private fun TextHead() {
    Column(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(top = 120.dp)
    ) {
        Text(
            text = "Добро пожаловать",
            style = Tema.Text_24_24_Bold,
            color = MainColor,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        VerticalSpacer(10.dp)
        Text(
            text = "Введите адрес электронной почты и пароль, чтобы продолжить.",
            style = Tema.Text_20_20_Medium,
            color = MainColor,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MainBody(viewModel: LogInViewModel) {
    Column(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(top = 40.dp)
    ) {
        Column(
        ) {
            Text(
                text = "Адрес почты",
                style = Tema.Text_24_24_Bold,
                color = MainColor
            )
            VerticalSpacer(10.dp)
            CustomOutlinedTextField(
                value = viewModel.emailUser.collectAsState().value,
                onValueChange = {
                    viewModel.setEmailUserFieldValue(it)
                    viewModel.isDataValid()
                },
                states = TextFieldStates.Email
            )

            Column(
                modifier = Modifier.Companion.padding(top = 20.dp)
            ) {
                Text(
                    text = "Пароль",
                    style = Tema.Text_24_24_Bold,
                    maxLines = 1,
                    color = MainColor
                )
                VerticalSpacer(10.dp)
                CustomOutlinedTextField(
                    value = viewModel.passwordUser.collectAsState().value,
                    onValueChange = {
                        viewModel.setPasswordUserFieldValue(it)
                        viewModel.isDataValid()
                    },
                    states = TextFieldStates.Password
                )
            }
        }
    }
}

@Composable
private fun BottomBody(viewModel: LogInViewModel) {
    Row(
        verticalAlignment = Alignment.Companion.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Checkbox(
            checked = viewModel.rememberUser.collectAsState().value,
            onCheckedChange = { viewModel.setRememberUserCheckBoxValue(it) },
            modifier = Modifier.Companion
                .size(35.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = MainColor,
                uncheckedColor = MainColor,
                checkmarkColor = BackgroundColor
            ),
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.Companion.fillMaxWidth()
        ) {
            Text(
                text = "Запомнить пароль",
                color = MainColor,
                style = Tema.Text_20_20_Medium,
                textAlign = TextAlign.Left
            )
            Text(
                text = "Забыли пароль?",
                style = Tema.Text_20_20_Medium,
                color = MainColor,
                textAlign = TextAlign.Right
            )
        }
    }
}

@Composable
private fun BottomText(
    viewModel: LogInViewModel,
    onNavigateForSignUp: () -> Unit
) {
    val screenState = viewModel.logInState.collectAsState()
    val buttonState = viewModel.isDataCorrect.collectAsState()
    val navigator = LocalNavigator.current
    Column(
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 64.dp).fillMaxWidth()
    ) {
        Button(
            onClick = {
                viewModel.Auth()
            },
            modifier = Modifier.Companion.fillMaxWidth().height(45.dp),
            shape = RectangleShape,
            border = BorderStroke(1.dp, MainColor),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundColor,
                contentColor = MainColor,
                disabledContainerColor = BackgroundColor,
                disabledContentColor = MainColor
            )
        ) {
            Text(
                text = "Войти",
                style = Tema.Text_24_24_Bold,
            )
        }

        when (val curState = screenState.value) {
            is ScreenState.Failure -> FailAlertDialog(
                title = "Ошибка входа",
                curState.message,
                onOkClick = {
                    viewModel.logInState.value = ScreenState.Loading
                })

            ScreenState.Loading -> Unit
            is ScreenState.Success -> navigator?.replaceAll(ProfileScreen(curState.data.user_id))
        }


        val SignUpText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MainColor
                )
            )
            {
                append("У вас нет аккаунта? ")
            }

            pushStringAnnotation(tag = "click", annotation = "click")
            withStyle(
                SpanStyle(
                    color = Button_Color_Blue
                )
            ) {
                append("Зарегистрироваться")
            }
        }
        Text(
            text = SignUpText,
             modifier = Modifier.padding(top = 40.dp).fillMaxWidth().clickable {
                 onNavigateForSignUp()
            },
            style = Tema.Text_20_20_Medium,
            textAlign = TextAlign.Center
        )
    }
}