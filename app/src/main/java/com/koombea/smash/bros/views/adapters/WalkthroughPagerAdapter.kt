package com.koombea.smash.bros.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.koombea.smash.bros.R
import com.koombea.smash.bros.databinding.WalkthroughPagerItemBinding

class WalkthroughPagerAdapter(private val context: Context) : PagerAdapter() {

    private lateinit var binding: WalkthroughPagerItemBinding
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private val images =
        arrayOf(R.mipmap.walkthrough_1, R.mipmap.walkthrough_2, R.mipmap.walkthrough_3)
    private val labels = arrayOf(
        R.string.walkthrough_label_1,
        R.string.walkthrough_label_2,
        R.string.walkthrough_label_3
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = WalkthroughPagerItemBinding.inflate(inflater)
        binding.image.setImageResource(images[position])
        binding.label.text = context.getText(labels[position])
        container.addView(binding.root)
        return binding.root
    }

    override fun isViewFromObject(
        arg0: View,
        arg1: Any
    ) = arg0 === arg1 as View

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        arg1: Any
    ) {
        container.removeView(arg1 as View)
    }

    override fun getCount(): Int = images.size
}