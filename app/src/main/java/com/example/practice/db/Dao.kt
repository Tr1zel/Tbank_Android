package com.example.practice.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BaseDao {

    @Insert
    suspend fun insert(item: ItemsDB): Long

    @Query("SELECT * FROM Items")
    suspend fun getAll(): List<ItemsDB>


}
