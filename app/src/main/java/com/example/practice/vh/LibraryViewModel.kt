package com.example.practice.vh

import LibraryObject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.library.LibraryRepository
import kotlinx.coroutines.launch

class LibraryViewModel : ViewModel() {

    private val libraryRepository = LibraryRepository

    fun loadLibraryItems(onResult: (List<LibraryObject>) -> Unit) {
        viewModelScope.launch {
            val items = libraryRepository.getItems()
            onResult(items)
        }
    }
}

