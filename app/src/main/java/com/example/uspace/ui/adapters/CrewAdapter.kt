package com.example.uspace.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uspace.databinding.ItemCrewBinding
import com.example.uspace.ui.models.Crew
import javax.inject.Inject

class CrewAdapter @Inject constructor(val clickListener: CrewClickListener): ListAdapter<Crew, CrewAdapter.CrewViewHolder>(DiffCallback()) {

    class CrewViewHolder(val binding: ItemCrewBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val binding = ItemCrewBinding.inflate(LayoutInflater.from(parent.context))
        return CrewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        val crew = getItem(position)
        holder.binding.crew = crew
        holder.binding.clickListener = clickListener
    }
}

class DiffCallback : ItemCallback<Crew>() {
    override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
        return oldItem == newItem
    }
}

class CrewClickListener(val callback: (crew: Crew) -> Unit) {
    fun onClick(crew: Crew) = callback(crew)
}