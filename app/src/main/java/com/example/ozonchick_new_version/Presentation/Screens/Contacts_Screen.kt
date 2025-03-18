package com.example.ozonchick_new_version.Presentation.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Presentation.ViewModels.ContactsViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.Public_Loading
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import org.koin.androidx.compose.koinViewModel

var isLoading: MutableState<Boolean> = mutableStateOf(false)

class Contacts_Screen (private val sharedId: Int) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ContactsViewModel = koinViewModel()
        Ozonchick_new_versionTheme {
            LaunchedEffect(viewModel.informationList.collectAsState().value.isEmpty())
            {
                viewModel.getInformation(sharedId)
                isLoading.value = true
            }
            Contacts_construct()
        }

    }
}
@Preview
@Composable
fun PreviewContacts() {
    Contacts_construct()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Contacts_construct(viewModel: ContactsViewModel = koinViewModel()) {
    if (isLoading.value) {
        val navigator = LocalNavigator.current
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_square_right),
                            contentDescription = "",
                            modifier = Modifier
                                .size(35.dp)
                                .clickable { navigator?.pop() },
                            tint = MainColor,
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundColor),
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            },

        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                item {
                    Text(
                        text = "Номер телефона: ${viewModel.informationList.collectAsState().value[0].vakans_number}",
                        style = Tema.Text_24_24_Medium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                    Text(
                        text ="Почта: ${viewModel.informationList.collectAsState().value[0].vakans_mail}",
                        style = Tema.Text_24_24_Medium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                    Text(
                        text = "Расположение: ${viewModel.informationList.collectAsState().value[0].vakans_raspolog}",
                        style = Tema.Text_24_24_Medium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                            .drawBehind {
                                drawLine(
                                    MainColor,
                                    Offset(0f, size.height),
                                    Offset(size.width, size.height),
                                    10f
                                )
                            }
                            .padding(start = 5.dp, end = 5.dp, bottom = 10.dp)
                    )
                    Text(
                        text = "О работе:",
                        style = Tema.Text_24_24_Medium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding( top = 30.dp).padding(start =  5.dp, end = 5.dp),
                    )
                    Text(
                        text = viewModel.informationList.collectAsState().value[0].vakans_aboutWork,
                        style = Tema.Text_24_24_Medium,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 5.dp, end = 10.dp)
                    )
                }
            }
        }
//        Public_Scaffold(
//            mainText = "Контакты",
//            backButton = true,
//            topBar = {
//                TopAppBar(
//                    title = {
//                        Row(
//                            modifier = Modifier.fillMaxSize(),
//                            horizontalArrangement = Arrangement.Start,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.arrow_square_right),
//                                contentDescription = "",
//                                modifier = Modifier
//                                    .size(30.dp)
//                                    .clickable {
//
//                                        navigator?.pop()
//                                    },
//                                tint = mainColor
//                            )
//                        }
//                    },
//                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                )
//            }
//        ) {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 65.dp),
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.SpaceBetween,
//            ) {
//                item {
//                    Text(
//                        text = "Номер телефона: ${viewModel.informationList.collectAsState().value[0].vakans_number}",
//                        style = Tema.Text_24_24_Medium,
//                        color = MaterialTheme.colorScheme.primary,
//                        textAlign = TextAlign.Start,
//                        modifier = Modifier.padding(horizontal = 5.dp)
//                    )
//                    Text(
//                        text ="Почта: ${viewModel.informationList.collectAsState().value[0].vakans_mail}",
//                        style = Tema.Text_24_24_Medium,
//                        color = MaterialTheme.colorScheme.primary,
//                        textAlign = TextAlign.Start,
//                        modifier = Modifier.padding(horizontal = 5.dp)
//                    )
//                    Text(
//                        text = "Расположение: ${viewModel.informationList.collectAsState().value[0].vakans_raspolog}",
//                        style = Tema.Text_24_24_Medium,
//                        color = MaterialTheme.colorScheme.primary,
//                        textAlign = TextAlign.Start,
//                        modifier = Modifier.fillMaxWidth()
//                            .drawBehind {
//                            drawLine(
//                                mainColor,
//                                Offset(0f, size.height),
//                                Offset(size.width, size.height),
//                                10f
//                            )
//                        }
//                            .padding(start = 5.dp, end = 5.dp, bottom = 10.dp)
//                    )
//                    Text(
//                        text = "О работе:",
//                        style = Tema.Text_24_24_Medium,
//                        color = MaterialTheme.colorScheme.primary,
//                        textAlign = TextAlign.Start,
//                        modifier = Modifier.padding( top = 30.dp).padding(start =  5.dp, end = 5.dp),
//                    )
//                    Text(
//                        text = viewModel.informationList.collectAsState().value[0].vakans_aboutWork,
//                        style = Tema.Text_24_24_Medium,
//                        color = MaterialTheme.colorScheme.primary,
//                        textAlign = TextAlign.Start,
//                        modifier = Modifier.padding(start = 5.dp, end = 10.dp)
//                    )
//                }
//            }
//        }
    } else {
        Public_Loading(isLoading = isLoading.value)
    }
}