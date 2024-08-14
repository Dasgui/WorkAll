package com.example.ozonchick_new_version.Screens.Onboarding

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Onboarding_Components.Onboarding_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Primary_Color
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.LogIn_Screen
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.SignUp_Screen

class Onboarding_Screen_3 : Screen {
    @Composable
    override fun Content() {
        Onboarding_3_Construct()
    }
}

@Composable
fun Onboarding_3_Construct() {
    val navigator = LocalNavigator.current
//    val Screen = rememberScreen(provider = Onboarding_Screens.Onboarding1)
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        item {
            Box(
                modifier = Modifier.padding(top = 105.dp, bottom = 63.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rafiki_2),
                    contentDescription = "Главное лого",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(298.dp)
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
                        .padding(start = 47.dp, end = 47.dp),
                ) {
                    Text(
                        text = "Real-time Tracking",
                        // Heading -Bold 24
                        style = Tema.BigMainText_24_24_700,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Track your packages/items from the comfort of your home till final destination",
                        // Heading -Bold 24
                        style = Tema.SmallMainText_16_20_400,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 82.dp)
                ) {
                    Onboarding_Button(
                        onClick = { navigator?.replaceAll(SignUp_Screen()) },
                        colors = ButtonDefaults.buttonColors(containerColor = Primary_Color),
                        modifier = Modifier.fillMaxWidth()
                    )
                    {
                        Text(
                            text = "Sign up",
                            style = Tema.ButtonText_14_10_700_Active
                        )
                    }
                }
                val SignInText = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                        )
                    )
                    {
                        append("Already have an account? ")
                    }

                    pushStringAnnotation(tag = "click", annotation = "click")
                    withStyle(
                        SpanStyle(
                            color = Primary_Color,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                        )
                    ) {
                        append("Sign in")
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
                /*                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.Black,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight(400),
                                            )
                                        ) {
                                            append("Already have an account?")
                                        }
                                        pushStringAnnotation(tag = "Sign in", annotation = "Sign in")
                                        withStyle(
                                            style = SpanStyle(
                                                color = Primary_Color,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight(400),
                                            ),
                                            ) {
                                            append("Sign in")
                                        }
                                    },

                                    *//*style = Tema.SecondaryText_14_16,*//*
                    modifier = Modifier.padding(top = 20.dp)
                )*/

            }
        }


    }


    /*    val navigator = LocalNavigator.current
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Box(

            ) {
                Image(painter = painterResource(id = R.drawable.rafiki_2),
                    contentDescription = "Главное лого",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp)
                )
            }
            Column(

            ) {
                Text(
                    text = "Real-time Tracking",
                    // Heading -Bold 24
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF0560FA),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .width(287.dp)
                        .height(52.dp)
                )
                Text(
                    text = "Track your packages/items from the comfort of your home till final destination",
                    // Heading -Bold 24
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF3A3A3A),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .width(271.dp)
                        .height(40.dp)
                )
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Primary_Color),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 96.dp)
                    .width(100.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 9.38.sp,
                        fontWeight = FontWeight(700),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                )
            }
            Text(
                text = "Already have an account? Sign in",
                // Body regular 14
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFA7A7A7),
                ),
                modifier = Modifier.padding(top = 20.dp)
            )
        }*/
}

@Composable
fun SpannableTextScreen() {

    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
            )
        )
        {
            append("Already have an account? ")
        }

        pushStringAnnotation(tag = "click", annotation = "click")
        withStyle(
            SpanStyle(
                color = Primary_Color,
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
            )
        ) {
            append("Sign in")
        }
    }
    val context = LocalContext.current

    Box(
    ) {
        ClickableText(text = text, onClick = { offset ->
            text.getStringAnnotations(tag = "click", start = offset, end = offset).firstOrNull()
                ?.let {
                    // on click operation here
                    Toast.makeText(context, "hey", Toast.LENGTH_SHORT).show()
                }
        })
    }

}