package com.koombea.smash.bros.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.koombea.smash.bros.R
import com.koombea.smash.bros.databinding.WalkthroughFragmentBinding
import com.koombea.smash.bros.views.adapters.WalkthroughPagerAdapter
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
        val viewPager = binding.pager
        val dots = binding.dots
        context?.apply {
            viewPager.adapter = WalkthroughPagerAdapter(this)
            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) = Unit

                override fun onPageSelected(position: Int) {
                    binding.dot1.setImageResource(R.drawable.ic_dot_unselected)
                    binding.dot2.setImageResource(R.drawable.ic_dot_unselected)
                    binding.dot3.setImageResource(R.drawable.ic_dot_unselected)
                    binding.fab.visibility = View.INVISIBLE

                    when (position) {
                        0 -> binding.dot1.setImageResource(R.drawable.ic_dot_selected)
                        1 -> binding.dot2.setImageResource(R.drawable.ic_dot_selected)
                        else -> {
                            binding.dot3.setImageResource(R.drawable.ic_dot_selected)
                            binding.fab.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onPageScrollStateChanged(state: Int) = Unit

            })
        }

        binding.fab.setOnClickListener {
            val directions = WalkthroughFragmentDirections.navigateToFighters()
            findNavController().navigate(directions)
        }
    }
}