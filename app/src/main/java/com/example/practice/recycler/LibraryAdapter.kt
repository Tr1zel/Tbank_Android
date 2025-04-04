package com.example.practice
import Library
import LibraryObject
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.practice.databinding.LibraryItemBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.vh.LibraryViewHolder

class LibraryAdapter(
    private val items: MutableList<LibraryObject>,
    val onItemClick: (LibraryObject) -> Unit ):
    RecyclerView.Adapter<LibraryViewHolder>() {
        val library = Library()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding = LibraryItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return LibraryViewHolder(binding, onItemClick)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }
}