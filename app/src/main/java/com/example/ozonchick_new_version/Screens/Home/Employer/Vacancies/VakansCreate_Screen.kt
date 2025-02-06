package com.example.ozonchick_new_version.Screens.Home.Employer.Vacancies

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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Scaffold
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.SignUp_LogIn_Components.SignUp_LogIn_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.SignUp_LogIn_Components.SignUp_LogIn_OutlinedTextField
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.backgroundColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.mainColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.mainTextStyle
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.middleTextStyle
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.secondTextStyle
import com.example.ozonchick_new_version.Screens.Home.Candidate.VakansCreate_ViewModel
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.koinViewModel

private var paddingBetween_Text = 5.dp
private var paddingBetween_Lines = 10.dp
private var paddingBetween_MainTitles = 15.dp

class VakansCreate_Screen: Screen {
    @Composable
    @Preview
    override fun Content() {
        VakansCreate_construct()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VakansCreate_construct(vm: VakansCreate_ViewModel = koinViewModel())
{
    val navigator = LocalNavigator.current
    Public_Scaffold(
        bottomButton = false,
        backButton = true,
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
                            tint = mainColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor),
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
                VakansCreate_part_main()
                VakansCreate_part_main_contacts()
                VakansCreate_part_optinalData()
                VakansCreate_part_confiramtion()
            }
        }
    }

}


@Composable
fun VakansCreate_part_main(vm: VakansCreate_ViewModel = koinViewModel())
{
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingBetween_Lines)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingBetween_MainTitles),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom =  paddingBetween_Text),
                text = "Требуемая специальность*",
                style = middleTextStyle.value,
                color = mainColor
            )
            SignUp_LogIn_OutlinedTextField(
                value = vm.specialization.value,
                onValueChange = { vm.specialization.value = it },
                placeholder = { Text(text = "Менеджер", style = secondTextStyle.value, color = mainColor)}
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding( top = paddingBetween_MainTitles),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom =  paddingBetween_Text),
                text = "Заработная плата*",
                style = middleTextStyle.value,
                color = mainColor
            )
            SignUp_LogIn_OutlinedTextField(
                value = vm.wage.value,
                onValueChange = { vm.wage.value = it },
                placeholder = { Text(text = "100000", style = secondTextStyle.value, color = mainColor)}
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingBetween_MainTitles),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = paddingBetween_Text),
                text = "О работе*",
                style = middleTextStyle.value,
                color = mainColor
            )
            SignUp_LogIn_OutlinedTextField(
                value = vm.experience.value,
                onValueChange = {
                    if (it.length < 500)
                    {
                        vm.experience.value = it
                    }
                    else if (it.length >= 500)
                    {
                        val toast = Toast.makeText(context,"Произошла ошибка, допустимо лишь 500 символов", Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.FILL_VERTICAL,0,0)
                        toast.show()
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
fun  VakansCreate_part_main_contacts(vm: VakansCreate_ViewModel = koinViewModel())
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = paddingBetween_MainTitles)
    ) {
        Text(
            text = "Контакты*:",
            style = mainTextStyle.value,
            color = mainColor
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, top = 10.dp, bottom = 15.dp)
                .drawBehind {
                    drawLine(
                        mainColor,
                        Offset(-10f, size.height-15f),
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
                    text = "Номер телефона*",
                    style = middleTextStyle.value,
                    color = mainColor
                )
                SignUp_LogIn_OutlinedTextField(
                    value = vm.phoneNumber.value,
                    onValueChange = { vm.phoneNumber.value = it },
                    placeholder = { Text(text = "+79923845944", style = secondTextStyle.value, color = mainColor)}
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
                    text = "Почта*",
                    style = middleTextStyle.value,
                    color = mainColor
                )
                SignUp_LogIn_OutlinedTextField(
                    value = vm.mail.value,
                    onValueChange = { vm.mail.value = it },
                    placeholder = { Text(text = "cilo@mail.ru", style = secondTextStyle.value, color = mainColor)}
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
                    text = "Расположение места работы*",
                    style = middleTextStyle.value,
                    color = mainColor,
                )
                SignUp_LogIn_OutlinedTextField(
                    value = vm.locationOfWork.value,
                    onValueChange = { vm.locationOfWork.value = it },
                    placeholder = { Text(text = "Ленинград", style = secondTextStyle.value, color = mainColor)}
                )
            }
        }
    }
}

@Composable
fun VakansCreate_part_optinalData(vm: VakansCreate_ViewModel = koinViewModel())
{
    Text(
        text = "Не обязательные данные:",
        color = mainColor,
        style = mainTextStyle.value
    )
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
                .fillMaxSize()
                .padding(bottom = paddingBetween_Lines),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingBetween_Text),
                text = "Требуемый опыт работы/навыки",
                style = middleTextStyle.value,
                color = mainColor
            )
            SignUp_LogIn_OutlinedTextField(
                value = vm.experience.value,
                onValueChange = { vm.experience.value = it },
                placeholder = { Text(text = ""/*"Не требуется"*/, style = secondTextStyle.value, color = mainColor)}
            )
        }

    }
}

@Composable
fun VakansCreate_part_confiramtion()
{
    SignUp_LogIn_Button(
        onClick = {},
        enabled = true,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 55.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor, contentColor = mainColor)
    )
    {
        Text(
            text = "Создать вакансию",
            style = mainTextStyle.value,
            color = mainColor
        )
    }
}