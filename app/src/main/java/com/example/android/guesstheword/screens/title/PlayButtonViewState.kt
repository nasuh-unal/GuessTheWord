package com.example.android.guesstheword.screens.title

import android.opengl.Visibility
import android.view.View

data class PlayButtonViewState(private val isPlayButtonVisible:Boolean) {
    fun getPlayButtonVisibility() =if (isPlayButtonVisible==true) View.VISIBLE else View.INVISIBLE
}