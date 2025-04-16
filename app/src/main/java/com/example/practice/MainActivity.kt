package com.example.practice.library

import Book
import Disk
import Journal
import LibraryObject
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.fragments.List_elem_Fragment
import com.example.practice.LibraryAdapter
import com.example.practice.R
import com.example.practice.fragments.elem_detail_Fragments
import com.example.practice.vh.LibraryViewHolder

class MainActivity : AppCompatActivity(), List_elem_Fragment.OnLibraryItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private var isLandscape = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (savedInstanceState == null)
        {
            supportFragmentManager.commit  {
                replace(R.id.list_container, List_elem_Fragment())
            }
        }

    }

    override fun onLibraryItemClick(item: LibraryObject) {
        val detailFragment = elem_detail_Fragments.newInstance(item)

        if (isLandscape) {
            supportFragmentManager.commit {
                replace(R.id.detail_container, detailFragment)
            }
        } else {
            supportFragmentManager.commit {
                replace(R.id.list_container, detailFragment)
                addToBackStack(null)
            }
        }    }

    override fun onAddNewItem() {
        val detailFragment = elem_detail_Fragments.newInstance(null)
        if (isLandscape) {
            supportFragmentManager.commit {
                replace(R.id.detail_container, detailFragment)
            }
        } else {
            supportFragmentManager.commit {
                replace(R.id.list_container, detailFragment)
                addToBackStack(null)
            }
        }
    }
}