package com.example.ozonchick_new_version.Presentation.Screens

import android.view.Gravity
import android.widget.Toast
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Presentation.ViewModels.VakansCreate_ViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.SignUp_LogIn_Components.SignUp_LogIn_Button
import com.example.ozonchick_new_version.Presentation.ui.components.SignUp_LogIn_Components.SignUp_LogIn_OutlinedTextField
import com.example.ozonchick_new_version.Presentation.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.mainTextStyle
import com.example.ozonchick_new_version.Presentation.ui.theme.middleTextStyle
import com.example.ozonchick_new_version.Presentation.ui.theme.secondTextStyle
import org.koin.androidx.compose.koinViewModel

private var paddingBetween_Text = 5.dp
private var paddingBetween_Lines = 10.dp
private var paddingBetween_MainTitles = 15.dp
private var textField_Color_Error = Color.Red
private var textField_Number_Color = MainColor
private var textField_Mail_Color = MainColor

class ChangeInformation_Screen : Screen {
    @Preview
    @Composable
    override fun Content() {
        Ozonchick_new_versionTheme {
            ChangeInformation_construct()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeInformation_construct(vm: VakansCreate_ViewModel = koinViewModel()) {
    val navigator = LocalNavigator.current
    CustomScaffold(
        bottomButton = false,
        scaffoldStates = ScaffoldStates.OnSide,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_square_right),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    navigator?.pop()
                                },
                            tint = MainColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor),
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        )
        {
            item {
                ChangeInformation_part_main()
                ChangeInformation_part_main_contacts()
                ChangeInformation_part_optinalData()
                ChangeInformation_part_confiramtion()
            }
        }
    }
}

@Composable
fun ChangeInformation_part_main(vm: VakansCreate_ViewModel = koinViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingBetween_Lines)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingBetween_Lines),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingBetween_Text),
                text = "Возраст",
                style = middleTextStyle.value,
                color = MaterialTheme.colorScheme.primary
            )
            SignUp_LogIn_OutlinedTextField(
                value = vm.specialization.value,
                onValueChange = { vm.specialization.value = it },
                placeholder = {
                    Text(
                        text = "",
                        style = secondTextStyle.value,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingBetween_Lines),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingBetween_Text),
                text = "Место проживания",
                style = middleTextStyle.value,
                color = MaterialTheme.colorScheme.primary
            )
            SignUp_LogIn_OutlinedTextField(
                value = vm.wage.value,
                onValueChange = { vm.wage.value = it },
                placeholder = {
                    Text(
                        text = "",
                        style = secondTextStyle.value,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingBetween_Lines),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingBetween_Text),
                text = "Ограничения по здоровью",
                style = middleTextStyle.value,
                color = MaterialTheme.colorScheme.primary
            )
            SignUp_LogIn_OutlinedTextField(
                value = vm.wage.value,
                onValueChange = { vm.wage.value = it },
                placeholder = {
                    Text(
                        text = "",
                        style = secondTextStyle.value,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    }
}

@Composable
fun ChangeInformation_part_main_contacts(vm: VakansCreate_ViewModel = koinViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = paddingBetween_MainTitles)
    ) {
        Text(
            text = "Контакты:",
            style = mainTextStyle.value,
            color = MaterialTheme.colorScheme.primary
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, top = 10.dp, bottom = 15.dp)
                .drawBehind {
                    drawLine(
                        MainColor,
                        Offset(-10f, size.height - 15f),
                        Offset(-10f, 45f),
                        20f
                    )
                },
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingBetween_Text),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingBetween_Text),
                    text = "Номер телефона",
                    style = middleTextStyle.value,
                    color = MaterialTheme.colorScheme.primary
                )
                SignUp_LogIn_OutlinedTextField(
                    value = vm.phoneNumber.value,
                    onValueChange = {
                        vm.phoneNumber.value = it
                        textField_Number_Color = if (!(it.contains("[+]".toRegex()))) {
                            Color.Red
                        } else if (it.isEmpty()) {
                            MainColor
                        } else {
                            MainColor
                        }
                    },
                    placeholder = {
                        Text(
                            text = "",
                            style = secondTextStyle.value,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = /*textField_Color*/ MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = /*textField_Color*/ MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary/*Color_MainText.value*/,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary/*Color_MainText.value*/,
                        focusedTextColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingBetween_Text),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingBetween_Text),
                    text = "Почта",
                    style = middleTextStyle.value,
                    color = MaterialTheme.colorScheme.primary
                )
                SignUp_LogIn_OutlinedTextField(
                    value = vm.mail.value,
                    onValueChange = {
                        vm.mail.value = it
                        if (!(it.contains("@".toRegex())))
                        {
                            textField_Mail_Color = Color.Red
                        }
                        else if (it.isEmpty())
                        {
                            textField_Mail_Color = MainColor
                        }
                        else
                        {
                            textField_Mail_Color = MainColor
                        }
                    },
                    placeholder = {
                        Text(
                            text = "",
                            style = secondTextStyle.value,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = /*textField_Color*/ MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = /*textField_Color*/ MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary/*Color_MainText.value*/,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary/*Color_MainText.value*/,
                        focusedTextColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}

@Composable
fun ChangeInformation_part_optinalData(vm: VakansCreate_ViewModel = koinViewModel()) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, bottom = 35.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start,
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingBetween_Text),
                text = "О себе",
                style = middleTextStyle.value,
                color = MaterialTheme.colorScheme.primary
            )
            SignUp_LogIn_OutlinedTextField(
                value = vm.experience.value,
                onValueChange = {
                    if (it.length >= 300) {
                        val toast = Toast.makeText(
                            context,
                            "Произошла ошибка, допустимо лишь 300 символов",
                            Toast.LENGTH_LONG
                        )
                        toast.setGravity(Gravity.FILL_VERTICAL, 0, 0)
                        toast.show()
                    } else {
                        vm.experience.value = it
                    }
                },
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                singleLine = false
            )
        }
    }
}

@Composable
fun ChangeInformation_part_confiramtion() {
    SignUp_LogIn_Button(
        onClick = {},
        enabled = true,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 55.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BackgroundColor, contentColor = MainColor)
    )
    {
        Text(
            text = "Изменить данные",
            style = mainTextStyle.value,
            color = MaterialTheme.colorScheme.primary
        )
    }
}