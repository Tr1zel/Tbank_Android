package com.example.practice.library

import LibraryObject
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.fragments.ListElemFragment
import com.example.practice.R
import com.example.practice.fragments.ElemDetailFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ListElemFragment.OnLibraryItemClickListener {

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
                replace(R.id.list_container, ListElemFragment())
            }
        }
    }

    override fun onLibraryItemClick(item: LibraryObject) {
        val detailFragment = ElemDetailFragment.newInstance(item)
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

    override fun onAddNewItem() {
        val detailFragment = ElemDetailFragment.newInstance(null)
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
