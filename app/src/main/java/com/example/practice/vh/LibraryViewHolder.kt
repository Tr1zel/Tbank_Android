package com.example.practice.vh

import LibraryObject
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.databinding.LibraryItemBinding

class LibraryViewHolder(binding: LibraryItemBinding): RecyclerView.ViewHolder(binding.root) {
    private val title: TextView = binding.title
    private val idText: TextView = binding.id
    private val image: ImageView = binding.ivAvatar
    var AVAILABLE_ITEM_ALPHA  = 1.0f
    var TAKEN_ITEM_ALPHA = 0.3f
    var AVAILABLE_ITEM_ELEVATION = 10f
    var TAKEN_ITEM_ELEVATION  = 1f

    fun bind(item: LibraryObject, onItemClick: (LibraryObject) -> Unit) {
        title.text = item.title
        idText.text = "ID: ${item.id}"

        val resourceId = itemView.context.resources.getIdentifier(
            "o${item.id}", "drawable", itemView.context.packageName
        )
        image.setImageResource(resourceId)

        val alphaValue = if (item.access) AVAILABLE_ITEM_ALPHA  else TAKEN_ITEM_ALPHA
        itemView.alpha = alphaValue
        itemView.elevation = if (item.access) AVAILABLE_ITEM_ELEVATION else TAKEN_ITEM_ELEVATION

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}
