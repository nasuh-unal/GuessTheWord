package com.example.android.guesstheword.screens.game
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class GameViewModel: ViewModel() {

    private val timer:CountDownTimer

    private val _wordLiveData = MutableLiveData<String>()
    val wordLiveData:LiveData<String> get()=_wordLiveData

    private val _scoreLiveData = MutableLiveData<Int>()
    val scoreLiveData:LiveData<Int> get() = _scoreLiveData

    private val _gameFinishEventLiveData=MutableLiveData<Boolean>()
    val gameFinishEventLiveData:LiveData<Boolean> get() = _gameFinishEventLiveData

    private val _currentTimeLiveData=MutableLiveData<Long>()

    private lateinit var wordList: MutableList<String>

    val currentTimeString = Transformations.map(_currentTimeLiveData) { time ->
        DateUtils.formatElapsedTime(time)
    }

    init {
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
        _wordLiveData.value=""
        _scoreLiveData.value=0
        timer= object :CountDownTimer(START_TIME, ONE_SECOND ) {
            override fun onTick(millisUntilFinished: Long) { //Azalma miktarınca çalışacaktır
                _currentTimeLiveData.value=millisUntilFinished/ ONE_SECOND
            }//LiveData Transformation ile veriler her güncellendiğinde bu verileri biçimlendirebiliriz
            override fun onFinish() {  //
                _currentTimeLiveData.value= DONE
            }
        }
        timer.start()
    }

    companion object{
        private const val START_TIME=60000L
        private const val ONE_SECOND=1000L
        private const val DONE=0L
    }

     fun onSkip() {
        _scoreLiveData.value=_scoreLiveData.value?.minus(1)
        nextWord()
    }

     fun onCorrect() {
         _scoreLiveData.value=(_scoreLiveData.value)?.plus(1)
        nextWord()
    }

    private fun nextWord() {
        if (!wordList.isEmpty()) {
            _wordLiveData.value = wordList.removeAt(0)
        }else{
            onFinishGame()
        }
    }

    fun onFinishGame() {
        _gameFinishEventLiveData.value=true
    }

    fun disableGameFinishEvent() {
        _gameFinishEventLiveData.value=false
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
        timer.cancel()
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }
}

