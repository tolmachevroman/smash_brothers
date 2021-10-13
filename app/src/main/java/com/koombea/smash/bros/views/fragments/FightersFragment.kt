package com.koombea.smash.bros.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.koombea.smash.bros.R
import com.koombea.smash.bros.data.models.Universe
import com.koombea.smash.bros.databinding.FightersFragmentBinding
import com.koombea.smash.bros.utils.ResourceObserver
import com.koombea.smash.bros.viewmodels.FightersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FightersFragment : Fragment() {

    lateinit var binding: FightersFragmentBinding
    private val viewModel by viewModels<FightersViewModel>()

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
                val directions = FightersFragmentDirections.navigateToFilters()
                findNavController().navigate(directions)
            }
            true
        }

        viewModel.getUniverses().observe(
            viewLifecycleOwner, ResourceObserver(
                javaClass.simpleName,
                hideLoading = { binding.progressBar.visibility = View.GONE },
                showLoading = { binding.progressBar.visibility = View.VISIBLE },
                onSuccess = { universes: List<Universe> ->
                    //TODO populate tab pager
                },
                onError = { errorMessage ->
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }
            )
        )
    }

}