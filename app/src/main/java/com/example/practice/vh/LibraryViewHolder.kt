package com.example.practice.vh

import Book
import Disk
import Journal
import LibraryObject
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.LibraryAdapter
import com.example.practice.R
import com.example.practice.databinding.LibraryItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class LibraryViewHolder(binding: LibraryItemBinding): RecyclerView.ViewHolder(binding.root) {
    private val title: TextView = binding.title
    private val image: ImageView = binding.ivAvatar

    fun bind(item: LibraryObject, onItemClick: (LibraryObject) -> Unit) {
        title.text = item.title
        val resourceId = when (item) {
            is Book -> R.drawable.book
            is Journal -> R.drawable.journal
            is Disk -> R.drawable.disk
            else -> R.drawable.ic_launcher_foreground
        }
        try {
            image.setImageResource(resourceId)
        } catch (e: Exception) {
            Log.e("ResourceBinding", "Error setting Image resource", e)
        }

        itemView.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                delay(Random.nextInt(100, 2000).toLong())
                val error = Random.nextInt(4) == 0

                if (error) {
                    Toast.makeText(
                        itemView.context,
                        "Ошибка при открытии",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    onItemClick(item)
                }
            }
        }
    }
}

