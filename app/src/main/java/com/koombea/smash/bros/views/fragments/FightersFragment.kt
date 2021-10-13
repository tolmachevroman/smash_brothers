package com.koombea.smash.bros.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.koombea.smash.bros.R
import com.koombea.smash.bros.databinding.FightersFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FightersFragment : Fragment() {

    lateinit var binding: FightersFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FightersFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.filters) {

            }
            true
        }
    }

}