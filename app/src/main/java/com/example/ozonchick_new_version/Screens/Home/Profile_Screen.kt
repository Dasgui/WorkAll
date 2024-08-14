package com.example.ozonchick_new_version.Screens.Home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Card
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Scaffold
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Background
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Data_Layer.network.supabase
import com.example.ozonchick_new_version.Presentation_Layer.Models.Home_ViewModel
import com.example.ozonchick_new_version.Presentation_Layer.Models.SignUp_ViewModel
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.Onboarding.Onboarding_Screen_1
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.Send_a_Package_Provider
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


private var mainTextStyle: TextStyle = Tema.Text_24_24_Bold
private var secondTextStyle: TextStyle = Tema.Text_18_18_Medium

private var MenuTextColor: MutableState<Color> = Color_MainText

private var background: MutableState<Color> = Color_Background
private var mainTextColor: MutableState<Color> = Color_MainText

private var bottomTextStyle: androidx.compose.ui.text.TextStyle = Tema.Text_14_14_Regular
private var bottomTextColor: MutableState<Color> = Color_MainText

class Profile_Screen() : Screen {
    @Composable
    override fun Content() {
        Profile_Construct()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile_Construct(vm: Home_ViewModel = koinViewModel()) {
    val screen = rememberScreen(provider = Send_a_Package_Provider.Send_a_Package)
    var navigator = LocalNavigator.current
    Public_Scaffold(mainText = "Профиль", bottomButton = true) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 90.dp)
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.good_tick),
                    contentDescription = "К",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Log.d("Имя 2", vm.nameUser.value)
                        Text(
                            text = vm.Name_for_base(),
                            style = mainTextStyle/*vm.Name_for_base()*/,
                            color = mainTextColor.value
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_round_log_out),
                            contentDescription = "Выход",
                            modifier = Modifier
                                .size(50.dp)
                                .clickable { vm.Exit(navigator) },
                            colorFilter = ColorFilter.tint(Color_MainText.value)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),

            ) {
                Card_construct(
                    "Настройки",
                    "Здесь вы можете настроить приложение",
                    R.drawable.setting_icon,
                    navigator
                )
                Card_construct(
                    "Информация о вас",
                    "Здесь вы можете изменить информация",
                    R.drawable.setting_icon,
                    navigator
                )
            }
        }
    }


}

@Composable
private fun Card_construct(
    mainText: String,
    secondText: String,
    icon: Int,
    localNavigator: Navigator?
) {
    Public_Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(60.dp)
            .clickable { localNavigator?.push(Settings_Screen()) },
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "Настройки",
                    tint = mainTextColor.value
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.padding(start = 5.dp)
                ) {
                    Text(
                        text = mainText,
                        style = mainTextStyle,
                        color = mainTextColor.value,
                    )
                    Text(
                        text = secondText,
                        style = secondTextStyle,
                        color = mainTextColor.value,
                    )
                }
            }
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.vector),
                contentDescription = "",
                tint = mainTextColor.value
            )
        }
    }
}

