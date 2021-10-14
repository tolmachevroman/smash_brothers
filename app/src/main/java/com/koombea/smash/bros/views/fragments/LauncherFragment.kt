package com.koombea.smash.bros.views.fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.koombea.smash.bros.databinding.LauncherFragmentBinding
import com.koombea.smash.bros.views.activities.MainActivity
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
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        (activity as MainActivity).supportActionBar?.apply {
            hide()
        }

        // TODO check if launched for the 1st time, or skip

        //navigate to Walkthrough after 2 seconds
        view.postDelayed({
            val directions = LauncherFragmentDirections.navigateToWalkthrough()
            findNavController().navigate(directions)
        }, 2000)
    }

}