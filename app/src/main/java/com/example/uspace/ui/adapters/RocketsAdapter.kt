package com.example.uspace.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uspace.databinding.ItemRocketBinding
import com.example.uspace.ui.models.Rocket

class RocketsAdapter: ListAdapter<Rocket, RocketsAdapter.RocketViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Rocket>() {
        override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
            return oldItem == newItem
        }

    }

    class RocketViewHolder(private val binding: ItemRocketBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): RocketViewHolder {
                val binding = ItemRocketBinding.inflate(LayoutInflater.from(parent.context));
                return RocketViewHolder(binding);
            }
        }

        fun bind(rocket: Rocket) {
            binding.rocket = rocket
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        return RocketViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}