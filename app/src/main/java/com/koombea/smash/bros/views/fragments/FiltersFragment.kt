package com.koombea.smash.bros.views.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.koombea.smash.bros.databinding.FiltersFragmentBinding
import com.koombea.smash.bros.viewmodels.FiltersViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.koombea.smash.bros.R
import com.koombea.smash.bros.views.activities.MainActivity

@AndroidEntryPoint
class FiltersFragment : Fragment() {

    private lateinit var binding: FiltersFragmentBinding
    private val viewModel by viewModels<FiltersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FiltersFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).supportActionBar?.apply {
            show()
            title = getString(R.string.filters)
            setDisplayHomeAsUpEnabled(true)
        }
    }
}