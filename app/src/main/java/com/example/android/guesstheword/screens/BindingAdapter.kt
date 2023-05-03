package com.example.android.guesstheword.screens

import android.widget.Button
import androidx.databinding.BindingAdapter

@BindingAdapter("playButtonVisibility")
fun Button.setPlayButtonVisibility(playButtonVisibility:Int){
    this.visibility=playButtonVisibility
}
