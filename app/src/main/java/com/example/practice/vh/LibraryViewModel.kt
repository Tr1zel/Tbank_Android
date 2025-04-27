package com.example.practice.vh

import LibraryObject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.db.BaseDao
import com.example.practice.library.LibraryRepository
import kotlinx.coroutines.launch

class LibraryViewModel(private val LibraryDao: BaseDao) : ViewModel() {

    private val libraryRepository = LibraryRepository

    fun loadLibraryItems(onResult: (List<LibraryObject>) -> Unit) {
        viewModelScope.launch {
            val items = libraryRepository.getItems(LibraryDao).map { it as LibraryObject }
            onResult(items)
        }
    }
}

