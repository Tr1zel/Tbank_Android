package com.example.practice.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Items")
data class ItemsDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "type")
    var type: String, // "Book", "Disk", "journal"
    @ColumnInfo(name = "access")
    var access: Boolean = true,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "pages")
    val pages: Int? = null,
    @ColumnInfo(name = "author")
    var author: String? = null,
    @ColumnInfo(name = "numIssue")
    val numIssue: Int? = null,
    @ColumnInfo(name = "numMonth")
    val numMonthIssue: Int? = null,
    @ColumnInfo(name = "typeDisk")
    val typeDisk: String? = null,
)
