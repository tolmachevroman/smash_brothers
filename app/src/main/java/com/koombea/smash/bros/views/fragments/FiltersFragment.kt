package com.koombea.smash.bros.views.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

        when (viewModel.getSortBy()) {
            1 -> binding.name.isChecked = true
            2 -> binding.price.isChecked = true
            3 -> binding.rate.isChecked = true
            else -> binding.downloads.isChecked = true
        }

        binding.ratingBar.rating = viewModel.getFilterBy().toFloat()

        binding.applyButton.setOnClickListener {
            val newSortBy = when {
                binding.name.isChecked -> {
                    1
                }
                binding.price.isChecked -> {
                    2
                }
                binding.rate.isChecked -> {
                    3
                }
                else -> {
                    4
                }
            }
            viewModel.setSortBy(newSortBy)
            viewModel.setFilterBy(binding.ratingBar.rating.toInt())
            findNavController().popBackStack()
        }

        binding.resetButton.setOnClickListener {
            binding.name.isChecked = true
            binding.ratingBar.rating = 0.0f
            viewModel.reset()
        }
    }
}