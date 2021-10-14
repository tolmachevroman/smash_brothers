package com.koombea.smash.bros.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koombea.smash.bros.R
import com.koombea.smash.bros.data.models.Universe
import com.koombea.smash.bros.databinding.UniversesAdapterItemBinding

class UniversesAdapter(
    context: Context,
    private val data: List<Universe>,
    private var selectedId: String = "",
    private val onClickListener: ((String) -> Unit)
) : RecyclerView.Adapter<UniversesAdapter.ViewHolder>() {

    private val selectedColor = context.resources.getColor(R.color.blue_700)
    private val unselectedColor = context.resources.getColor(R.color.blue_500)

    class ViewHolder(binding: UniversesAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val button = binding.universeButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UniversesAdapterItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        changeButtonColor(holder, position)
        holder.button.text = data[position].name
        holder.button.setOnClickListener {
            selectedId = data[position].objectID
            onClickListener.invoke(selectedId)
            changeButtonColor(holder, position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun changeButtonColor(holder: ViewHolder, position: Int) {
        if (data[position].objectID == selectedId) {
            holder.button.setBackgroundColor(selectedColor)
        } else {
            holder.button.setBackgroundColor(unselectedColor)
        }
    }
}