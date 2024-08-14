package com.example.ozonchick_new_version.Data_Layer.data.ui.components.Sand_a_Package_Components

import androidx.compose.runtime.Composable

@Composable
fun Send_a_Package_Details(valueTextField : String, onValueChange:(String) -> Unit, placeholder: @Composable() (() -> Unit)? = null ) {
    Send_a_Package_Card() {
     Send_a_Package_TextField(value =valueTextField , onValueChange = onValueChange, placeholder = placeholder)
    }
}