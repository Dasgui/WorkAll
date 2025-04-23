package com.example.ozonchick_new_version.Presentation.Screens.Search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data.model.ListOfVacancies.StatesOfGetVacancies
import com.example.ozonchick_new_version.Presentation.Screens.ListVacancies.ListVacanciesScreen
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.TextFieldStates
import com.example.ozonchick_new_version.Presentation.ui.components.CustomButton
import com.example.ozonchick_new_version.Presentation.ui.components.CustomCheckBox
import com.example.ozonchick_new_version.Presentation.ui.components.CustomOutlinedTextField
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.HorizontalSpacer
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.koinViewModel

class SearchScreen(private val statesOfGetVacancies: StatesOfGetVacancies = StatesOfGetVacancies.Normal) : Screen {
    @Composable
    override fun Content() {
        SearchConstruct(statesOfGetVacancies = statesOfGetVacancies)
    }
}

@Composable
private fun SearchConstruct(viewModel: SearchViewModel = koinViewModel(), statesOfGetVacancies: StatesOfGetVacancies) {
    val navigator = LocalNavigator.current
    LaunchedEffect(true) {
        when (val localState = statesOfGetVacancies) {
            is StatesOfGetVacancies.Filter -> {
                viewModel.loadFilters(localState)
            }
            StatesOfGetVacancies.Normal -> Unit
        }

    }
    CustomScaffold(
        mainText = "Поиск",
        scaffoldStates = ScaffoldStates.Vacancies
    ) {
        TextFields(viewModel)
        VerticalSpacer(25.dp)
        BottomButtons(viewModel, navigator)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFields(viewModel: SearchViewModel) {
    Text(
        text = "Поиск",
        style = Tema.Text_20_20_Medium,
        color = MainColor
    )
    VerticalSpacer(10.dp)
    CustomOutlinedTextField(
        value = viewModel.search.value,
        onValueChange = {
            viewModel.search.value = it
        },
        modifier = Modifier.fillMaxWidth()
    )
    VerticalSpacer(20.dp)
    FilterField(
        viewModel.checkSalary,
        viewModel.minSalaryText,
        title = "Учитывать начальный уровень зарплаты",
        image = R.drawable.wallet_3
    )
    VerticalSpacer(20.dp)
    FilterDropMenu(viewModel.checkExperience, title = "Учитывать требуемый опыт работы", textField = viewModel.experience, viewModel.checkListExperience, viewModel.listExperience)
}

@Composable
private fun FilterField(
    checked: MutableState<Boolean>, filter: MutableState<String>,
    title: String, image: Int,
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomCheckBox(
            checked = checked,
            onChange = {
                checked.value = it
            },
            modifier = Modifier
                .size(24.dp)
                .padding(start = 0.dp)
        )
        HorizontalSpacer(10.dp)
        Text(
            text = title,
            style = Tema.Text_20_20_Medium,
            color = MainColor
        )
    }
    VerticalSpacer(15.dp)
    if (checked.value) {
        CustomOutlinedTextField(
            value = filter.value,
            states = TextFieldStates.Salary,
            trailingIcon = {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MainColor),
                    modifier = Modifier.size(30.dp)
                )
            },
            onValueChange = {
                filter.value = it
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterDropMenu(
    checked: MutableState<Boolean>,
    title: String,
    textField : MutableState<String>, visibleList : MutableState<Boolean>,
    dropItemsList : MutableState<List<String>>

) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomCheckBox(
            checked = checked,
            onChange = {
                checked.value = it
            },
            modifier = Modifier
                .size(24.dp)
                .padding(start = 0.dp)
        )
        HorizontalSpacer(10.dp)
        Text(
            text = title,
            style = Tema.Text_20_20_Medium,
            color = MainColor
        )
    }
    VerticalSpacer(15.dp)
    if (checked.value) {
        CustomOutlinedTextField(
            value = textField.value,
            states = TextFieldStates.None,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_square_down),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { visibleList.value = true },
                    tint = MainColor
                )
                DropdownMenu(
                    expanded = visibleList.value,
                    onDismissRequest = {
                        visibleList.value = false
                    },
                    containerColor = BackgroundColor,
                ) {
                    for (item in 0 until dropItemsList.value.size) {
                        if (item != 0) HorizontalDivider()
                        DropdownMenuItem(
                            text = {
                                Text(
                                    dropItemsList.value[item],
                                    style = Tema.Text_16_16_Medium,
                                    color = MainColor
                                )
                            },
                            onClick = {
                                textField.value = dropItemsList.value[item]
                                visibleList.value = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            },

            )
    }
}


@Composable
fun BottomButtons(viewModel: SearchViewModel, navigator: Navigator?) {
    CustomButton(
        onClick = {
            navigator?.replaceAll(
                ListVacanciesScreen(
                    viewModel.getStatesOfGetVacancies()
                )
            )
        },
        placeText = "Найти вакансии"
    )
    VerticalSpacer(10.dp)
    CustomButton(
        onClick = {
            viewModel.resetFilters()
//            navigator?.replaceAll(
//                ListVacanciesScreen(
//                    StatesOfGetVacancies.Normal
//                )
//            )
        },
        placeText = "Сбросить поиск"
    )
}