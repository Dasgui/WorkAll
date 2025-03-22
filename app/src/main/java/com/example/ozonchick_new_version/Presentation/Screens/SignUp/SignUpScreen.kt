package com.example.ozonchick_new_version.Screens.SignUpLogIn.SignUp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Common.UserType
import org.koin.androidx.compose.koinViewModel
import com.example.ozonchick_new_version.Presentation.Screens.LogIn.LogInScreen
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.Screens.SignUp.SignUpViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.CustomButton
import com.example.ozonchick_new_version.Presentation.ui.components.CustomOutlinedTextField
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.NameTextField
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.Button_Color_Blue
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor

class SignUpScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        SignUpConstruct(
            onNavigateForLogIn = {
                navigator?.replaceAll(LogInScreen())
            }
        )
    }
}

@Composable
fun SignUpConstruct(viewModel: SignUpViewModel = koinViewModel(), onNavigateForLogIn: () -> Unit) {
    val screenState = viewModel.signUpState.collectAsState()
    val navigator = LocalNavigator.current
    when (val curState = screenState.value) {
        is ScreenState.Failure -> FailAlertDialog(
            title = "Ошибка регистрации",
            curState.message,
            onOkClick = {
                viewModel.signUpState.value = ScreenState.Loading
            })
        is ScreenState.Loading -> Unit
        is ScreenState.Success -> navigator?.replaceAll(LogInScreen())
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(start = 24.dp, end = 24.dp, top = 120.dp, bottom = 50.dp)
    ) {
        item {
            TextHead()
            VerticalSpacer(15.dp)
            MainBody(viewModel)
            BottomTypeRegistration(viewModel)
            Bottom(viewModel, onNavigateForLogIn)
        }
    }
}

@Composable
private fun TextHead() {
    Column(
        modifier = Modifier.fillMaxWidth()
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
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        NameTextField(
            title = "Имя",
            holderText = "Василий",
            field = viewModel.name,
            maxChar = 21
        )
        NameTextField(
            title = "Фамилия",
            holderText = "Иванович",
            field = viewModel.secondName,
            maxChar = 21
        )
        NameTextField(
            title = "Номер телефона",
            holderText = "+7**********",
            field = viewModel.phoneNumber,
            maxChar = 12
        )
        NameTextField(
            title = "Адрес почты",
            holderText = "vasili@mail.ru",
            field = viewModel.email
        )
        VerticalSpacer(5.dp)
        NameTextField(
            title = "Пароль",
            holderText = "******",
            field = viewModel.password,
            maxChar = 20,
        )
        NameTextField(
            title = "Подтвердите пароль",
            holderText = "******",
            field = viewModel.confirmPassword,
            maxChar = 20,
        )
        if (viewModel.userRole.value == UserType.Employer) {
            NameTextField(
                title = "Название организации",
                holderText = "******",
                field = viewModel.organizationName,
                maxChar = 50,
            )
            NameTextField(
                title = "Подтвердите ИНН",
                holderText = "",
                field = viewModel.INN,
                maxChar = 12,
                onlyNumbers = true
            )
        }
    }
}

//@Composable
//private fun NameTextField(
//    title: String,
//    holderText: String,
//    field: MutableState<String>,
//    maxChar: Int = 0,
//    onlyNumbers : Boolean = false,
//) {
//    Column(
//        modifier = Modifier.fillMaxWidth(),
//        verticalArrangement = Arrangement.spacedBy(10.dp)
//    ) {
//        Text(
//            text = title,
//            style = Tema.Text_24_24_Bold,
//            maxLines = 1,
//            color = MainColor,
//        )
//        CustomOutlinedTextField(
//            value = field.value,
//            onValueChange = {
//                if (maxChar == 0) {
//                    field.value = it
//                } else {
//                    if (onlyNumbers) {
//                        if (it.length <= maxChar && it.isDigitsOnly()) {
//                            field.value = it
//                        }
//                    } else {
//                        if (it.length <= maxChar) {
//                            field.value = it
//                        }
//                    }
//                }
//            },
//            placeText = holderText,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//        )
//    }
//}

@Composable
private fun BottomTypeRegistration(viewModel: SignUpViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Соискатель",
            style = Tema.Text_24_24_Bold,
            color = if (viewModel.userRole.value == UserType.Candidate) {
                MainColor
            } else {
                Button_Color_Blue
            },
            modifier = Modifier.clickable { viewModel.userRole.value = UserType.Candidate })
        Text(
            text = "Работадатель",
            style = Tema.Text_24_24_Bold,
            color = if (viewModel.userRole.value == UserType.Employer) {
                MainColor
            } else {
                Button_Color_Blue
            },
            modifier = Modifier.clickable { viewModel.userRole.value = UserType.Employer })
    }
}


@Composable
private fun Bottom(viewModel: SignUpViewModel, onNavigateForLogIn: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 30.dp, bottom = 30.dp)
    ) {
        CustomButton(
            onClick = {
                if (viewModel.checkEmail() && viewModel.checkName()
                    && viewModel.checkInn() && viewModel.checkPhoneNumber() && viewModel.checkPasswordMatch()) {
                    if (viewModel.userRole.value == UserType.Candidate) {
                        viewModel.registrationCandidate()
                    }
                    if (viewModel.userRole.value == UserType.Employer) {
                        viewModel.registrationEmployer()
                    }
                } else {
                    viewModel.signUpState.value = ScreenState.Failure(0, "Вы ошиблись при заполнении данных. Пожалуйста проверьте их.")
                }
//                if (viewModel.checkName() && viewModel.checkEmail()
//                    && viewModel.checkPhoneNumber() && viewModel.checkPasswordMatch() && viewModel.userRole.value is UserType.Candidate
//                ) {
//                    Log.d("Проверяем поля", "ТЫК")
//                    viewModel.registrationCandidate()
//                } else if (viewModel.checkName() && viewModel.checkEmail()
//                    && viewModel.checkPhoneNumber() && viewModel.checkInn()
//                    && viewModel.checkPasswordMatch() && viewModel.userRole.value is UserType.Employer) {
//                    Log.d("Проверяем поля", "ТЫК")
//                    viewModel.registrationEmployer()
//                }
            },
            placeText = "Зарегистрироваться"
        )
        BottomText(onNavigateForLogIn)
    }
}


@Composable
private fun BottomText(onNavigateForLogIn: () -> Unit) {

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
                color = MainColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
        ) {
            append("Войти")
        }
    }

    Text(
        text = signInText,
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth()
            .clickable {
                onNavigateForLogIn()
            },
        style = Tema.Text_20_20_Medium,
        textAlign = TextAlign.Center
    )
}