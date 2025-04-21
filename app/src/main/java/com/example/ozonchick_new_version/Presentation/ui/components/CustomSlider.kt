package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.decode.ImageSource
import com.example.ozonchick_new_version.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSlider (
    sliderValue : MutableState<Float>,
    valueRange :  ClosedFloatingPointRange<Float>
) {
// Пример со слайдером
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        TextField(
//            value = viewModel.minSalaryText.value,
//            onValueChange = { newValue ->
//                viewModel.minSalaryText.value = newValue
//                viewModel.validateAndAdjustSalaries(newValue, viewModel.maxSalaryText.value)
//            },
//            modifier = Modifier.weight(1f),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            label = { Text("Мин. зарплата") }
//        )
//
//        Spacer(Modifier.width(16.dp))
//
//        TextField(
//            value = viewModel.maxSalaryText.value,
//            onValueChange = { newValue ->
//                viewModel.maxSalaryText.value = newValue
//                viewModel.validateAndAdjustSalaries(viewModel.minSalaryText.value, newValue)
//            },
//            modifier = Modifier.weight(1f),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            label = { Text("Макс. зарплата") }
//        )
//    }
//
//    Slider(
//        value = viewModel.currentSalary.value,
//        onValueChange = {
//            viewModel.currentSalary.value = it
//        },
//        valueRange = viewModel.minSalary.intValue.toFloat()..viewModel.maxSalary.intValue.toFloat()
//    )
//
//    // Текущее значение
//    Text(
//        text = "Выбранная зарплата: ${viewModel.currentSalary.floatValue.toInt()} ₽",
//        modifier = Modifier.align(Alignment.CenterHorizontally),
//    )

//    fun validateAndAdjustSalaries(newMin: String, newMax: String) {
//        // Парсим значения (если не число, используем 0)
//        val min = newMin.toIntOrNull() ?: 0
//        val max = newMax.toIntOrNull() ?: 0
//
//        // Корректируем значения, если min > max
//        if (min > max) {
//            minSalaryText.value = max.toString()
//            maxSalaryText.value = min.toString()
//            minSalary.intValue = max
//            maxSalary.intValue = min
//        } else {
//            minSalary.intValue = min
//            maxSalary.intValue = max
//        }
//
//        // Обновляем текущее значение слайдера, если оно выходит за границы
//        if (currentSalary.floatValue < minSalary.intValue) {
//            currentSalary.floatValue = minSalary.intValue.toFloat()
//        } else if (currentSalary.floatValue > maxSalary.intValue) {
//            currentSalary.floatValue = maxSalary.intValue.toFloat()
//        }
//    }

//    var sliderPosition by remember{mutableStateOf(0f)}
    Slider(
        value = sliderValue.value,
        onValueChange = {sliderValue.value = it},
        modifier = Modifier.fillMaxWidth(),
        colors = SliderDefaults.colors(
            thumbColor = Color.Red,
            activeTrackColor = Color.Black,
            inactiveTrackColor = Color.Green
        ),
        valueRange = valueRange
    )
}