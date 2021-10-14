package com.koombea.smash.bros.views.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.koombea.smash.bros.R
import com.koombea.smash.bros.data.models.Universe
import com.koombea.smash.bros.databinding.FightersFragmentBinding
import com.koombea.smash.bros.utils.ResourceObserver
import com.koombea.smash.bros.viewmodels.FightersViewModel
import com.koombea.smash.bros.views.activities.MainActivity
import com.koombea.smash.bros.views.adapters.UniversesAdapter
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.LinearLayoutManager
import com.koombea.smash.bros.data.models.Fighter
import com.koombea.smash.bros.views.adapters.FightersAdapter


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
                ::hideLoading,
                ::showLoading,
                ::showUniverses,
                ::showErrorMessage
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

    private fun showUniverses(universes: List<Universe>) {
        context?.apply {
            val currentUniverse = viewModel.getCurrentUniverse()
            val adapter = UniversesAdapter(this, universes, currentUniverse) { universe ->
                //Set newly selected universe
                viewModel.setCurrentUniverse(universe)

                //Get fighters from current universe
                viewModel.getFighters(universe).observe(
                    viewLifecycleOwner, ResourceObserver(
                        javaClass.simpleName,
                        ::hideLoading,
                        ::showLoading,
                        ::showFighters,
                        ::showErrorMessage
                    )
                )
            }
            binding.universesList.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            binding.universesList.adapter = adapter

            //Get fighters from last selected universe
            val position = universes.indexOfFirst { u -> u.name == currentUniverse }
            if (position == 0) {
                binding.universesList.scrollToPosition(position)
            } else {
                binding.universesList.scrollToPosition(position - 1)
            }
            viewModel.getFighters(currentUniverse).observe(
                viewLifecycleOwner, ResourceObserver(
                    javaClass.simpleName,
                    ::hideLoading,
                    ::showLoading,
                    ::showFighters,
                    ::showErrorMessage
                )
            )
        }
    }

    private fun showFighters(fighters: List<Fighter>) {
        context?.apply {
            val adapter = FightersAdapter(this, fighters) { fighter ->
                val directions = FightersFragmentDirections.navigateToFighterDetails(fighter)
                findNavController().navigate(directions)
            }
            binding.fightersList.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            binding.fightersList.addItemDecoration(
                DividerItemDecoration(
                    this,
                    LinearLayoutManager.VERTICAL
                )
            )
            binding.fightersList.adapter = adapter
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showErrorMessage(errorMessage: String?) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }
}