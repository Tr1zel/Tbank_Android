package com.example.practice
import Library
import LibraryObject
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.practice.databinding.LibraryItemBinding
import androidx.recyclerview.widget.RecyclerView

class LibraryAdapter(
    private val items: MutableList<LibraryObject>,
    private val onItemClick: (LibraryObject) -> Unit ):
    RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>() {
        val library = Library()

    inner class LibraryViewHolder(binding: LibraryItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val title: TextView = binding.title
        private val idText: TextView = binding.id
        private val image: ImageView = binding.ivAvatar

        fun bind(item: LibraryObject) {
            title.text = item.title
            idText.text = "ID: ${item.id}"

            val resourceId = itemView.context.resources.getIdentifier(
                "o${item.id}", "drawable", itemView.context.packageName
            )
            image.setImageResource(resourceId)

            val alphaValue = if (item.access) 1.0f else 0.3f
            itemView.alpha = alphaValue
            itemView.elevation = if (item.access) 10f else 1f

            itemView.setOnClickListener {
               onItemClick(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding = LibraryItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return LibraryViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.bind(items[position])
    }
}