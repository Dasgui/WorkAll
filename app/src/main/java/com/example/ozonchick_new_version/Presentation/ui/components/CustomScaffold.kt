package com.example.ozonchick_new_version.Presentation.ui.components

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.ui.components.Test_Components.ScaffoldBottomBar
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    bottomButton: Boolean = false,
    mainText: String = "",
    modifier: Modifier = Modifier,
    scaffoldStates: ScaffoldStates = ScaffoldStates.Main,
    navigator : Navigator? = LocalNavigator.current,
    topBar: @Composable () -> Unit = {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawLine(
                        MainColor,
                        Offset(0f, size.height),
                        Offset(size.width, size.height),
                        10f
                    )
                },
            shape = RectangleShape
        ) {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp).fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (scaffoldStates == ScaffoldStates.OnSide) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_square_right),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { navigator?.pop() },
                                tint = MainColor
                            )
                        } else {
                            Spacer(Modifier.size(40.dp))
                        }
                        Text(
                            text = mainText,
                            style = Tema.Text_24_24_Medium,
                            color = MainColor,
                        )
                        if (scaffoldStates == ScaffoldStates.EmployerVacancies) {
                            Icon(
                                painter = painterResource(id = R.drawable.add_button_transparent),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { navigator?.pop() },
                                tint = MainColor
                            )
                        } else {
                            Spacer(Modifier.size(40.dp))
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor),
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    },
    bottomBar: @Composable () -> Unit = {
        if (bottomButton) {
            ScaffoldBottomBar(navigator = navigator, sizeIcon = 35.dp, bottomTextStyle = Tema.Text_16_16_Medium)
        }
    },
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = BackgroundColor,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    mainBody : @Composable () -> Unit
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
    ) {
        LazyColumn (
            modifier = Modifier.padding(it).padding(start = 15.dp, end = 15.dp, top = 25.dp).fillMaxWidth()
        ) {
            item {
                mainBody()
            }
        }
    }
}