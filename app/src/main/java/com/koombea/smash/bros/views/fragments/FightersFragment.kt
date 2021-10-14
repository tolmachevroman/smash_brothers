package com.koombea.smash.bros.views.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.koombea.smash.bros.R
import com.koombea.smash.bros.data.models.Universe
import com.koombea.smash.bros.databinding.FightersFragmentBinding
import com.koombea.smash.bros.utils.ResourceObserver
import com.koombea.smash.bros.viewmodels.FightersViewModel
import com.koombea.smash.bros.views.activities.MainActivity
import com.koombea.smash.bros.views.adapters.UniversesAdapter
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.LinearLayoutManager


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
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.insetsController?.show(WindowInsets.Type.statusBars())
        } else {
            activity?.window?.clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.fighters)
            show()
            setDisplayHomeAsUpEnabled(false)
            setHasOptionsMenu(true)
        }

        viewModel.getUniverses().observe(
            viewLifecycleOwner, ResourceObserver(
                javaClass.simpleName,
                hideLoading = { binding.progressBar.visibility = View.GONE },
                showLoading = { binding.progressBar.visibility = View.VISIBLE },
                onSuccess = { universes: List<Universe> ->
                    //TODO refactor
                    context?.let {
                        val adapter = UniversesAdapter(it, universes) { universeId ->
                            //TODO filter fighters by universeId
                        }
                        binding.universesList.layoutManager = LinearLayoutManager(
                            activity,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        binding.universesList.adapter = adapter
                    }


                    //TODO refactor
                    viewModel.getFighters().observe(
                        viewLifecycleOwner, ResourceObserver(
                            javaClass.simpleName,
                            hideLoading = { binding.progressBar.visibility = View.GONE },
                            showLoading = { binding.progressBar.visibility = View.VISIBLE },
                            onSuccess = { fighters ->


                            },
                            onError = { errorMessage ->
                                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                            }
                        )
                    )

                },
                onError = { errorMessage ->
                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                }
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.filters_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filters) {
            val directions = FightersFragmentDirections.navigateToFilters()
            findNavController().navigate(directions)
        }
        return true
    }

}