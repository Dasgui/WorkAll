package com.example.ozonchick_new_version.Presentation.Screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ViewModels.ContactsViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.RowTextImage
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.get
import org.koin.core.parameter.parametersOf

var isLoading: MutableState<Boolean> = mutableStateOf(false)

class ContactsScreen (private val sharedId: Int) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ContactsViewModel = get {parametersOf(sharedId)}
        Ozonchick_new_versionTheme {
            Contacts_construct(viewModel)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Contacts_construct(viewModel: ContactsViewModel) {
    val navigator = LocalNavigator.current
    val screenState = viewModel.contactsState.collectAsState()
    when (val curState = screenState.value) {
        is ScreenState.Failure -> FailAlertDialog(
            title = "Ошибка",
            curState.message,
            onOkClick = {
                viewModel.contactsState.value = ScreenState.Loading
            })
        is ScreenState.Loading -> CustomLoading()
        is ScreenState.Success -> {
            CustomScaffold(
                mainText = "Контактная информация",
                bottomButton = true,
                scaffoldStates = ScaffoldStates.OnSide,
                mainBody = {
                    Column (
                        modifier = Modifier
                            .padding(bottom = 80.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        RowTextImage(
                            R.drawable.phone_fill_icon,
                            title = "Номер телефона:",
                            text = viewModel.informationList.collectAsState().value[0].vakans_number,
                            isDescription = true
                        )
//                        Text(
//                            text = "Номер телефона: ${viewModel.informationList.collectAsState().value[0].vakans_number}",
//                            style = Tema.Text_24_24_Medium,
//                            color = MainColor,
//                            textAlign = TextAlign.Start,
//                            modifier = Modifier.padding(horizontal = 5.dp)
//                        )
                        RowTextImage(
                            R.drawable.email_fill_icon,
                            title = "Почта:",
                            text = viewModel.informationList.collectAsState().value[0].vakans_mail,
                            isDescription = true
                            )
//                        Text(
//                            text ="Почта: ${viewModel.informationList.collectAsState().value[0].vakans_mail}",
//                            style = Tema.Text_24_24_Medium,
//                            color = MainColor,
//                            textAlign = TextAlign.Start,
//                            modifier = Modifier.padding(horizontal = 5.dp)
//                        )
                        RowTextImage(
                            R.drawable.town_icon,
                            title = "Расположение:",
                            text = viewModel.informationList.collectAsState().value[0].vakans_raspolog,
                            isDescription = true
                        )
                        Canvas(modifier = Modifier.fillMaxWidth()) {
                            drawLine(
                                color = MainColor,
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                strokeWidth = 15f
                            )
                        }
                        VerticalSpacer(20.dp)
//                        Text(
//                            text = "Расположение: ${viewModel.informationList.collectAsState().value[0].vakans_raspolog}",
//                            style = Tema.Text_24_24_Medium,
//                            color = MainColor,
//                            textAlign = TextAlign.Start,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .drawBehind {
//                                    drawLine(
//                                        MainColor,
//                                        Offset(0f, size.height),
//                                        Offset(size.width, size.height),
//                                        10f
//                                    )
//                                }
//                                .padding(start = 5.dp, end = 5.dp, bottom = 10.dp)
//                        )
                        RowTextImage(
                            R.drawable.information_icon,
                            title = "О работе: ",
                            viewModel.informationList.collectAsState().value[0].vakans_aboutWork,
                            isDescription = true
                        )
//                        Text(
//                            text = "О работе:",
//                            style = Tema.Text_24_24_Medium,
//                            color = MainColor,
//                            textAlign = TextAlign.Start,
//                            modifier = Modifier.padding( top = 30.dp),
//                        )
//                        Text(
//                            text = viewModel.informationList.collectAsState().value[0].vakans_aboutWork,
//                            style = Tema.Text_24_24_Medium,
//                            color = MainColor,
//                            textAlign = TextAlign.Start,
//                            modifier = Modifier.padding(start = 5.dp, end = 10.dp)
//                        )
                    }
                }
            )
        }

        is ScreenState.Error -> Unit
        ScreenState.AwaitingChangeFromUser -> Unit
        ScreenState.AwaitingResponse -> Unit
    }
//    Scaffold (
//        topBar = {
//            TopAppBar(
//                title = {},
//                navigationIcon = {
//                    Icon(
//                        painter = painterResource(id = R.drawable.arrow_square_right),
//                        contentDescription = "",
//                        modifier = Modifier
//                            .size(35.dp)
//                            .clickable { navigator?.pop() },
//                        tint = MainColor,
//                    )
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor),
//                modifier = Modifier
//                    .fillMaxWidth(),
//            )
//        },
//
//        ) { innerPadding ->
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding),
//            horizontalAlignment = Alignment.Start,
//            verticalArrangement = Arrangement.SpaceBetween,
//        ) {
//            item {
//                Text(
//                    text = "Номер телефона: ${viewModel.informationList.collectAsState().value[0].vakans_number}",
//                    style = Tema.Text_24_24_Medium,
//                    color = MaterialTheme.colorScheme.primary,
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier.padding(horizontal = 5.dp)
//                )
//                Text(
//                    text ="Почта: ${viewModel.informationList.collectAsState().value[0].vakans_mail}",
//                    style = Tema.Text_24_24_Medium,
//                    color = MaterialTheme.colorScheme.primary,
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier.padding(horizontal = 5.dp)
//                )
//                Text(
//                    text = "Расположение: ${viewModel.informationList.collectAsState().value[0].vakans_raspolog}",
//                    style = Tema.Text_24_24_Medium,
//                    color = MaterialTheme.colorScheme.primary,
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier.fillMaxWidth()
//                        .drawBehind {
//                            drawLine(
//                                MainColor,
//                                Offset(0f, size.height),
//                                Offset(size.width, size.height),
//                                10f
//                            )
//                        }
//                        .padding(start = 5.dp, end = 5.dp, bottom = 10.dp)
//                )
//                Text(
//                    text = "О работе:",
//                    style = Tema.Text_24_24_Medium,
//                    color = MaterialTheme.colorScheme.primary,
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier.padding( top = 30.dp).padding(start =  5.dp, end = 5.dp),
//                )
//                Text(
//                    text = viewModel.informationList.collectAsState().value[0].vakans_aboutWork,
//                    style = Tema.Text_24_24_Medium,
//                    color = MaterialTheme.colorScheme.primary,
//                    textAlign = TextAlign.Start,
//                    modifier = Modifier.padding(start = 5.dp, end = 10.dp)
//                )
//            }
//        }
//    }
}
