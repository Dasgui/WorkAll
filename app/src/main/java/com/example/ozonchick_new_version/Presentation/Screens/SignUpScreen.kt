package com.example.ozonchick_new_version.Screens.SignUpLogIn.SignUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
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
import com.example.ozonchick_new_version.Data.data.db.accessSharedRegistrait
import com.example.ozonchick_new_version.Data.data.db.emailSharedUser
import com.example.ozonchick_new_version.Data.data.variables.firstType_OfRegistration
import com.example.ozonchick_new_version.Data.data.variables.secondType_OfRegistration
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.koinViewModel
import com.example.ozonchick_new_version.Data.data.dto.userType
import com.example.ozonchick_new_version.Presentation.Screens.LogInScreen
import com.example.ozonchick_new_version.Presentation.ViewModels.SignUpViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.ButtonPendingCondition
import com.example.ozonchick_new_version.Presentation.ui.components.SignUp_LogIn_Components.SignUp_LogIn_OutlinedTextField
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.Primary_Color
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondColor

class SignUp_Screen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Create_Account(
            onNavigateForLogIn = {
                navigator?.replaceAll(LogInScreen())
            }
        )
    }
}


@Composable
fun Create_Account(viewModel: SignUpViewModel = koinViewModel(), onNavigateForLogIn:() -> Unit) {
    if (accessSharedRegistrait.value) {
        emailSharedUser.value = viewModel.email.collectAsState().value
        onNavigateForLogIn()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
    )
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 23.dp)
        ) {
            item {
                TextHead()
                MainBody(viewModel)
                BottomTypeRegistraion(viewModel)
                Bottom(viewModel, onNavigateForLogIn)
            }
        }
    }
}

@Composable
private fun TextHead() {
    Column(
        modifier = Modifier.padding(top = 33.dp, bottom = 9.dp)
    ) {
        Text(
            text = "Создать аккаунт",
            style = Tema.Text_24_24_Bold,
            color = MainColor,
        )
        Text(
            text = "Завершите процесс регистрации, чтобы начать",
            color = MainColor,
            modifier = Modifier.padding(top = 8.dp),
            style = Tema.Text_16_16_Medium
        )
    }
}

@Composable
private fun MainBody(viewModel: SignUpViewModel) {
    MainBody_PartContacts(viewModel)
    MainBody_PartPassword(viewModel)
    MainBody_PartTypeUsers(viewModel)
}

@Composable
private fun MainBody_PartContacts(viewModel: SignUpViewModel) {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "Имя",
            style = Tema.Text_24_24_Bold,
            maxLines = 1,
            color = MainColor,
            textDecoration = viewModel.getErrorDisplay(viewModel.numberCorrectFields[0])
        )
        SignUp_LogIn_OutlinedTextField(
            value = viewModel.name.collectAsState().value,
            onValueChange = {
                viewModel.setNameUserFieldValue(it)
                viewModel.checkNameTextField()
                viewModel.dataIsEnteredCorrectly()
            },
            placeholder = {
                Text(
                    text = "Василий",
                    style = Tema.Text_16_16_Medium
                )
            },
        )
    }

    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "Номер телефона",
            style = Tema.Text_24_24_Bold,
            maxLines = 1,
            color = MainColor,
            textDecoration = viewModel.getErrorDisplay(viewModel.numberCorrectFields[1])
        )
        SignUp_LogIn_OutlinedTextField(
            value = viewModel.phoneNumber.collectAsState().value,
            onValueChange = {
                if (it.length < viewModel.maxLengthPhoneNumber) {
                    viewModel.setPhoneNumberFieldValue(it)
                    viewModel.checkPhoneNumberTextField()
                    viewModel.dataIsEnteredCorrectly()
                }
            },
            placeholder = {
                Text(
                    text = "+7**********",
                    style = Tema.Text_16_16_Medium
                )
            },
        )
    }

    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "Адрес почты",
            style = Tema.Text_24_24_Bold,
            maxLines = 1,
            color = MainColor,
            textDecoration = viewModel.getErrorDisplay(viewModel.numberCorrectFields[2])
        )
        SignUp_LogIn_OutlinedTextField(
            value = viewModel.email.collectAsState().value,
            onValueChange = {
                viewModel.setEmailUserFieldValue(it)
                viewModel.checkEmailTextField()
                viewModel.dataIsEnteredCorrectly()
            },
            placeholder = {
                Text(
                    text = "vasili@mail.ru",
                    style = Tema.Text_16_16_Medium
                )
            },
        )
    }
}

@Composable
private fun MainBody_PartPassword(viewModel: SignUpViewModel) {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "Пароль",
            style = Tema.Text_24_24_Bold,
            maxLines = 1,
            color = MainColor,
            textDecoration = viewModel.getErrorDisplay(viewModel.numberCorrectFields[3])
        )
        SignUp_LogIn_OutlinedTextField(
            tagTest = "password",
            value = viewModel.password.collectAsState().value,
            onValueChange = {
                viewModel.setPasswordUserFieldValue(it)
                viewModel.checkPasswordTextField()
                viewModel.dataIsEnteredCorrectly()
            },
            placeholder = {
                Text(
                    text = "******",
                    style = Tema.Text_16_16_Medium
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.eye_slash),
                    contentDescription = "Показать пароль",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            viewModel.setPasswordVisibleFirst(true)
                        },
                    colorFilter = ColorFilter.tint(MainColor)
                )
            },
            visualTransformation = if (viewModel.passwordVisibleFirst.collectAsState().value) VisualTransformation.None else PasswordVisualTransformation(),
        )
    }

    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = "Подтвердите пароль",
            style = Tema.Text_24_24_Bold,
            maxLines = 1,
            color = MainColor,
            textDecoration = viewModel.getErrorDisplay(viewModel.numberCorrectFields[3])
        )
        SignUp_LogIn_OutlinedTextField(
            tagTest = "confirm password",
            value = viewModel.confirmPassword.collectAsState().value,
            onValueChange = {
                viewModel.setConfirmPasswordFieldValue(it)
                viewModel.checkPasswordTextField()
                viewModel.dataIsEnteredCorrectly()
            },
            placeholder = {
                Text(
                    text = "*******",
                    style = Tema.Text_16_16_Medium
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.eye_slash),
                    contentDescription = "Показать пароль",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            viewModel.setPasswordVisibleSecond(true)
                        },
                    colorFilter = ColorFilter.tint(MainColor)
                )
            },
            visualTransformation = if (viewModel.passwordVisibleSecond.collectAsState().value)
                VisualTransformation.None else PasswordVisualTransformation(),
        )
    }
}

@Composable
private fun MainBody_PartTypeUsers(viewModel: SignUpViewModel) {
    if (viewModel.userRole.collectAsState().value == userType.nesoiskat) {
        Column(modifier = Modifier.padding(top = 24.dp)) {
            Text(
                text = "Подтвердите ИНН",
                style = Tema.Text_24_24_Bold,
                maxLines = 1,
                color = MainColor
            )
            SignUp_LogIn_OutlinedTextField(
                value = viewModel.INN.collectAsState().value,
                onValueChange = { viewModel.setInnFieldValue(it) })
        }
    }
}

@Composable
private fun BottomTypeRegistraion(viewModel: SignUpViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = firstType_OfRegistration,
            style = Tema.Text_24_24_Bold,
            color = if (viewModel.userRole.collectAsState().value == userType.soiskat) {
                MainColor
            } else {
                SecondColor
            },
            modifier = Modifier.clickable { viewModel.setUserRoleFieldValue(userType.soiskat) })
        Text(
            text = secondType_OfRegistration,
            style = Tema.Text_24_24_Bold,
            color = if (viewModel.userRole.collectAsState().value == userType.nesoiskat) {
                MainColor
            } else {
                SecondColor
            },
            modifier = Modifier.clickable { viewModel.setUserRoleFieldValue(userType.nesoiskat) })
    }
}


@Composable
private fun Bottom(viewModel: SignUpViewModel, onNavigateForLogIn:() -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 30.dp, bottom = 30.dp)
    ) {
        BottomButton(viewModel)
        BottomText(onNavigateForLogIn)
    }
}

@Composable
private fun BottomButton(viewModel: SignUpViewModel) {
    if (viewModel.isWrittenCorrectly.collectAsState().value) {
        ButtonPendingCondition(
            enabled = viewModel.isWrittenCorrectly.collectAsState().value,
            text = "Зарегистрироваться"
        )
        {
//        Тестовая регистрация
//        Основная регистрация
            viewModel.Registrait()
        }
    }
}

@Composable
private fun BottomText(onNavigateForLogIn:() -> Unit) {

    val signInText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MainColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,

                )
        )
        {
            append("Уже есть аккаунт? ")
        }

        pushStringAnnotation(tag = "click", annotation = "click")

        withStyle(
            SpanStyle(
                color = Primary_Color,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
        ) {
            append("Войти")
        }
    }

    ClickableText(
        text = signInText,
        onClick = {
            signInText.getStringAnnotations(tag = "click", start = it, end = it).firstOrNull()
                ?.let {
                    onNavigateForLogIn()
                }
        },
        modifier = Modifier.padding(top = 20.dp)
    )
}