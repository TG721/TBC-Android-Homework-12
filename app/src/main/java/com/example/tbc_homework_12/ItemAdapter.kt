package com.example.tbc_homework_12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_homework_12.databinding.ItemBinding

class ItemAdapter(val Items: MutableList<Item>, val recyclerViewInterface: RecyclerViewInterface) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.imageView.setImageResource(item.image)
            binding.imageView.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION)
                    recyclerViewInterface.onItemClick(item)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(Items[position])
    }

    override fun getItemCount(): Int {
        return Items.size
    }
}