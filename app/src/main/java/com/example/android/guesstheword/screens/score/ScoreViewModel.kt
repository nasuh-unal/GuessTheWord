package com.example.android.guesstheword.screens.score
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore:Int): ViewModel() {

    private val _onPlayAgainLiveData=MutableLiveData<Boolean>()
    val onPlayAgainLiveData:LiveData<Boolean> get() = _onPlayAgainLiveData
    var score = finalScore

        init {
            Log.i("ScoreViewModel", "Nihai puan: $finalScore")
        }
    fun onPlayAgain() {
        _onPlayAgainLiveData.value=true
    }
    fun finishPlayAgainEvent() {
        _onPlayAgainLiveData.value=false
    }
}