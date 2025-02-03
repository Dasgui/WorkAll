package com.example.ozonchick_new_version.Screens.SignUpLogIn.LogIn

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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
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
import com.example.ozonchick_new_version.Data_Layer.data.db.emailSharedUser
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.SignUp_LogIn_Components.SignUp_LogIn_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.SignUp_LogIn_Components.SignUp_LogIn_OutlinedTextField
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.backgroundColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.MainFontFamily
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.Home.Profile_Screen
import com.example.ozonchick_new_version.Screens.SignUpLogIn.SignUp.SignUp_Screen
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.LogIn.LogInViewModel
import org.koin.androidx.compose.koinViewModel

class LogInScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Ozonchick_new_versionTheme {
            LogInConstruct(
                onNavigateForProfile = {
                    navigator?.replaceAll(Profile_Screen())
                },
                onNavigateForSignUp = {
                    navigator?.replaceAll(SignUp_Screen())
                }
            )
        }
    }
}

@Composable
private fun LogInConstruct(viewModel: LogInViewModel = koinViewModel(),
                           onNavigateForProfile: () -> Unit, onNavigateForSignUp: () -> Unit) {


    MaterialTheme.colorScheme
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            TextHead()
            MainBody(viewModel)
            BottomBody(viewModel)
            if (viewModel.accessSharedAuth.collectAsState().value) {
                emailSharedUser.value = viewModel.emailUser.collectAsState().value
                onNavigateForProfile()
            }
            BottomText(viewModel, onNavigateForSignUp = onNavigateForSignUp)
        }
    }

}

@Composable
private fun TextHead()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 155.dp)
    ) {
        Text(
            text = "Добро пожаловать",
            style = Tema.Text_24_24_Bold,
            color = MaterialTheme.colorScheme.primary /*Color_MainText.value*/
        )
        Text(
            text = "Введите адрес электронной почты и пароль, чтобы продолжить",
            style = Tema.Text_16_16_Medium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun MainBody(viewModel: LogInViewModel)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Column(
        ) {
            Text(
                text = "Адрес почты",
                style = Tema.Text_24_24_Bold,
                color = MaterialTheme.colorScheme.primary
            )
            SignUp_LogIn_OutlinedTextField(
                value = viewModel.emailUser.collectAsState().value,
                onValueChange = { viewModel.setEmailUserFieldValue (it) },
                placeholder = {
                    Text(
                        text = "*******@mail.com",
                        style = Tema.Text_16_16_Medium
                    )
                }
            )

            Column(
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(text = "Пароль", style = Tema.Text_24_24_Bold, maxLines = 1, color = MaterialTheme.colorScheme.primary)
                SignUp_LogIn_OutlinedTextField(
                    value = viewModel.passwordUser.collectAsState().value,
                    onValueChange = { viewModel.setPasswordUserFieldValue (it) },
                    placeholder = {
                        Text(
                            text = "***********",
                            style = Tema.Text_16_16_Medium
                        )
                    },
                    trailingIcon = {
                        val localImage:MutableState<Int> =remember { mutableStateOf(R.drawable.eye_slash) }
                        Image(
                            painter = painterResource(id = localImage.value),
                            contentDescription = "Показать пароль",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    if (localImage.value == R.drawable.eye_slash) {
                                        localImage.value = R.drawable.eye
                                    } else {
                                        localImage.value = R.drawable.eye_slash
                                    }
                                    viewModel.setPasswordVisible(!viewModel.passwordVisible.value)
                                },
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                        )
                    },
                    visualTransformation = if (viewModel.passwordVisible.collectAsState().value)
                        VisualTransformation.None else PasswordVisualTransformation(),
                )
            }
        }
    }
}

@Composable
private fun BottomBody(viewModel: LogInViewModel)
{
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Checkbox(
            checked = viewModel.rememberUser.collectAsState().value,
            onCheckedChange = { viewModel.setRememberUserCheckBoxValue  (it) },
            modifier = Modifier
                .size(35.dp),
            colors = CheckboxDefaults.colors(
                uncheckedColor = MaterialTheme.colorScheme.primary,
                checkedColor = MaterialTheme.colorScheme.secondary
            ),
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Запомнить пароль",
                color = MaterialTheme.colorScheme.primary,
                style = Tema.Text_16_16_Medium
            )
            Text(
                text = "Забыли пароль?",
                style = Tema.Text_16_16_Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun BottomText(viewModel: LogInViewModel, onNavigateForSignUp: () -> Unit)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 64.dp)
    ) {
        SignUp_LogIn_Button(
            enabled = viewModel.ConstructProverka(viewModel.emailUser.collectAsState().value, viewModel.passwordUser.collectAsState().value),
            onClick = {
                viewModel.Auth()
            },
            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
        ) {
            Text(
                text = "Войти",
                style = Tema.Text_24_24_Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        val SignUpText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color =  MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = MainFontFamily
                )
            )
            {
                append("У вас нет аккаунта? ")
            }

            pushStringAnnotation(tag = "click", annotation = "click")
            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
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
            modifier = Modifier.padding(top = 20.dp).testTag("Registrait")
        )
    }
}