package com.example.practice.vh

import Book
import Disk
import Journal
import LibraryObject
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.LibraryAdapter
import com.example.practice.R
import com.example.practice.SecondActivity
import com.example.practice.databinding.LibraryItemBinding
import com.example.practice.library.MainActivity

class LibraryViewHolder(binding: LibraryItemBinding): RecyclerView.ViewHolder(binding.root) {
    private val title: TextView = binding.title
    private val idText: TextView = binding.id
    private val image: ImageView = binding.ivAvatar

    fun bind(item: LibraryObject, onItemClick: (LibraryObject) -> Unit) {
        title.text = item.title

        val resourceId = itemView.context.resources.getIdentifier(
            "book", "drawable", itemView.context.packageName
        )
        if (resourceId != 0) {
            image.setImageResource(resourceId)
        } else {
            image.setImageResource(R.drawable.ic_launcher_foreground) // или запасная картинка
        }
        itemView.setOnClickListener {
            onItemClick(item)

//            val intent = SecondActivity.createIntent(activityContext).apply {
//                putExtra("info", item.showInfo())
////                putExtra(SecondActivity.AUTHOR_TEXT, when (item){
////                    is Book -> item.author //Нужно проверить, есть ли автор у journal/disk
////                    else -> ""
////                })
////                when(item){
////                    is Book -> item.title
////                    is Journal -> item.title
////                    is Disk -> item.title
////                    else -> {""}
////                }
//                putExtra(SecondActivity.POSITION, LibraryAdapter(items, onItemClick, activityContext).libraryItems.indexOf(item) )   //.indexOf(item)) //Передаем позицию элемента
//            }
//
//            (activityContext as? MainActivity)?.startForResult?.launch(intent)

        }

    }
}

