package com.example.ozonchick_new_version.Presentation.States

sealed interface ScreenState <out T : Any> {
    data object Loading : ScreenState <Nothing>
    data object Error : ScreenState <Nothing>
    data class Success <out T : Any> (val data : T) : ScreenState <T>
    data class Failure (
        val code : Int,
        val message : String
    ) : ScreenState <Nothing>
}