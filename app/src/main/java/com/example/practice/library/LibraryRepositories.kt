package com.example.practice.library

import Book
import Disk
import Journal
import Library
import LibraryObject
import kotlinx.coroutines.delay

object LibraryRepository {
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
    private var nextId = 1

    fun getItems(): List<LibraryObject> = items

    suspend fun addItem(item: LibraryObject) {
        delay(1000)
        items.add(item)
    }

    fun getNextId(): Int = nextId++
}