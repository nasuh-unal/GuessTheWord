package com.example.android.guesstheword.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.ScoreFragmentBinding

class   ScoreFragment : Fragment() {
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.score_fragment, container, false)

        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreText.text = viewModel.score.toString()

        binding.playAgainButton.setOnClickListener {
            onPlayAgain()
        }
        viewModel.onPlayAgainLiveData.observe(viewLifecycleOwner,{playAgain->
            if(playAgain){
                goToGameScreen()
            }
        })
        return binding.root
    }

    private fun onPlayAgain() {
        viewModel.onPlayAgain()
    }

    private fun goToGameScreen(){
        findNavController().navigate(ScoreFragmentDirections.actionRestart())
        viewModel.finishPlayAgainEvent()
    }
}
