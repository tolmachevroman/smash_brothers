package com.koombea.smash.bros.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koombea.smash.bros.R
import com.koombea.smash.bros.data.models.Fighter
import com.koombea.smash.bros.databinding.FightersAdapterItemBinding
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class FightersAdapter(
    context: Context,
    private val data: List<Fighter>,
    private val onClickListener: ((Fighter) -> Unit)
) : RecyclerView.Adapter<FightersAdapter.ViewHolder>() {

    private val pricePlaceholder = context.resources.getString(R.string.fighters_adapter_price)
    private val ratePlaceholder = context.resources.getString(R.string.fighters_adapter_rate)
    private val downloadsPlaceholder =
        context.resources.getString(R.string.fighters_adapter_downloads)

    class ViewHolder(binding: FightersAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val layout = binding.root
        val image = binding.image
        val name = binding.name
        val universe = binding.universe
        val price = binding.price
        val rate = binding.rate
        val downloads = binding.downloads
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FightersAdapterItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].apply {
            holder.name.text = name
            holder.universe.text = universe
            holder.price.text = String.format(pricePlaceholder, price)
            holder.rate.text = String.format(ratePlaceholder, rate)
            holder.downloads.text = String.format(
                downloadsPlaceholder,
                NumberFormat.getNumberInstance(Locale.US).format(downloads)
            )

            Picasso.get()
                .load(imageURL)
                .into(holder.image)

            holder.layout.setOnClickListener {
                onClickListener.invoke(this)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}