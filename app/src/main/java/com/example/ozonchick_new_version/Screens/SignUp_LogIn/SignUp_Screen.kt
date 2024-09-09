package com.example.ozonchick_new_version.Screens.SignUp_LogIn

import android.util.Log
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.db.accessSharedRegistrait
import com.example.ozonchick_new_version.Data_Layer.data.db.emailSharedUser
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.SignUp_LogIn_Components.SignUp_LogIn_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.SignUp_LogIn_Components.SignUp_LogIn_OutlinedTextField
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Background
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Gray_Color_Passive_Secondary
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Primary_Color
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Red
import com.example.ozonchick_new_version.Data_Layer.data.variables.firstType_OfRegistration
import com.example.ozonchick_new_version.Data_Layer.data.variables.secondType_OfRegistration
import com.example.ozonchick_new_version.Data_Layer.repository.SignUp_LogIn_Case.getName
import com.example.ozonchick_new_version.Presentation_Layer.Models.SignUp_ViewModel
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.SignUp_LogIn_Provider
import org.koin.androidx.compose.koinViewModel
import com.example.ozonchick_new_version.Data_Layer.data.dto.userType
private var mainTextStyle: TextStyle = Tema.Text_24_24_Bold
private var secondTextStyle: TextStyle = Tema.Text_16_16_Medium
class SignUp_Screen : Screen {
    @Composable
    override fun Content() {
        Create_Account()
    }
}

@Composable
fun SignUp_Construct() {

}

@Preview
@Composable
fun Create_Account(vm: SignUp_ViewModel = koinViewModel()) {
    val screen = rememberScreen(provider = SignUp_LogIn_Provider.LogIn)
    val context = LocalContext.current
    val navigator = LocalNavigator.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var passwordVisible1 by rememberSaveable { mutableStateOf(false) }
    if (accessSharedRegistrait.value) {
        emailSharedUser.value = vm.emailUser.collectAsState().value
        navigator?.replaceAll(screen)
    }
//    val accessRegistrait = vm.accessRegistrait.collectAsState()
    /*    var text by remember { mutableStateOf("") }
        val checkedState = remember { mutableStateOf(false) }*/
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color_Background.value),)
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 23.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.padding(top = 33.dp, bottom = 9.dp)
                ) {
                    Text(
                        text = "Создать аккаунт",
                        style = mainTextStyle,
                        color = Color_MainText.value
                    )
                    Text(
                        text = "Завершите процесс регистрации, чтобы начать",
                        color = Color_MainText.value,
                        modifier = Modifier.padding(top = 8.dp),
                        style = secondTextStyle
                    )
                }
                Column(modifier = Modifier.padding(top = 24.dp)) {
                    Text(text = "Имя", style = mainTextStyle, maxLines = 1, color = Color_MainText.value)
                    SignUp_LogIn_OutlinedTextField(
                        value = vm.nameUser.collectAsState().value,
                        onValueChange = { vm.setNameUserFieldValue(it) },
                        placeholder = {
                            Text(
                                text = "Василий"/*getUser.name*/,
                                style = secondTextStyle
                            )
                        },
                    )
                }
                Column(modifier = Modifier.padding(top = 24.dp)) {
                    Text(text = "Номер телефона", style = mainTextStyle, maxLines = 1, color = Color_MainText.value)
                    SignUp_LogIn_OutlinedTextField(
                        value = vm.phoneNumber.collectAsState().value,
                        onValueChange = { vm.setPhoneNumberFieldValue (it) },
                        placeholder = {
                            Text(
                                text = "+7*****",
                                style = secondTextStyle
                            )
                        },
                    )
                }
                Column(modifier = Modifier.padding(top = 24.dp)) {
                    Text(text = "Адрес почты", style = mainTextStyle, maxLines = 1, color = Color_MainText.value)
                    SignUp_LogIn_OutlinedTextField(
                        value = vm.emailUser.collectAsState().value,
                        onValueChange = { vm.setEmailUserFieldValue (it) },
                        placeholder = {
                            Text(
                                text = "vasili@mail.ru",
                                style = secondTextStyle
                            )
                        },
                        colors = if (vm.ProverkaMail()) {
                            Log.d("Проверка", "true")
                            TextFieldDefaults.colors(
                                focusedContainerColor = Color_Background.value,
                                unfocusedContainerColor = Color_Background.value,
                                focusedIndicatorColor = Color_MainText.value,
                                unfocusedIndicatorColor = Color_MainText.value,
                                focusedTextColor = Color_MainText.value,
                                unfocusedTextColor = Color_MainText.value
                            )
                        } else {
                            TextFieldDefaults.colors(
                                focusedContainerColor = Color_Background.value,
                                unfocusedContainerColor = Color_Background.value,
                                focusedIndicatorColor = Color_Red,
                                unfocusedIndicatorColor = Color_Red,
                                focusedTextColor = Color_Red,
                                unfocusedTextColor = Color_Red,
                            )
                        }
                    )
                }


                Column(modifier = Modifier.padding(top = 24.dp)) {
                    Text(text = "Пароль", style = mainTextStyle, maxLines = 1, color = Color_MainText.value)
                    SignUp_LogIn_OutlinedTextField(
                        value = vm.passwordUser.collectAsState().value,
                        onValueChange = { vm.setPasswordUserFieldValue (it) },
                        placeholder = {
                            Text(
                                text = "******",
                                style = secondTextStyle
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
                                colorFilter = ColorFilter.tint(Color_MainText.value)
                            )
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    )
                }
                Column(modifier = Modifier.padding(top = 24.dp)) {
                    Text(text = "Подтвердите пароль", style = mainTextStyle, maxLines = 1, color = Color_MainText.value)
                    SignUp_LogIn_OutlinedTextField(
                        value = vm.confirmPassword.collectAsState().value,
                        onValueChange = { vm.setConfirmPasswordFieldValue (it) },
                        placeholder = {
                            Text(
                                text = "*******",
                                style = secondTextStyle
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
                                        passwordVisible1 = !passwordVisible1
                                    },
                                colorFilter = ColorFilter.tint(Color_MainText.value)
                            )
                        },
                        visualTransformation = if (passwordVisible1) VisualTransformation.None else PasswordVisualTransformation(),
                    )
                }
                if (vm.userRole.collectAsState().value == userType.nesoiskat)
                {
                    Column(modifier = Modifier.padding(top = 24.dp)) {
                        Text(text = "Подтвердите ИНН", style = mainTextStyle, maxLines = 1, color = Color_MainText.value)
                        SignUp_LogIn_OutlinedTextField(value = vm.INN.collectAsState().value, onValueChange = {vm.setInnFieldValue (it)})
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    Text(text = firstType_OfRegistration, style = mainTextStyle, color = Color_MainText.value, modifier = Modifier.clickable { vm.setUserRoleFieldValue(userType.soiskat)})
                    Text(text = secondType_OfRegistration, style = mainTextStyle, color = Color_MainText.value, modifier = Modifier.clickable { vm.setUserRoleFieldValue (userType.nesoiskat)})
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(top = 30.dp, bottom = 30.dp)
                ) {
                    SignUp_LogIn_Button(
//                        enabled = vm.Proverka(),
                        onClick = {
                            vm.Registrait(context)

                            /*                        vm.ClearParametres()*/
                        },
                        colors = if (vm.Proverka()) {
                            ButtonDefaults.buttonColors(containerColor = Color_MainText.value)
                        } else {
                            ButtonDefaults.buttonColors(containerColor = Gray_Color_Passive_Secondary)
                        }
                    ) {
                        Text(
                            text = "Зарегистрироваться",
                            style = mainTextStyle,
                            color = if (vm.Proverka()){
                                Color_Background.value
                            }
                            else
                            {
                                Color_MainText.value
                            }
                        )
                    }
                    val SignInText = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color_MainText.value,
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
                        text = SignInText,
                        onClick = {
                            SignInText.getStringAnnotations(tag = "click", start = it, end = it).firstOrNull()
                                ?.let {
                                    navigator?.replaceAll(LogIn_Screen())
                                }
                        },
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
            }
        }
    }
}


/*@Composable
fun Create_Account_Min_Elem(Maint_text: String, Inside_Text: String, vm: SignUp_ViewModel) {
    val getUser = getName()
    var text by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(text = "$Maint_text", style = Tema.SecondaryText_14_16_500, maxLines = 1)
        SignUp_LogIn_OutlinedTextField(
            value = vm.nameUser.value,
            onValueChange = { vm.nameUser.value = it },
            placeholder = { Text(text = getUser.user_name, style = Tema.PassiveSideText_14_16_500) },
        )
    }
}

@Composable
fun Create_Account_Min_Elem_Password(Maint_text: String, Inside_Text: String) {
    var text by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(text = "$Maint_text", style = Tema.SecondaryText_14_16_500, maxLines = 1)
        SignUp_LogIn_OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(text = Inside_Text, style = Tema.PassiveSideText_14_16_500) },
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
}*/
