package com.example.ozonchick_new_version.Presentation.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.ListOfVacancies.StatesOfGetVacancies
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesScreen
import com.example.ozonchick_new_version.Presentation.Screens.ListVacancies.ListVacanciesScreen
import com.example.ozonchick_new_version.Presentation.Screens.Profile.ProfileScreen
import com.example.ozonchick_new_version.Presentation.Screens.Responses.ResponsesScreen
import com.example.ozonchick_new_version.Presentation.Screens.Search.SearchScreen
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    bottomButton: Boolean = false,
    backButton : Boolean = false,
    mainText: String = "",
    modifier: Modifier = Modifier,
    scaffoldStates: ScaffoldStates = ScaffoldStates.Main,
    addVacancy : (() -> Unit) = {},
    navigator : Navigator? = LocalNavigator.current,
    statesOfGetVacancies : StatesOfGetVacancies = StatesOfGetVacancies.Normal,
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
                        if (backButton) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_square_right),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { navigator?.pop() },
                                tint = MainColor
                            )
                        }
                        else {
                            Spacer(Modifier.size(20.dp))
                        }
                        Text(
                            text = mainText,
                            style = Tema.Text_24_24_Medium,
                            color = MainColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                        )
                        if (scaffoldStates == ScaffoldStates.EmployerVacancies) {
                            Icon(
                                painter = painterResource(id = R.drawable.add_button_transparent),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { addVacancy() },
                                tint = MainColor
                            )
                        } else if (scaffoldStates == ScaffoldStates.Vacancies) {
                            Icon(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable { navigator?.push(SearchScreen(statesOfGetVacancies)) },
                                tint = MainColor
                            )
                        } else {
                            Spacer(Modifier.size(20.dp))
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


@Composable
private fun ScaffoldBottomBar(
    navigator : Navigator?,
    sizeIcon : Dp = 35.dp,
    bottomTextStyle : TextStyle = Tema.Text_16_16_Medium
) {
    Log.d("Тип пользователя", localUserInformation.type.toString())
    BottomAppBar(
        containerColor = BackgroundColor,
        modifier = Modifier.drawWithContent {
            drawLine(
                MainColor,
                Offset(0f, 0f),
                Offset(size.width, 0f),
                10f
            )
            drawContent()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CustomClickableIcon(
                navigator = navigator,
                screen = ListVacanciesScreen(),
                iconId = R.drawable.vakansi_icon_3,
                sizeIcon = sizeIcon,
                colorIcon = MainColor,
                bottomText = "Вакансии",
                bottomTextStyle = bottomTextStyle,
                colorBottomText = MainColor
            )
            HorizontalSpacer(5.dp)
            CustomClickableIcon(
                navigator = navigator,
                screen = if (localUserInformation.type is UserType.Employer) EmployerVacanciesScreen() else ResponsesScreen()
                /*when (publicUserId.type) {
                    UserType.Admin -> SignUpScreen()
                    UserType.Candidate -> ResponsesScreen()
                    UserType.Employer -> EmployerVacanciesScreen()
                    UserType.None -> SplashScreen()
                }*/,

                iconId = R.drawable.folder,
                sizeIcon = sizeIcon,
                colorIcon = MainColor,
                bottomText = if (localUserInformation.type is UserType.Employer) "Мои вакансии" else "Отклики"
                /*when (publicUserId.type) {
                    UserType.Admin -> "Работодатель"
                    UserType.Candidate -> "Отклики"
                    UserType.Employer -> "Мои вакансии"
                    UserType.None -> ""
                }*/,

                bottomTextStyle = bottomTextStyle,
                colorBottomText = MainColor
            )
            HorizontalSpacer(5.dp)
            CustomClickableIcon(
                navigator = navigator,
                screen = ProfileScreen(),
                iconId = R.drawable.profile_circle,
                sizeIcon = sizeIcon,
                colorIcon = MainColor,
                bottomText = "Профиль",
                bottomTextStyle = bottomTextStyle,
                colorBottomText = MainColor
            )
        }
    }
}


@Composable
private fun CustomClickableIcon(
    navigator: Navigator?,
    screen: Screen,

    iconId: Int,
    sizeIcon : Dp,
    colorIcon : Color,

    bottomText : String,
    bottomTextStyle : TextStyle,
    colorBottomText : Color,

    ) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { navigator?.push(screen) }
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "Иконка",
            modifier = Modifier
                .size(sizeIcon),
            tint = colorIcon
        )
        Text(
            text = bottomText,
            style = bottomTextStyle,
            color = colorBottomText,
            modifier = Modifier.padding(top = 3.dp)
        )
    }
}