package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Presentation.States.TextFieldStates
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R

@Composable
fun <T> CustomComboBox(
    textField: String,
    dropItemsList: List<T>,
    selectedChange : (T) -> Unit,
    enabled : Boolean = true,
    itemToString: (T) -> String = { it.toString() }
) {
    val visible : MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
    CustomOutlinedTextField(
        value = textField,
        states = TextFieldStates.None,
        onValueChange = {},
        enabled = enabled,
        readOnly = true,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.arrow_square_down),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { if (enabled) visible.value = true },
                tint = MainColor
            )
            DropdownMenu(
                expanded = visible.value,
                onDismissRequest = {
                    visible.value = false
                },
                containerColor = BackgroundColor,
            ) {
                dropItemsList.forEachIndexed { index, item ->
                    if (index != 0) HorizontalDivider()
                    DropdownMenuItem(
                        text = {
                            Text(
                                itemToString(item),
                                style = Tema.Text_16_16_Medium,
                                color = MainColor
                            )
                        },
                        onClick = {
                            selectedChange(item)
                            visible.value = false
                        },
                    )
                }
            }
        },
    )
}