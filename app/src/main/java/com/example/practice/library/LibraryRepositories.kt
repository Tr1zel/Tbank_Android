package com.example.practice.library

import Book
import Disk
import Journal
import LibraryObject
import ReleaseMonthJournal
import com.example.practice.db.BaseDao
import com.example.practice.db.ItemType
import com.example.practice.db.ItemsDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LibraryRepository(private val libraryDao: BaseDao) {

    suspend fun getItems():List<LibraryObject> {
        delay(1000)
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

    fun LibraryObject.toDb(): ItemsDB = when (this) {
        is Book -> ItemsDB(
            id = this.id,
            type = ItemType.BOOK.rawValue,
            title = this.title,
            access = this.access,
            pages = this.pages,
            author = this.author
        )

        is Journal -> ItemsDB(
            id = this.id,
            type = ItemType.JOURNAL.rawValue,
            title = this.title,
            access = this.access,
            numIssue = this.numIssue,
            numMonthIssue = this.numMonthIssue
        )

        is Disk -> ItemsDB(
            id = this.id,
            type = ItemType.DISK.rawValue,
            title = this.title,
            access = this.access,
            typeDisk = this.typeDisk
        )

        else -> throw IllegalArgumentException("Неизвестный тип объекта: ${ItemsDB::class.java}")
    }

    fun ItemsDB.toLibraryObject(): LibraryObject = when (type) {
        "Book" -> Book(
            id = this.id,
            title = this.title,
            pages = this.pages ?: 0,
            author = this.author ?: ""
        ).also { it.access = this.access }

        "Journal" -> Journal(
            id = this.id,
            title = this.title,
            numIssue = this.numIssue ?: 0,
            numMonthIssue = this.numMonthIssue ?: 1
        ).also { it.access = this.access }

        "Disk" -> Disk(
            id = this.id,
            title = this.title,
            typeDisk = this.typeDisk ?: "CD"
        ).also { it.access = this.access }

        else -> throw IllegalArgumentException("Неизвестный тип: $type")
    }

    fun LibraryObject.toDB(): ItemsDB = when (this) {
        is Book -> ItemsDB(
            id = this.id,
            type = ItemType.BOOK.rawValue,
            title = this.title,
            access = this.access,
            pages = this.pages,
            author = this.author
        )

        is Journal -> ItemsDB(
            id = this.id,
            type = ItemType.JOURNAL.rawValue,
            title = this.title,
            access = this.access,
            numIssue = this.numIssue,
            numMonthIssue = this.numMonthIssue
        )

        is Disk -> ItemsDB(
            id = this.id,
            type = ItemType.DISK.rawValue,
            title = this.title,
            access = this.access,
            typeDisk = this.typeDisk
        )

        else -> throw IllegalArgumentException("Неизвестный тип: $this")
    }


    suspend fun addItem(item: LibraryObject, dao: BaseDao): LibraryObject {
        delay(1000)
        val generateId = dao.insert(item.toDB())
        val savedItem = item.   copyWithId(item, generateId.toInt())
        return savedItem
    }

}