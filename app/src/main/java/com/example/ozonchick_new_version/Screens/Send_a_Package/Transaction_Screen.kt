package com.example.ozonchick_new_version.Screens.Send_a_Package

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Sand_a_Package_Components.Send_a_Package_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Primary_Color
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.RobotoFamily
import com.example.ozonchick_new_version.Presentation_Layer.Models.Send_a_Package_ViewModel
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.Home_Provider
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.SignUp_LogIn_Provider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class Transaction_Screen : Screen {
    @Composable
    override fun Content() {
        Ozonchick_new_versionTheme {
            Transaction_construct()
        }

    }
}

@Composable
fun Transaction_construct(vm: Send_a_Package_ViewModel = koinViewModel()) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        var rorate by remember {
            mutableStateOf(0f)
        }
        var DownImage by remember {
            mutableStateOf(R.drawable.cruck)
        }
        val screen = rememberScreen(provider = Home_Provider.Profile)
        val navigator = LocalNavigator.current
        Image(
            painter = painterResource(id = DownImage),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 100.dp)
                .size(120.dp)
                .rotate(rorate)
        )
        CenterText()
        Bottom(navigator, screen)
        LaunchedEffect(key1 = true) {
            CoroutineScope(Dispatchers.IO).launch {
                while (rorate <= 800) {
                    rorate = rorate + 1f
                    delay(1)
                }
                if (rorate >= 800) {
                    DownImage = R.drawable.good_tick
                    rorate = 360f
                }
            }
        }

    }
}

@Composable
fun CenterText()
{
    Column(
        modifier = Modifier.padding(top = 130.dp)
    ) {
        Text(
            text = "Your rider is on the way to your destination",
            style = Tema.MainText_14_16_400
        )
        Text(
            text = "Tracking Number R-7458-4567-4434-5854",
            modifier = Modifier.padding(top = 8.dp),
            style = Tema.MainText_14_16_400,
        )
    }
}
@Composable
fun Bottom(navigator: Navigator?, screen: Screen)
{
    Column(
        modifier = Modifier.padding(top = 140.dp)
    ) {
        Send_a_Package_Button(onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = Primary_Color)) {
            Text(text = "Track my item")
        }
        Send_a_Package_Button(onClick = {navigator?.replaceAll(screen)}, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Primary_Color), modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()) {
            Text(text = "Go back to homepage")
        }
    }
}