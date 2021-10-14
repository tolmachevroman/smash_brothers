package com.koombea.smash.bros.views.fragments

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.koombea.smash.bros.viewmodels.FiltersViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.koombea.smash.bros.R
import com.koombea.smash.bros.data.models.Fighter
import com.koombea.smash.bros.databinding.FighterDetailsFragmentBinding
import com.koombea.smash.bros.views.activities.MainActivity
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class FighterDetailsFragment : Fragment() {

    private lateinit var binding: FighterDetailsFragmentBinding
    private val viewModel by viewModels<FiltersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FighterDetailsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.apply {
            val fighter = FighterDetailsFragmentArgs.fromBundle(this).fighter

            (activity as MainActivity).supportActionBar?.apply {
                title = fighter.name
                setDisplayHomeAsUpEnabled(true)
            }

            binding.name.text = fighter.name
            binding.universe.text = fighter.universe
            binding.description.text = fighter.description
            binding.price.text = "\$${fighter.price}"

            context?.resources?.apply {
                binding.downloads.text = String.format(
                    getString(R.string.fighters_adapter_downloads),
                    NumberFormat.getNumberInstance(Locale.US).format(fighter.downloads.toInt())
                )
            }

            for (i in 0 until fighter.rate) {
                (binding.stars.getChildAt(i) as ImageView)
                    .setImageResource(R.drawable.ic_rating_selected)
            }

            Picasso.get()
                .load(fighter.imageURL)
                .into(binding.image)
        }
    }
}