package com.example.practice.library

import Book
import Disk
import Journal
import android.app.Activity
import android.content.Intent
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.LibraryAdapter
import com.example.practice.R
import com.example.practice.SecondActivity
import com.example.practice.vh.LibraryViewHolder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var libraryadapter: LibraryAdapter

//    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val data: Intent? = result.data
//            val title = data?.getStringExtra(SecondActivity.TITLE_TEXT)
//            val author = data?.getStringExtra(SecondActivity.AUTHOR_TEXT)
//            val position = data?.getIntExtra(SecondActivity.POSITION, -1)
//
//            if (title != null && author != null && position != -1) {
//                if (position != null) {
//                    items[position].title = title
//                }
//                if (position != null) {
//                    libraryadapter.notifyItemChanged(position)
//                }
//            }
//        }
//    }


    private val items = mutableListOf(
        Book(id = 11,"Гарри Поттер и узник Азкабана",620,"Джоан Роулинг"),
        Book(id = 12, "Унесенные ветром", 270, "Маргарет Митчелл"),
        Book(id = 13, "Зеленая миля", 1000, "Стивен Кинг"),
        Book(id = 14, "Волшебство на котлин", 999, "Tbank"),
        Journal(id = 21, "Известия Москвы", 12345, 1),
        Journal(id = 22, "СпидИнфо", 666, 11),
        Journal(id = 23, "Комсомольская правда",9999, 5),
        Disk(id = 31, "Рэмбо 2001",   "CD"),
        Disk(id = 32,  "Контер страйк 1.6",  "DVD"),
        Disk(id = 33, "Сборник фильмов Гарри Поттер", "DVD"),
        Disk(id = 34, "GTA 5", "DVD")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        libraryadapter = LibraryAdapter(items) { item ->
            val intent = SecondActivity.createIntent(this)
            intent.putExtra("info", item.showInfo())
            intent.putExtra("id", item.id)
            startActivity(intent)
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = libraryadapter


    }

//    companion object {
//        const val TITLE_TEXT = "titleText"
//        const val AUTHOR_TEXT = "authorText"
//        const val POSITION = "position"
//    }
}