package com.example.practice.library


import Book
import Disk
import Journal
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.LibraryAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var libraryadapter: LibraryAdapter

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
            item.access = !item.access
            Toast.makeText(this, "Элемент с Id: ${item.id}", Toast.LENGTH_SHORT).show()
            libraryadapter.notifyDataSetChanged()
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = libraryadapter
    }
}