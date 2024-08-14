package com.example.ozonchick_new_version.Screens.Send_a_Package

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Button_Color_Blue
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Gray_Color_Passive_Secondary
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Primary_Color
import com.example.ozonchick_new_version.Presentation_Layer.Models.Send_a_Package_ViewModel
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.Send_a_Package_Provider
import org.koin.androidx.compose.koinViewModel

class Send_a_Package_Receipt_Screen : Screen {
    @Composable
    override fun Content() {
        Send_a_Package_Receipt_Constructor()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Send_a_Package_Receipt_Constructor(vm: Send_a_Package_ViewModel = koinViewModel()) {
    Ozonchick_new_versionTheme() {
        val navigator = LocalNavigator.current
        Scaffold(
            topBar = {
                Card(
                    elevation = CardDefaults.cardElevation(10.dp),
                    shape = RoundedCornerShape(0.dp),
                ) {
                    TopAppBar(
                        title = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 24.dp)
                                    .fillMaxWidth()

                            ) {
                                IconButton(
                                    onClick = {
                                        vm.Add.value = false
                                        navigator?.pop()
                                    },
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
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                    )
                }
            }
        )
        {
            Column(
                modifier = Modifier
                    .padding(top = 132.dp, end = 24.dp, start = 24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Package Information", style = Tema.MainTextBlue_16)
                OriginDetails(vm)
                Destination_Details_Construct(vm)
                Other_Details(vm)
                Charges(vm)
                Bottom(vm,navigator)
            }
        }
    }
}

@Composable
fun OriginDetails(vm: Send_a_Package_ViewModel) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Origin details", style = Tema.BoldSideText_12_16)
        Text(
            text = "${vm.address.value}, ${vm.stateCountry.value}",
            style = Tema.SideText_12_16_400,
            modifier = Modifier.padding(top = 5.dp)
        )
        Text(
            text = vm.phoneNumber.value,
            style = Tema.SideText_12_16_400,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Composable
fun Destination_Details_Construct(vmSend_a_package_ViewModel: Send_a_Package_ViewModel) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Destination details", style = Tema.BoldSideText_12_16)
        for (i in 0 until vmSend_a_package_ViewModel.ListZakaz.size - 1) {
            DestionInformation(vmSend_a_package_ViewModel = vmSend_a_package_ViewModel, number = i)
        }
    }
}

@Composable
fun Bottom(vm: Send_a_Package_ViewModel, navigator: Navigator?) {
    val screen = rememberScreen(provider = Send_a_Package_Provider.Transaction)
    val ScreenBack = rememberScreen(provider = Send_a_Package_Provider.Send_a_Package)
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HablonButton("Edit package", Color.Transparent, Button_Color_Blue, ScreenBack, vm,navigator)
        HablonButton("Make payment", MaterialTheme.colorScheme.onTertiaryContainer, Color.White, screen, vm, navigator)
    }
}

@Composable
fun HablonButton(
    MainText: String,
    Backcolor: Color,
    TintTextColor: Color,
    SideScreen: Screen,
    vm: Send_a_Package_ViewModel,
    navigator : Navigator?
) {

    Card(
        modifier = Modifier
            .width(160.dp)
            .height(50.dp)
            .clickable {
                vm.InsertIformation()
                navigator?.replaceAll(SideScreen)
            },
        colors = CardDefaults.cardColors(containerColor = Backcolor),
        border = BorderStroke(color = Primary_Color, width = 1.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = MainText,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    color = TintTextColor,
                    fontWeight = FontWeight(500)
                ),
//                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@Composable
fun DestionInformation(vmSend_a_package_ViewModel: Send_a_Package_ViewModel, number: Int) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(
            text = "${number + 1}. ${vmSend_a_package_ViewModel.ListZakaz[number].Address}, ${vmSend_a_package_ViewModel.ListZakaz[number].State_Country}",
            style = Tema.SideText_12_16_400
        )
        Text(
            text = vmSend_a_package_ViewModel.ListZakaz[number].PhoneNumber,
            style = Tema.SideText_12_16_400
        )
    }
}


@Composable
fun Other_Details(vm: Send_a_Package_ViewModel) {
    Text(
        text = "Other details",
        style = Tema.BoldSideText_12_16,
        modifier = Modifier.padding(top = 5.dp)
    )
    Column(
        modifier = Modifier
            /*.padding(top = 5.dp)*/
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Package Items", style = Tema.SideText_12_16_400)
            Text(
                text = vm.packageItem.value,
                style = Tema.OrangeSideText_12_16
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Weight of items", style = Tema.SideText_12_16_400)
            Text(
                text = vm.weightItem.value,
                style = Tema.OrangeSideText_12_16
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Worth of Items", style = Tema.SideText_12_16_400)
            Text(
                text = vm.worthItem.value,
                style = Tema.OrangeSideText_12_16
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Tracking Number", style = Tema.SideText_12_16_400)
            Text(text = "R-890-89-21-34-445", style = Tema.OrangeSideText_12_16)
        }
    }
}


@Composable
fun Charges(vm: Send_a_Package_ViewModel) {
    Canvas(
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth()
    ) {
        drawLine(
            start = Offset(x = size.width, y = 0f),
            end = Offset(x = 0f, y = size.height),
            color = Gray_Color_Passive_Secondary,
            strokeWidth = 5f
        )
    }
    Column(
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Charges", style = Tema.MainTextBlue_16)

    }
    for (i in 0 until 3) {
        Charges_Text(mainText = vm.chargesName[i], chislo = vm.chargesZnach[i])
    }
    Canvas(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
    ) {
        drawLine(
            start = Offset(x = size.width, y = 0f),
            end = Offset(x = 0f, y = size.height),
            color = Gray_Color_Passive_Secondary,
            strokeWidth = 5f
        )
    }
    Row(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 40.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Package total", style = Tema.SideText_12_16_400)
        Text(text = vm.sumDelivary.toString(), style = Tema.OrangeSideText_12_16)
    }
}

@Composable
fun Charges_Text(mainText: String, chislo: Double) {
    Row(
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = mainText, style = Tema.SideText_12_16_400)
        Text(text = "${chislo}", style = Tema.OrangeSideText_12_16)
    }
}


@Composable
fun Package_Information_Construct(vm: Send_a_Package_ViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Package Information", style = Tema.MainTextBlue_16)
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Origin details", style = Tema.BoldSideText_12_16)
            Text(
                text = "${vm.address.value}, ${vm.stateCountry.value}",
                style = Tema.SideText_12_16_400,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = vm.phoneNumber.value,
                style = Tema.SideText_12_16_400,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
        Destination_Details_Construct(vm)
        Text(
            text = "Other details",
            /*style = Tem.BoldSideText_12_16,*/
            modifier = Modifier.padding(top = 5.dp)
        )
        Other_Details(vm)
        Canvas(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
        ) {
            drawLine(
                start = Offset(x = size.width, y = 0f),
                end = Offset(x = 0f, y = size.height),
                color = Gray_Color_Passive_Secondary,
                strokeWidth = 5f
            )
        }
        Charges(vm)
    }
}