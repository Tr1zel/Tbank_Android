package com.example.practice
import Library
import LibraryObject
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LibraryAdapter(
    private val items: MutableList<LibraryObject>,
     val onItemClick: (LibraryObject) -> Unit
):
    RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>() {
        val library = Library()

    inner class LibraryViewHolder(binding: View): RecyclerView.ViewHolder(binding) {

        private val title: TextView = binding.findViewById(R.id.title)
        private val idText: TextView = binding.findViewById(R.id.id)
        private val image: ImageView = binding.findViewById(R.id.avatar)

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
//        val binding = LibraryItemBinding.inflate(LayoutInflater.from(parent.context))
        val binding = LayoutInflater.from(parent.context).inflate(R.layout.library_item, parent,false)
        return LibraryViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.bind(items[position])
    }
}