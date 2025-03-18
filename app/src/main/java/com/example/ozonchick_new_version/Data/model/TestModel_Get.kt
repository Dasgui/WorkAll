package com.example.ozonchick_new_version.Data.model

import androidx.compose.runtime.MutableState

data class TestModel_Get(
    val list:MutableList<Vakans_contacts>,
    val number:MutableState<Int>
)
