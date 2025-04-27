package com.example.practice.vh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practice.db.BaseDao

class LibraryViewModelFactory(private val baseDao: BaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LibraryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LibraryViewModel(baseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
