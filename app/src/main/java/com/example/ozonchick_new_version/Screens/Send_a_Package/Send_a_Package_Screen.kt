package com.example.ozonchick_new_version.Screens.Send_a_Package

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Sand_a_Package_Components.Send_a_Package_Card
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Sand_a_Package_Components.Send_a_Package_Details
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Button_Color_Blue
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Gray_Color_Active_SideText
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Gray_Color_Passive_Secondary
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model.infoZakaz
import com.example.ozonchick_new_version.Presentation_Layer.Models.Send_a_Package_ViewModel
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.Send_a_Package_Provider
import org.koin.androidx.compose.koinViewModel

class Send_a_Package_Screen : Screen {
    @Composable
    override fun Content() {
        Send_a_Package_Construct()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Send_a_Package_Construct(vm: Send_a_Package_ViewModel = koinViewModel()) {
    Ozonchick_new_versionTheme {
        val navigator = LocalNavigator.current
        val screen = rememberScreen(provider = Send_a_Package_Provider.Send_a_Package_Receipt)
        val context = LocalContext.current
        Scaffold(
            topBar = {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(10.dp),
                    shape = RoundedCornerShape(0.dp),

                    ) {
                    TopAppBar(
                        modifier = Modifier
                            .fillMaxWidth(),
                        title = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 24.dp)
                                    .fillMaxWidth()

                            ) {
                                IconButton(
                                    onClick = {navigator?.pop()
                                        vm.CleanViewModel()},
                                    modifier = Modifier
                                        .size(24.dp),
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.arrow_square_right),
                                        contentDescription = "Меню",
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        tint = Button_Color_Blue
                                    )
                                }
                                Text(
                                    text = "Send a package",
                                    style = Tema.Text_16_16_Medium,
                                    modifier = Modifier.padding(start = 125.dp, end = 125.dp)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                    )
                }

            },

            bottomBar = {}
        ) {
            LazyColumn(
                modifier = Modifier.padding(top = 100.dp, start = 24.dp, end = 24.dp),
                horizontalAlignment = Alignment.Start
            )
            {
                items(count = 1)
                {
//                    Text(text = vm.GetId().toString())
                    vm.GetId(context)
                    /*SignUp_LogIn_Button(onClick = { vm.GetUserId() }) {}*/
                    StandartCard(vm)
                    DestinationDetails(vm)
                    PackageDetails(vm, context)
                    DeliveryType(vm, screen, navigator, context)

                }
            }
        }
    }
}

@Composable
fun DestinationDetails(vm: Send_a_Package_ViewModel) {
    if (vm.Add.value) {
        vm.ListZakaz.add(infoZakaz())
    }
    for (i in 0..vm.Colich.value + 1) {
        if (i <= vm.Colich.value - 1) {

            if (vm.ListZakaz.size != 0) {
                Log.e("Size", vm.ListZakaz.size.toString())
                шаблон2(vm.MainText_Card, "Destination Details", R.drawable.vectormap, vm, i)

            }
        } else if (i == vm.Colich.value + 1) {

            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clickable {
                        vm.Colich.value++
                        vm.Add.value = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_square),
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .size(14.dp),
                    contentDescription = "",
                    tint = Button_Color_Blue
                )
                Text(
                    text = "Add destination",
                    style = Tema.SideText_12_16_400,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

        }
    }
}

@Composable
fun HablonTablichek(
    MainText: String,
    IdImage: Int,
    Backcolor: Color,
    TintTextColor: Color,
    SideScreen: Screen,
    vm: Send_a_Package_ViewModel,
    navigator: Navigator?,
    context: Context
) {


    Send_a_Package_Card(
        modifier = Modifier
            .width(160.dp)
            .height(75.dp)
            .padding(bottom = 10.dp)
            .clickable {
                vm.Proverka(navigator, context)
                /*                Log.d("ttt", vm.ListZakaz.size.toString())
                for (i in 0 until vm.ListZakaz.size) {
                    if (vm.address.value.isNotEmpty() && vm.stateCountry.value.isNotEmpty()
                        && vm.phoneNumber.value.isNotEmpty() && vm.packageItem.value.isNotEmpty()
                        && vm.weightItem.value.isNotEmpty() && vm.worthItem.value.isNotEmpty()
                        && (vm.ListZakaz[i].Address.isNotEmpty() && vm.Colich.value == i + 1 )
                        && (vm.ListZakaz[i].State_Country.isNotEmpty() && vm.Colich.value == i + 1)
                        && (vm.ListZakaz[i].PhoneNumber.isNotEmpty() && vm.Colich.value == i + 1)
                    ) {
                        Log.d("Размер", vm.ListZakaz.size.toString())
                        vm.GetUserId()
                        vm.ChargesChet()
                        navigator?.push(SideScreen)
                    }
                }*/
            },
        colors = CardDefaults.cardColors(
            containerColor = Backcolor,
            disabledContainerColor = Backcolor
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Icon(
                painter = painterResource(id = IdImage),
                contentDescription = "F",
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .size(24.dp),
                tint = TintTextColor
            )
            Text(
                text = MainText,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    color = TintTextColor,
                    fontWeight = FontWeight(500)
                )
            )
        }
    }
}

@Composable
fun PackageDetails(vm: Send_a_Package_ViewModel, context : Context) {
    Column(
        modifier = Modifier.padding(top = 15.dp, bottom = 40.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Package Details",
                style = Tema.ActiveSideText_14_16_500,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Send_a_Package_Details(
            valueTextField = vm.packageItem.value,
            onValueChange = {
                vm.packageItem.value = it
            },
            placeholder = { Text(text = "package items", color = Gray_Color_Active_SideText) }
        )
        Send_a_Package_Details(
            valueTextField = vm.weightItem.value,
            onValueChange = {
                vm.weightItem.value = it
            },
            placeholder = { Text(text = "Weight items", color = Gray_Color_Active_SideText) }
        )
        Send_a_Package_Details(
            valueTextField = vm.worthItem.value,
            onValueChange = {
                vm.worthItem.value = it
            },
            placeholder = { Text(text = "Worth of items", color = Gray_Color_Active_SideText) }
        )
    }
}

@Composable
fun StandartCard(vm: Send_a_Package_ViewModel) {
    Column(
        modifier = Modifier.padding(bottom = 25.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(id = R.drawable.vector2),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(16.dp),
                tint = Button_Color_Blue
            )
            Text(
                text = "Origin Details",
                style = Tema.ActiveSideText_14_16_500,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Send_a_Package_Details(
            valueTextField = vm.address.value,
            onValueChange = {
                vm.address.value = it
            },
            placeholder = { Text(text = "Address", color = MaterialTheme.colorScheme.onSecondaryContainer) }
        )
        Send_a_Package_Details(
            valueTextField = vm.stateCountry.value,
            onValueChange = {
                vm.stateCountry.value = it
            },
            placeholder = { Text(text = "State,Country", color = MaterialTheme.colorScheme.onSecondaryContainer) }
        )
        Send_a_Package_Details(
            valueTextField = vm.phoneNumber.value,
            onValueChange = {
                vm.phoneNumber.value = it
            },
            placeholder = { Text(text = "Phone number", color = MaterialTheme.colorScheme.onSecondaryContainer) }
        )
        Send_a_Package_Details(
            valueTextField = vm.others.value,
            onValueChange = {
                vm.others.value = it
            },
            placeholder = { Text(text = "Others", color = MaterialTheme.colorScheme.onSecondaryContainer) }
        )
    }
}

@Composable
fun шаблон2(
    list: ArrayList<String>,
    MText: String,
    Image: Int,
    vmShablon: Send_a_Package_ViewModel,
    nomer: Int
) {

    var obgect by remember {
        mutableStateOf(vmShablon.ListZakaz[nomer])
    }
    var adress by remember {
        mutableStateOf(vmShablon.ListZakaz[nomer].Address)
    }
    var State_Country by remember {
        mutableStateOf(vmShablon.ListZakaz[nomer].State_Country)
    }
    var PhoneNumber by remember {
        mutableStateOf(vmShablon.ListZakaz[nomer].PhoneNumber)
    }
    var Others by remember {
        mutableStateOf(vmShablon.ListZakaz[nomer].Others)
    }





    Column(
        modifier = Modifier.padding(top = 25.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(id = Image), contentDescription = "", modifier = Modifier
                    .padding(end = 10.dp)
                    .size(16.dp),
                tint = Button_Color_Blue
            )
            Text(
                text = MText,
                style = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                ),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Send_a_Package_Details(
            valueTextField = adress,
            onValueChange = {
                adress = it
                vmShablon.ListZakaz[nomer].Address = it
            },
            placeholder = { Text(text = list[0], color = Gray_Color_Passive_Secondary) }
        )
        Send_a_Package_Details(
            valueTextField = State_Country,
            onValueChange = {
                State_Country = it
                vmShablon.ListZakaz[nomer].State_Country = it
            },
            placeholder = { Text(text = list[1], color = Gray_Color_Passive_Secondary) }
        )
        Send_a_Package_Details(
            valueTextField = PhoneNumber,
            onValueChange = {
                PhoneNumber = it
                vmShablon.ListZakaz[nomer].PhoneNumber = it
            },
            placeholder = { Text(text = list[2], color = Gray_Color_Passive_Secondary) }
        )
        Send_a_Package_Details(
            valueTextField = Others,
            onValueChange = {
                Others = it
                vmShablon.ListZakaz[nomer].Others = it
            },
            placeholder = { Text(text = list[3], color = Gray_Color_Passive_Secondary) }
        )


//        Card(
//            modifier = Modifier
//                .padding(top = 10.dp)
//                .fillMaxWidth(),
//            shape = RectangleShape,
//            elevation = CardDefaults.cardElevation(20.dp)
//            /*elevation = 20.dp*/
//        )
//        {
//            NIaTextField(
//                value = adress,
//                onValueChange = {
//                    adress = it
//                    vmShablon.ListZakaz[nomer].Address = it
//                },
//                placeholder = { Text(text = list[0], color = Gray_Color_Passive_Secondary) },
//                modifier = Modifier
//                    .padding()
//                    .fillMaxWidth(),
//                singleLine = true,
///*                colors = TextFieldDefaults.textFieldColors(
//                    focusedIndicatorColor = Gray_Color_Passive_Secondary,
//                    focusedPlaceholderColor = Color.White
//                ),*/
//            )
//        }
//        Card(
//            modifier = Modifier
//                .padding(top = 10.dp)
//                .fillMaxWidth(),
//            shape = RectangleShape,
//            elevation = CardDefaults.cardElevation(20.dp)
//            /*elevation = 20.dp*/
//        )
//        {
//            TextField(
//                value = State_Country,
//                onValueChange = {
//                    State_Country = it
//                    vmShablon.ListZakaz[nomer].State_Country = it
//                },
//                placeholder = { Text(text = list[1], color = Gray_Color_Passive_Secondary) },
//                modifier = Modifier
//                    .padding()
//                    .fillMaxWidth(),
//                singleLine = true,
//                textStyle = TextStyle(
//                    fontSize = 12.sp,
//                    lineHeight = 16.sp,
//                    color = Text_Color_Passive,
//                    textAlign = TextAlign.Justify,
//                ),
//            )
//        }
//
//        Card(
//            modifier = Modifier
//                .padding(top = 10.dp)
//                .fillMaxWidth(), shape = RectangleShape, /*elevation = 20.dp*/
//        )
//        {
//            TextField(
//                value = PhoneNumber,
//                onValueChange = {
//                    PhoneNumber = it
//                    vmShablon.ListZakaz[nomer].PhoneNumber = it
//                },
//                placeholder = { Text(text = list[2], color = Gray_Color_Passive_Secondary) },
//                modifier = Modifier
//                    .padding()
//                    .fillMaxWidth(),
//                singleLine = true,
//                textStyle = TextStyle(
//                    fontSize = 12.sp,
//                    lineHeight = 16.sp,
//                    color = Text_Color_Passive,
//                    textAlign = TextAlign.Justify,
//                ),
///*                colors = TextFieldDefaults.textFieldColors(
//                    focusedIndicatorColor = Gray_Color_Passive,
//                    backgroundColor = White_Color
//                ),*/
//            )
//        }
//
//        Card(
//            modifier = Modifier
//                .padding(top = 10.dp)
//                .fillMaxWidth(), shape = RectangleShape, /*elevation = 20.dp*/
//        )
//        {
//            TextField(
//                value = Others,
//                onValueChange = {
//                    Others = it
//                    vmShablon.ListZakaz[nomer].Others = it
//                },
//                placeholder = { Text(text = list[3], color = Gray_Color_Passive_Secondary) },
//                modifier = Modifier
//                    .padding()
//                    .fillMaxWidth(),
//                singleLine = true,
//                textStyle = TextStyle(
//                    fontSize = 12.sp,
//                    lineHeight = 16.sp,
//                    color = Text_Color_Passive,
//                    textAlign = TextAlign.Justify,
//                ),
///*                colors = TextFieldDefaults.textFieldColors(
//                    focusedIndicatorColor = Gray_Color_Passive,
//                    backgroundColor = White_Color
//                ),*/
//            )
//        }

    }
}

@Composable
fun DeliveryType(vm: Send_a_Package_ViewModel, screen: Screen, navigator: Navigator?, context: Context) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Select delivery type",
            modifier = Modifier.padding(bottom = 15.dp),
            color = MaterialTheme.colorScheme.onPrimary)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            HablonTablichek(
                "Instant delivery",
                R.drawable.clock,
                MaterialTheme.colorScheme.tertiaryContainer,
                Color.White,
                screen,
                vm,
                navigator,
                context
            )
            HablonTablichek(
                "Scheduled delivery",
                R.drawable.calendar,
                MaterialTheme.colorScheme.tertiaryContainer,
                Color.White,
                screen,
                vm,
                navigator,
                context
            )
        }
    }
}
