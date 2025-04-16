package com.example.practice.fragments

import Library
import LibraryObject
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.LibraryAdapter
import com.example.practice.R
import com.example.practice.databinding.FragmentListElemBinding
import com.example.practice.databinding.LibraryItemBinding
import com.example.practice.library.LibraryRepository

class List_elem_Fragment : Fragment() {

    private var _binding: FragmentListElemBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: LibraryAdapter
    private var listener: OnLibraryItemClickListener? = null
    private var scrollPosition = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLibraryItemClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnLibraryItemClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListElemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager

        adapter = LibraryAdapter(LibraryRepository.getItems()) { item ->
            listener?.onLibraryItemClick(item)
        }

        binding.recyclerView.adapter = adapter

        // Кнопка "добавить"
        binding.fabAdd.setOnClickListener {
            listener?.onAddNewItem()
        }

        // Восстановим позицию скролла
        binding.recyclerView.scrollToPosition(scrollPosition)
    }

    override fun onPause() {
        super.onPause()
        val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
        scrollPosition = layoutManager.findFirstVisibleItemPosition()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnLibraryItemClickListener {
        fun onLibraryItemClick(item: LibraryObject)
        fun onAddNewItem()
    }
}
