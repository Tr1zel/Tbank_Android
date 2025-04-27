package com.example.practice.library

import Book
import Disk
import Journal
import Library
import LibraryObject
import ReleaseMonthJournal
import com.example.practice.db.BaseDao
import com.example.practice.db.ItemsDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

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

    suspend fun getItems(libraryDao: BaseDao): List<Any> {
        return withContext(Dispatchers.IO) {
            libraryDao.getAll().map { dbItem ->
                when (dbItem.type) {
                    "Book" -> Book(
                        id = dbItem.id,
                        title = dbItem.title,
                        pages = dbItem.pages ?: 0,
                        author = dbItem.author ?: ""
                    )

                    "Disk" -> Disk(
                        id = dbItem.id,
                        title = dbItem.title,
                        typeDisk = dbItem.typeDisk ?: ""
                    )

                    "Journal" -> Journal(
                        id = dbItem.id,
                        title = dbItem.title,
                        numIssue = dbItem.numIssue ?: 0,
                        numMonthIssue = ReleaseMonthJournal.JANUARY.numMonthIssue // ReleaseMonthJournal.JANUARY.numMonthIssue // Или как-то получить номер месяца
                    )

                    else -> throw IllegalArgumentException("Неизвестный тип объекта: ${dbItem.type}")
                }
            }
        }
    }
    suspend fun addItem(item: LibraryObject, libraryDao: BaseDao) {
        withContext(Dispatchers.IO) {
            val newItemDB = when (item) {
                is Book -> ItemsDB(
                    id = getNextId(),
                    title = item.title,
                    pages = 100,
                    author = item.author,
                    type = "Book"
                )

                is Disk -> ItemsDB(
                    id = getNextId(),
                    title = item.title,
                    typeDisk = item.typeDisk,
                    type = "Disk"
                )

                is Journal -> ItemsDB(
                    id = getNextId(),
                    title = item.title,
                    numMonthIssue = ReleaseMonthJournal.JANUARY.numMonthIssue,
                    numIssue = item.numIssue,
                    type = "Journal"
                )
                else -> throw IllegalArgumentException("Неизвестный тип объекта: ${item::class.java}")
            }
            libraryDao.insert(newItemDB)
            delay(1000)
            items.add(item)
        }
    }

    fun getNextId(): Int = nextId++
}