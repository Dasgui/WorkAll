package com.example.ozonchick_new_version.Screens.Home.Employer.Vacancies

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Card
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Scaffold
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.middleTextStyle
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_contacts
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class ListOfVacancies_Screen () :Screen
{
    @Composable
    @Preview
    override fun Content() {
        ListOfVacancies_construct()
    }
}


@Composable
fun ListOfVacancies_construct(vm: ListOfVacancies_ViewModel = koinViewModel())
{
    Public_Scaffold(
        bottomButton = true,
        mainText = "Ваши вакансии"
    ) {
        var isLoading: MutableState<Boolean> = remember {
            mutableStateOf(false)
        }
        LaunchedEffect(key1 = true) {
            CoroutineScope(Dispatchers.IO).launch {
                vm.GetInformation()
                delay(1000)
                isLoading.value = true
            }

        }
        if (isLoading.value)
        {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 70.dp, bottom = 80.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(vm.countOfVakans) { item ->
                    Card_Vakans(item, vm.spisokVakans)
                }
                item {
                    Image(
                        modifier = Modifier
                            .padding(bottom = 150.dp)
                            .size(50.dp),
                        painter = painterResource(id = R.drawable.addicon),
                        contentDescription = "",
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}

@Composable
fun Card_Vakans(number: Int, list: MutableList<Vakans_information>)
{
    var localNumber: MutableState<Int> = remember {
        mutableIntStateOf(number)
    }
    val context = LocalContext.current
    var contactList: MutableList<Vakans_contacts> = mutableListOf()
    val navigator = LocalNavigator.current
    Public_Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(bottom = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 5.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    //Требуется специалист Name
                    Text(
                        text = list[number].vakans_name/*"Инженер"*/,
                        style = middleTextStyle.value,
                        color = Color_MainText.value
                    )
                    //Заработная плата Price
                    Text(
                        text = list[number].vakans_price/*"90 000 - 130 000"*/,
                        style = middleTextStyle.value,
                        color = Color_MainText.value
                    )
                    //Организация Organization
                    Text(
                        text = list[number].vakans_organization/*"ООО Некронтиры"*/,
                        style = middleTextStyle.value,
                        color = Color_MainText.value
                    )
                    //Расположение Location
                    Text(
                        text = list[number].vakans_location/*"Челябинск"*/,
                        style = middleTextStyle.value,
                        color = Color_MainText.value
                    )
                    //Опыт работы Experience
                    Text(
                        text = list[number].vakans_experience/*"Опыт работы 3 до 6 лет"*/,
                        style = middleTextStyle.value,
                        color = Color_MainText.value
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Public_Button(
                        fillButton = false,
                        onClick = {  },
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(
                            text = "Изменить",
                            style = middleTextStyle.value,
                        )
                    }
                    Public_Button(
                        fillButton = false,
                        onClick = {

                        },
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(
                            text = "Соискатели",
                            style = middleTextStyle.value,
                        )
                    }
                    Public_Button(
                        fillButton = true,
                        onClick = {

                        },
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(
                            text = "Удалить",
                            style = middleTextStyle.value,
                        )
                    }
                }
            }
        }


    }
}