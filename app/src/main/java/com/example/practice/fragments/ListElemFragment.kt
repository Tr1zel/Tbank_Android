package com.example.practice.fragments

import LibraryObject
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.example.practice.R
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice.LibraryAdapter
import com.example.practice.databinding.FragmentListElemBinding
import com.example.practice.db.MyApplication
import com.example.practice.library.LibraryRepository
import com.example.practice.vh.LibraryViewModel
import com.example.practice.vh.LibraryViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.withContext

class ListElemFragment : Fragment() {

    private var _binding: FragmentListElemBinding? = null
    private val binding get() = _binding!!
    lateinit var shimmerLayout: ShimmerFrameLayout
    private lateinit var viewModel: LibraryViewModel

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
        val application = requireActivity().application as MyApplication
        val baseDao = application.baseDao
        val factory = LibraryViewModelFactory(baseDao)
        shimmerLayout = binding.shimmerViewContainer
        shimmerLayout.startShimmer()

        viewModel = ViewModelProvider(this, factory).get(LibraryViewModel::class.java)

        viewModel.loadLibraryItems { items ->
            adapter = LibraryAdapter(items) { item ->
                listener?.onLibraryItemClick(item)
            }
            binding.recyclerView.adapter = adapter
            binding.recyclerView.visibility = View.VISIBLE

            shimmerLayout.stopShimmer()
            shimmerLayout.visibility = View.GONE
        }


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
