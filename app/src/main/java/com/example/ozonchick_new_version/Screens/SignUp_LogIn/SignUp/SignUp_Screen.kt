package com.example.ozonchick_new_version.Screens.SignUp_LogIn.SignUp

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.ozonchick_new_version.Data_Layer.data.db.accessSharedRegistrait
import com.example.ozonchick_new_version.Data_Layer.data.db.emailSharedUser
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.SignUp_LogIn_Components.SignUp_LogIn_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.SignUp_LogIn_Components.SignUp_LogIn_OutlinedTextField
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.backgroundColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.mainColor
import com.example.ozonchick_new_version.Data_Layer.data.variables.firstType_OfRegistration
import com.example.ozonchick_new_version.Data_Layer.data.variables.secondType_OfRegistration
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.koinViewModel
import com.example.ozonchick_new_version.Data_Layer.data.dto.userType
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Primary_Color
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.LogIn_Screen

class SignUp_Screen : Screen {
    @Composable
    override fun Content() {
        Create_Account()
    }
}

@Composable
fun SignUp_Construct() {

}

@Composable
fun Create_Account(viewModel: SignUpViewModel = koinViewModel()) {
//    val screen = rememberScreen(provider = SignUp_LogIn_Provider.LogIn)
    val context = LocalContext.current
    val navigator = LocalNavigator.current
    if (accessSharedRegistrait.value) {
        emailSharedUser.value = viewModel.email.collectAsState().value
        navigator?.replaceAll(LogIn_Screen())
    }
//    val accessRegistrait = vm.accessRegistrait.collectAsState()
    /*    var text by remember { mutableStateOf("") }
        val checkedState = remember { mutableStateOf(false) }*/
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
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
                Bottom(viewModel)
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
            color = mainColor
        )
        Text(
            text = "Завершите процесс регистрации, чтобы начать",
            color = mainColor,
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
        Text(text = "Имя", style = Tema.Text_24_24_Bold, maxLines = 1, color = mainColor)
        SignUp_LogIn_OutlinedTextField(
            value = viewModel.name.collectAsState().value,
            onValueChange = {
                viewModel.setNameUserFieldValue(it)
                viewModel.checkNameTextField()
                viewModel.dataIsEnteredCorrectly()},
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
            color = mainColor
        )
        SignUp_LogIn_OutlinedTextField(
            value = viewModel.phoneNumber.collectAsState().value,
            onValueChange = {
                viewModel.setPhoneNumberFieldValue(it)
                viewModel.checkPhoneNumberTextField()
                viewModel.dataIsEnteredCorrectly()},
            placeholder = {
                Text(
                    text = "+7*****",
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
            color = mainColor
        )
        SignUp_LogIn_OutlinedTextField(
            value = viewModel.email.collectAsState().value,
            onValueChange = {
                viewModel.setEmailUserFieldValue(it)
                viewModel.checkEmailTextField()
                viewModel.dataIsEnteredCorrectly()},
            placeholder = {
                Text(
                    text = "vasili@mail.ru",
                    style = Tema.Text_16_16_Medium
                )
            },
            colors = viewModel.getColorScheme()
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
            color = mainColor
        )
        SignUp_LogIn_OutlinedTextField(
            value = viewModel.password.collectAsState().value,
            onValueChange = {
                viewModel.setPasswordUserFieldValue(it)
                viewModel.checkPasswordTextField()
                viewModel.dataIsEnteredCorrectly()},
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
                    colorFilter = ColorFilter.tint(mainColor)
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
            color = mainColor
        )
        SignUp_LogIn_OutlinedTextField(
            value = viewModel.confirmPassword.collectAsState().value,
            onValueChange = {
                viewModel.setConfirmPasswordFieldValue(it)
                viewModel.checkPasswordTextField()
                viewModel.dataIsEnteredCorrectly()},
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
                    colorFilter = ColorFilter.tint(mainColor)
                )
            },
            visualTransformation = if (viewModel.passwordVisibleSecond.collectAsState().value) VisualTransformation.None else PasswordVisualTransformation(),
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
                color = mainColor
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
            color = mainColor,
            modifier = Modifier.clickable { viewModel.setUserRoleFieldValue(userType.soiskat) })
        Text(
            text = secondType_OfRegistration,
            style = Tema.Text_24_24_Bold,
            color = mainColor,
            modifier = Modifier.clickable { viewModel.setUserRoleFieldValue(userType.nesoiskat) })
    }
}


@Composable
private fun Bottom(viewModel: SignUpViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 30.dp, bottom = 30.dp)
    ) {
        BottomButton(viewModel)
        BottomText()
    }
}

@Composable
private fun BottomButton(viewModel: SignUpViewModel) {

    SignUp_LogIn_Button(
        onClick = {
            viewModel.Registrait()
        },
        colors = if (viewModel.isWrittenCorrectly.collectAsState().value) {
            ButtonDefaults.buttonColors(containerColor = mainColor, contentColor = backgroundColor)
        } else {
            ButtonDefaults.buttonColors(containerColor = backgroundColor, contentColor = mainColor)
        },
        enabled = viewModel.isWrittenCorrectly.collectAsState().value
    ) {
        Text(
            text = "Зарегистрироваться",
            style = Tema.Text_24_24_Bold,
//            color = if (viewModel.Proverka()) {
//                backgroundColor
//            } else {
//                mainColor
//            }
        )
    }
}

@Composable
private fun BottomText() {
    val navigator = LocalNavigator.current

    val signInText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = mainColor,
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
                    navigator?.replaceAll(LogIn_Screen())
                }
        },
        modifier = Modifier.padding(top = 20.dp)
    )
}