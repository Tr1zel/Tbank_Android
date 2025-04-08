package com.example.practice
import Library
import LibraryObject
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.practice.databinding.LibraryItemBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.vh.LibraryViewHolder

class LibraryAdapter(
    val libraryItems: List<LibraryObject>,
    private val onItemClick: (LibraryObject) -> Unit,
) : RecyclerView.Adapter<LibraryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding = LibraryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val item = libraryItems[position]
        holder.bind(item, onItemClick) // Передаем контекст в bind
    }

    override fun getItemCount(): Int = libraryItems.size
}
