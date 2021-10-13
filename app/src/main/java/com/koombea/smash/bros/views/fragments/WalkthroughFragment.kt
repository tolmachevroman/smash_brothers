package com.koombea.smash.bros.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.koombea.smash.bros.databinding.WalkthroughFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalkthroughFragment : Fragment() {

    private lateinit var binding: WalkthroughFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WalkthroughFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}