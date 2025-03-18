package com.example.ozonchick_new_version.Common

import com.example.ozonchick_new_version.Presentation.States.ScreenState

sealed interface CustomResult<out T : Any> {
    data class Success <out T : Any> (val data : T) : CustomResult <T>
    data class Failure (
        val code : Int,
        val message : String
    ) : CustomResult <Nothing>
}