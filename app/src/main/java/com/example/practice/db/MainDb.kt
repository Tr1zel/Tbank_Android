package com.example.practice.db

import android.app.Application
import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [ItemsDB::class], version = 1)
abstract class MainDb: RoomDatabase() {
    abstract fun getDao(): BaseDao

    companion object {
        fun initDb(context: Context): MainDb {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "Library.db"
            ).build()
        }
    }
}


class MyApplication : Application() {
    lateinit var database: MainDb
    lateinit var baseDao: BaseDao

    override fun onCreate() {
        super.onCreate()
        database = MainDb.initDb(applicationContext)
        baseDao = database.getDao() // Используем метод getDao()
    }
}