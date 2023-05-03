package com.example.android.guesstheword.screens.title
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {
    private lateinit var titleViewModel: TitleViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: TitleFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.title_fragment, container, false)
        binding.playButtonViewState=PlayButtonViewState(false)
        binding.playGameButton.setOnClickListener {
            findNavController().navigate(TitleFragmentDirections.actionTitleToGame())
        }
        titleViewModel = ViewModelProvider(this).get(TitleViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.titleViewModell = titleViewModel //Bu satırı yazmamızın sebebi ViewModel sınıfından dönen değeri tekrar görünüme atamak
        titleViewModel.playButtonVisibility.observe(viewLifecycleOwner, {
            //binding.playGameButton.visibility = it
            binding.playButtonViewState=it
            binding.executePendingBindings()
        })
        return binding.root
    }
}
