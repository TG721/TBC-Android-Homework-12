package com.example.tbc_homework_12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_homework_12.databinding.ItemBinding

class ItemAdapter(val Items: MutableList<Item>, val recyclerViewInterface: RecyclerViewInterface) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.imageView.setOnClickListener{
                if (recyclerViewInterface != null) {
                    val pos = bindingAdapterPosition
                    if (pos != RecyclerView.NO_POSITION)
                        recyclerViewInterface.onItemClick(pos)
                }
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
        holder.binding.apply {
            imageView.setImageResource(Items[position].image)

        }
    }

    override fun getItemCount(): Int {
        return Items.size
    }
}