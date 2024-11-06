package com.example.ozonchick_new_version.Data_Layer.data.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Test_Components.Scafold_BottomBar
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.backgroundColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.mainColor
import com.example.ozonchick_new_version.R


private var bottomTextStyle: androidx.compose.ui.text.TextStyle = Tema.Text_16_16_Medium
private var BigTextStyle: androidx.compose.ui.text.TextStyle = Tema.Text_24_24_Medium
private var sizeIcon: Dp = 35.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Public_Scaffold(
    bottomButton: Boolean = false,
    backButton: Boolean = false,
    mainText: String = "",
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {
        var navigator = LocalNavigator.current
        if (backButton) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            mainColor,
                            Offset(0f, size.height),
                            Offset(size.width, size.height),
                            5f
                        )
                    },
                shape = RectangleShape
            ) {
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
                                    .clickable { navigator?.pop() },
                                tint = mainColor
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor),
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            mainColor,
                            Offset(0f, size.height),
                            Offset(size.width, size.height),
                            5f
                        )
                    },
                shape = RectangleShape
            ) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = mainText,
                                style = BigTextStyle/*Tema.Text_16_16_Medium*/,
                                color = mainColor,
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor),
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        }
    },
    bottomBar: @Composable () -> Unit = {
        if (bottomButton) {
            var navigator = LocalNavigator.current
            Scafold_BottomBar(navigator = navigator, sizeIcon = sizeIcon, bottomTextStyle = bottomTextStyle)
        }
    },
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = backgroundColor,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
        content = content
    )
}