package com.example.ozonchick_new_version.Screens.Onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Onboarding_Components.Onboarding_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Primary_Color
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.navigation.screen_provider.Onboarding_Provider

class Onboarding_Screen_1 : Screen {
    @Composable
    override fun Content() {
//        Text(text = "FF")
        Onboarding_1_Construct()
    }
}

@Composable
fun Onboarding_1_Construct() {
    val navigator = LocalNavigator.current
    val screen = rememberScreen(provider = Onboarding_Provider.Onboarding_2)
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 66.dp, bottom = 48.dp, start = 23.dp, end = 21.dp))
            {
                Image(
                    painter = painterResource(id = R.drawable.in_no_time_pana_1),
                    contentDescription = "фото 1",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 47.dp, end = 47.dp)
                ) {
                    Text(
                        text = "Quick Delivery At Your Doorstep",
                        style = Tema.BigMainText_24_24_700,
                        modifier = Modifier.fillMaxWidth()

                    )
                    Text(
                        text = "Enjoy quick pick-up and delivery to your destination",
                        style = Tema.SmallMainText_16_20_400,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)

                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 82.dp)
                        .fillMaxWidth()
                ) {
                    Onboarding_Button(
                        onClick = { navigator?.replaceAll(Onboarding_Screen_3()) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    ) {

                        Text(
                            text = "Skip",
                            style = Tema.ButtonText_14_10_700_Passive
                        )
                    }

                    Onboarding_Button(
                        onClick = { navigator?.replace(screen) },
                        colors = ButtonDefaults.buttonColors(containerColor = Primary_Color),
                    ) {
                        Text(
                            text = "Next",
                            style = Tema.ButtonText_14_10_700_Active
                        )
                    }
                }
            }
        }
    }
}