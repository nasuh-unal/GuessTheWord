package com.example.android.guesstheword.screens.title
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class TitleViewModel: ViewModel() {

    val currentText= MutableLiveData<String>()

    val playButtonVisibility=Transformations.map(currentText){
        PlayButtonViewState(it.length>=5)
    }
}