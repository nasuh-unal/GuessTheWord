package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.i("GameFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)

        viewModel.gameFinishEventLiveData.observe(viewLifecycleOwner,{hasGameFinished->
            if (hasGameFinished){
                gameFinished()
            }
        })

        binding.gameViewModel=viewModel
        binding.lifecycleOwner=viewLifecycleOwner

        /*viewModel.wordLiveData.observe(viewLifecycleOwner,{
            binding.wordIsText.text=it
        })

        viewModel.scoreLiveData.observe(viewLifecycleOwner,{
            binding.scoreText.text=it.toString()
        })*/
/*
        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        binding.endGameButton.setOnClickListener { onEndGame() }*/
        //updateScoreText()
        //updateWordText()
        return binding.root
    }
    private fun gameFinished() {
        viewModel.disableGameFinishEvent()
        val action=GameFragmentDirections.actionGameToScore()
        action.score=viewModel.scoreLiveData.value?:0
        NavHostFragment.findNavController(this).navigate(action)
    }
/*
    private fun onEndGame() {
       viewModel.onFinishGame()/*
        val action=GameFragmentDirections.actionGameToScore()
        action.score=viewModel.scoreLiveData.value?:0
        NavHostFragment.findNavController(this).navigate(action)*/
    }*/

/*
    private fun onSkip() {
        viewModel.onSkip()
        //updateScoreText()
        //updateWordText()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
        //updateScoreText()
        //updateWordText()
    }
*/
    /*
    private fun updateWordText() {
        //binding.wordText.text = viewModel.wordLiveData.value
    }

    private fun updateScoreText() {
        //binding.scoreText.text = viewModel.scoreLiveData.value.toString()
    }*/
}
