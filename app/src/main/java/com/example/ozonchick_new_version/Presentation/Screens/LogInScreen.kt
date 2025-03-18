package com.example.ozonchick_new_version.Presentation.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data.data.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Data.data.ui.components.SignUp_LogIn_Components.SignUp_LogIn_OutlinedTextField
import com.example.ozonchick_new_version.Data.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data.data.ui.theme.Button_Color_Blue
import com.example.ozonchick_new_version.Data.data.ui.theme.MainFontFamily
import com.example.ozonchick_new_version.Data.data.ui.theme.backgroundColor
import com.example.ozonchick_new_version.Data.data.ui.theme.mainColor
import com.example.ozonchick_new_version.Data.data.ui.theme.secondaryElementColor
import com.example.ozonchick_new_version.Presentation.ScreenState
import com.example.ozonchick_new_version.Presentation.ViewModels.LogInViewModel
import com.example.ozonchick_new_version.R
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
    Box(
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(backgroundColor)
    ) {
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

@Composable
private fun TextHead() {
    Column(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(top = 155.dp)
    ) {
        Text(
            text = "Добро пожаловать",
            style = Tema.Text_24_24_Bold,
            color = mainColor /*Color_MainText.value*/
        )
        Text(
            text = "Введите адрес электронной почты и пароль, чтобы продолжить",
            style = Tema.Text_16_16_Medium,
            color = mainColor,
            modifier = Modifier.Companion.padding(top = 8.dp)
        )
    }
}

@Composable
private fun MainBody(viewModel: LogInViewModel) {
    Column(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Column(
        ) {
            Text(
                text = "Адрес почты",
                style = Tema.Text_24_24_Bold,
                color = mainColor
            )
            SignUp_LogIn_OutlinedTextField(
                value = viewModel.emailUser.collectAsState().value,
                onValueChange = {
                    viewModel.setEmailUserFieldValue(it)
                    viewModel.isDataValid()
                },
                placeholder = {
                    Text(
                        text = "*******@mail.com",
                        style = Tema.Text_16_16_Medium
                    )
                }
            )

            Column(
                modifier = Modifier.Companion.padding(top = 24.dp)
            ) {
                Text(
                    text = "Пароль",
                    style = Tema.Text_24_24_Bold,
                    maxLines = 1,
                    color = mainColor
                )
                SignUp_LogIn_OutlinedTextField(
                    value = viewModel.passwordUser.collectAsState().value,
                    onValueChange = {
                        viewModel.setPasswordUserFieldValue(it)
                        viewModel.isDataValid()
                    },
                    placeholder = {
                        Text(
                            text = "***********",
                            style = Tema.Text_16_16_Medium
                        )
                    },
                    trailingIcon = {
                        val localImage: MutableState<Int> =
                            remember { mutableStateOf(R.drawable.eye_slash) }
                        Image(
                            painter = painterResource(id = localImage.value),
                            contentDescription = "Показать пароль",
                            contentScale = ContentScale.Companion.Crop,
                            modifier = Modifier.Companion
                                .size(24.dp)
                                .clickable {
                                    if (localImage.value == R.drawable.eye_slash) {
                                        localImage.value = R.drawable.eye
                                    } else {
                                        localImage.value = R.drawable.eye_slash
                                    }
                                    viewModel.setPasswordVisible(!viewModel.passwordVisible.value)
                                },
                            colorFilter = ColorFilter.Companion.tint(mainColor)
                        )
                    },
                    visualTransformation = if (viewModel.passwordVisible.collectAsState().value)
                        VisualTransformation.Companion.None else PasswordVisualTransformation(),
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
            .padding(top = 10.dp)
    ) {
        Checkbox(
            checked = viewModel.rememberUser.collectAsState().value,
            onCheckedChange = { viewModel.setRememberUserCheckBoxValue(it) },
            modifier = Modifier.Companion
                .size(35.dp),
            colors = CheckboxDefaults.colors(
                uncheckedColor = mainColor,
                checkedColor = secondaryElementColor
            ),
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.Companion.fillMaxWidth()
        ) {
            Text(
                text = "Запомнить пароль",
                color = mainColor,
                style = Tema.Text_16_16_Medium
            )
            Text(
                text = "Забыли пароль?",
                style = Tema.Text_16_16_Medium,
                color = mainColor
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
        modifier = Modifier.Companion.padding(top = 64.dp)
    ) {
        Button(
            onClick = {
                viewModel.Auth()
            },
            modifier = Modifier.Companion.fillMaxWidth().height(45.dp),
            shape = RectangleShape,
            border = BorderStroke(1.dp, mainColor),
            enabled = buttonState.value,
            colors = ButtonDefaults.buttonColors(
                containerColor = mainColor,
                contentColor = backgroundColor,
                disabledContainerColor = backgroundColor,
                disabledContentColor = mainColor
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
                    color = mainColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Companion.Medium,
                    fontFamily = MainFontFamily
                )
            )
            {
                append("У вас нет аккаунта? ")
            }

            pushStringAnnotation(tag = "click", annotation = "click")
            withStyle(
                SpanStyle(
                    color = Button_Color_Blue,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Companion.Medium,
                    fontFamily = MainFontFamily
                )
            ) {
                append("Зарегистрироваться")
            }
        }
        ClickableText(
            text = SignUpText,
            onClick = {
                SignUpText.getStringAnnotations(tag = "click", start = it, end = it).firstOrNull()
                    ?.let {
                        onNavigateForSignUp()
                    }
            },
            modifier = Modifier.Companion
                .padding(top = 20.dp)
                .testTag("Registrait")
        )
    }
}