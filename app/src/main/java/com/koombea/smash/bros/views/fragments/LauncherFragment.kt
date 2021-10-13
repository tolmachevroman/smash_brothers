package com.koombea.smash.bros.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.koombea.smash.bros.databinding.LauncherFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LauncherFragment : Fragment() {

    private lateinit var binding: LauncherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LauncherFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // check if launched for the 1st time, or skip
    }

}