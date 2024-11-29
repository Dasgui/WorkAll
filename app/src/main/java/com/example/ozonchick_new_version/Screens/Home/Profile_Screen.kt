package com.example.ozonchick_new_version.Screens.Home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Card
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Scaffold
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.koinViewModel




class Profile_Screen() : Screen {
    @Composable
    override fun Content() {
        var isLoading: MutableState<Boolean> = remember {
            mutableStateOf(false)
        }
        val viewModel: HomeViewModel = koinViewModel()
        Ozonchick_new_versionTheme {
            LaunchedEffect(true) {
                viewModel.getName()
                isLoading.value = true
            }
            Profile_Construct(isLoading.value)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile_Construct(isLoading:Boolean) {
    var navigator = LocalNavigator.current
    if (isLoading) {
        Public_Scaffold(mainText = "Профиль", bottomButton = true) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 90.dp)
            ) {
                ScreenHead(navigator = navigator)
                ScreenBody(navigator = navigator)
            }
        }
    } else {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize())
        {
            Image(
                painter = painterResource(id = R.drawable.workall_logo),
                contentDescription = "Лого",
                modifier = Modifier.fillMaxSize(0.7f)
            )
        }
    }



}

@Composable
private fun ScreenHead(
    viewModel: HomeViewModel = koinViewModel(),
    navigator: Navigator?
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.good_tick),
            contentDescription = "Значок подтверждения полной регистрации",
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
                Log.d("Имя 2", viewModel.nameUser.collectAsState().value)
                Text(
                    text = viewModel.nameUser.collectAsState().value/*"Имя"*/,
                    style = Tema.Text_24_24_Bold,
                    color = MaterialTheme.colorScheme.primary
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
                        .clickable { viewModel.Exit(navigator) },
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}

@Composable
private fun ScreenBody(navigator: Navigator?) {
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
            CardMain(mainText = mainText, secondText = secondText, icon = icon)
        }
    }
}

@Composable
private fun CardMain(
    mainText: String,
    secondText: String,
    icon: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = icon),
            contentDescription = "Значок",
            tint = MaterialTheme.colorScheme.primary
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = mainText,
                style = Tema.Text_24_24_Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = secondText,
                style = Tema.Text_18_18_Medium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
    Icon(
        modifier = Modifier.size(20.dp),
        painter = painterResource(id = R.drawable.vector),
        contentDescription = "",
        tint = MaterialTheme.colorScheme.primary
    )
}

