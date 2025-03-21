package com.example.ozonchick_new_version.Presentation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.ui.components.Public_Button
import com.example.ozonchick_new_version.Presentation.ui.components.Public_Card
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.middleTextStyle

class ListOfCandidate_Screen: Screen {
    @Composable
    @Preview
    override fun Content() {
        ListOfCandidate_construct()
    }
}

@Composable
fun ListOfCandidate_construct(/*vm: ListOfCandidate_ViewModel = koinViewModel()*/)
{
    CustomScaffold(
        bottomButton = true,
        scaffoldStates = ScaffoldStates.OnSide,
        mainText = "Откликнувшиеся соискатели"
    ) {
        Public_Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp, vertical = 5.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start
                    ) {
                        //Требуется специалист Name
                        Text(
                            text = "Николай",
                            style = middleTextStyle.value,
                            color = MainColor
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Public_Button(
                            fillButton = true,
                            onClick = {

                            },
                            modifier = Modifier.width(180.dp)
                        ) {
                            Text(
                                text = "Принять",
                                style = middleTextStyle.value,
                            )
                        }
                        Public_Button(
                            fillButton = false,
                            onClick = {

                            },
                            modifier = Modifier.width(180.dp)
                        ) {
                            Text(
                                text = "Отклонить",
                                style = middleTextStyle.value,
                            )
                        }
                        Public_Button(
                            fillButton = false,
                            onClick = {

                            },
                            modifier = Modifier.width(180.dp)
                        ) {
                            Text(
                                text = "Резюме",
                                style = middleTextStyle.value,
                            )
                        }
                    }
                }
            }
        }
        Public_Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp, vertical = 5.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start
                    ) {
                        //Требуется специалист Name
                        Text(
                            text = "Александр",
                            style = middleTextStyle.value,
                            color = MainColor
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Public_Button(
                            fillButton = true,
                            onClick = {

                            },
                            modifier = Modifier.width(180.dp)
                        ) {
                            Text(
                                text = "Принять",
                                style = middleTextStyle.value,
                            )
                        }
                        Public_Button(
                            fillButton = false,
                            onClick = {

                            },
                            modifier = Modifier.width(180.dp)
                        ) {
                            Text(
                                text = "Отклонить",
                                style = middleTextStyle.value,
                            )
                        }
                        Public_Button(
                            fillButton = false,
                            onClick = {

                            },
                            modifier = Modifier.width(180.dp)
                        ) {
                            Text(
                                text = "Резюме",
                                style = middleTextStyle.value,
                            )
                        }
                    }
                }
            }
        }

                    Public_Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 15.dp, vertical = 5.dp),
                            verticalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceEvenly,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    //Требуется специалист Name
                                    Text(
                                        text = "Дмитрий",
                                        style = middleTextStyle.value,
                                        color = MainColor
                                    )
                                }
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.End,
                                    modifier = Modifier.fillMaxHeight()
                                ) {
                                    Public_Button(
                                        fillButton = true,
                                        onClick = {

                                        },
                                        modifier = Modifier.width(180.dp)
                                    ) {
                                        Text(
                                            text = "Принять",
                                            style = middleTextStyle.value,
                                        )
                                    }
                                    Public_Button(
                                        fillButton = false,
                                        onClick = {

                                        },
                                        modifier = Modifier.width(180.dp)
                                    ) {
                                        Text(
                                            text = "Отклонить",
                                            style = middleTextStyle.value,
                                        )
                                    }
                                    Public_Button(
                                        fillButton = false,
                                        onClick = {

                                        },
                                        modifier = Modifier.width(180.dp)
                                    ) {
                                        Text(
                                            text = "Резюме",
                                            style = middleTextStyle.value,
                                        )
                                    }
                                }
                            }
                        }
                    }


//            var isLoading: MutableState<Boolean> = remember {
//                mutableStateOf(false)
//            }
//            LaunchedEffect(key1 = true) {
//                CoroutineScope(Dispatchers.IO).launch {
//                    vm.GetCandidates()
//                    delay(1000)
//                    isLoading.value = true
//                }
//
//            }

    }
}

@Composable
fun Card_Candidate(number: Int, list: MutableList<VakansInformation>)
{
    var localNumber: MutableState<Int> = remember {
        mutableIntStateOf(number)
    }
    val context = LocalContext.current
    var contactList: MutableList<Vakans_contacts> = mutableListOf()
    val navigator = LocalNavigator.current
    Public_Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(bottom = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 5.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    //Требуется специалист Name
                    Text(
                        text = list[number].vakans_name/*"Инженер"*/,
                        style = middleTextStyle.value,
                        color = MainColor
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Public_Button(
                        fillButton = true,
                        onClick = {

                        },
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(
                            text = "Принять",
                            style = middleTextStyle.value,
                        )
                    }
                    Public_Button(
                        fillButton = false,
                        onClick = {

                        },
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(
                            text = "Отклонить",
                            style = middleTextStyle.value,
                        )
                    }
                }
            }
        }


    }
}