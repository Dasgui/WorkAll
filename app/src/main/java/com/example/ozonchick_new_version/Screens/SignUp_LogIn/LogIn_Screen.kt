package com.example.ozonchick_new_version.Screens.SignUp_LogIn

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.db.emailSharedUser
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.SignUp_LogIn_Components.SignUp_LogIn_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.SignUp_LogIn_Components.SignUp_LogIn_OutlinedTextField
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Background
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Gray_Color_Passive_Secondary
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.MainFontFamily
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Primary_Color
import com.example.ozonchick_new_version.Presentation_Layer.Models.SignUp_ViewModel
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.Home.Profile_Screen
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.Home_Provider
import org.koin.androidx.compose.koinViewModel

class LogIn_Screen : Screen {
    @Composable
    override fun Content() {
        LogIn_Construct()
    }
}

@Composable
fun LogIn_Construct(viewModel: SignUp_ViewModel = koinViewModel()) {
    val navigator = LocalNavigator.current
//    val authState = vm.accessSharedAuth.collectAsState()

    MaterialTheme.colorScheme
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color_Background.value)
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
                navigator?.replaceAll(Profile_Screen())
            }
            BottomText(viewModel, onNavigateForSignUp = {navigator?.replaceAll(SignUp_Screen())})
        }
    }

}

@Composable
fun TextHead()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 155.dp)
    ) {
        Text(
            text = "Добро пожаловать",
            style = Tema.Text_24_24_Bold,
            color = Color_MainText.value
        )
        Text(
            text = "Введите адрес электронной почты и пароль, чтобы продолжить",
            style = Tema.Text_16_16_Medium,
            color = Color_MainText.value,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun MainBody(viewModel: SignUp_ViewModel)
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
                color = Color_MainText.value
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
            var passwordVisible by rememberSaveable { mutableStateOf(false) }
            Column(
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(text = "Пароль", style = Tema.Text_24_24_Bold, maxLines = 1, color = Color_MainText.value)
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
                                    passwordVisible = !passwordVisible
                                },
                            colorFilter = ColorFilter.tint(Color_MainText.value)
                        )
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                )
            }
        }
    }
}

@Composable
fun BottomBody(viewModel: SignUp_ViewModel)
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
                uncheckedColor = Gray_Color_Passive_Secondary,
                checkedColor = Primary_Color
            ),
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Запомнить пароль",
                color = Color_MainText.value,
                style = Tema.Text_16_16_Medium
            )
            Text(
                text = "Забыли пароль?",
                style = Tema.Text_16_16_Medium,
                color = Primary_Color
            )
        }
    }
}

@Composable
fun BottomText(viewModel: SignUp_ViewModel, onNavigateForSignUp: () -> Unit)
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
            colors = ButtonDefaults.buttonColors(containerColor = Color_Background.value)
        ) {
            Text(
                text = "Войти",
                style = Tema.Text_24_24_Bold,
                color = Color_MainText.value
            )
        }

        val SignUpText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color =  Color_MainText.value,
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
                    color = Primary_Color,
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
//                        navigator?.replaceAll(SignUp_Screen())
                    }
            },
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}

@Composable
fun Email(MainText: String, InsideText: String) {
    var text by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    Column(
    ) {
        Text(
            text = "$MainText",
            style = Tema.PassiveSideText_14_16_500
        )
        SignUp_LogIn_OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(text = "$InsideText", style = Tema.SecondaryText_14_16_500) }
        )
    }
}

@Composable
fun Password(MainText: String, InsideText: String) {
    var text by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(top = 24.dp)
    ) {
        Text(text = MainText, style = Tema.SecondaryText_14_16_500, maxLines = 1)
        SignUp_LogIn_OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    text = InsideText,
                    style = Tema.PassiveSideText_14_16_500
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
                            passwordVisible = !passwordVisible
                        },
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )
    }
}