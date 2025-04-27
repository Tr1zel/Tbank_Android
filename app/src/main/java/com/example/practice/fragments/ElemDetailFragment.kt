package com.example.practice.fragments

import Book
import Disk
import Journal
import LibraryObject
import ReleaseMonthJournal
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.practice.R
import com.example.practice.databinding.FragmentLibraryDetailBinding
import com.example.practice.db.BaseDao
import com.example.practice.db.MyApplication
import com.example.practice.library.LibraryRepository
import com.example.practice.vh.LibraryViewModel
import com.example.practice.vh.LibraryViewModelFactory
import kotlinx.coroutines.launch

class ElemDetailFragment : Fragment() {

    private var _binding: FragmentLibraryDetailBinding? = null
    private val binding get() = _binding!!
    private var item: LibraryObject? = null

    private lateinit var viewModel: LibraryViewModel

    companion object {
        private const val ARG_ITEM = "arg_item"

        fun newInstance(libraryItem: LibraryObject?): Fragment {
            val fragment = ElemDetailFragment()
            val args = Bundle()
            args.putSerializable(ARG_ITEM, libraryItem)
            fragment.arguments = args
            return fragment
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        item = arguments?.getSerializable(ARG_ITEM, LibraryObject::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val application = requireActivity().application as MyApplication
        val baseDao = application.baseDao
        val factory = LibraryViewModelFactory(baseDao)

        // Создайте LibraryViewModel, используя ViewModelProvider
        viewModel = ViewModelProvider(this, factory).get(LibraryViewModel::class.java)
        if (item != null) {
            showItemDetails()
        } else {
            showAddForm()
        }
    }

    private fun showItemDetails() {
        binding.groupAddForm.isVisible = false
        binding.groupAddForm.visibility = View.GONE

        binding.textViewDetail.text = item!!.showInfo()
    }

    private fun showAddForm() {
        binding.groupDetails.visibility = View.GONE
        binding.groupAddForm.visibility = View.VISIBLE
        val types = resources.getStringArray(R.array.types_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerType.adapter = adapter
        val application = requireActivity().application as MyApplication
        val baseDao = application.baseDao

        binding.buttonAdd.setOnClickListener {
            val name = binding.editName.text.toString()
            val type = binding.spinnerType.selectedItem.toString()

            val newItem = when (type) {
                "Книга" -> Book(
                    id = LibraryRepository.getNextId(),
                    title = name,
                    pages = 100,
                    author = "Автор"
                )

                "Диск" -> Disk(
                    id = LibraryRepository.getNextId(),
                    title = name,
                    typeDisk = "CD"
                )

                else -> Journal(
                    id = LibraryRepository.getNextId(),
                    title = name,
                    numMonthIssue = 1,
                    numIssue = 1
                )
            }

            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    LibraryRepository.addItem(newItem, baseDao)
                    parentFragmentManager.setFragmentResult("item_added", Bundle())
                    requireActivity().supportFragmentManager.popBackStack()
                } catch (e: Exception) {
                    Toast.makeText(
                        requireContext(),
                        "Ошибка при добавлении ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}