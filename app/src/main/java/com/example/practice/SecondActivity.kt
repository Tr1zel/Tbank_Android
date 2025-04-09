package com.example.practice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.practice.vh.LibraryViewHolder

class SecondActivity: AppCompatActivity() {

    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_info_activity)

        val authorview = findViewById<TextView>(R.id.item_title)
        val savebutton = findViewById<Button>(R.id.BookSaveButton)
//        val resourceId =
        val info = intent.getStringExtra("info")
        position = intent.getIntExtra(POSITION, -1)

        info?.let {
            authorview.setText(it)
        }


        savebutton.setOnClickListener {
            val newAuthorText = authorview.text.toString()
            val resultIntent = Intent().apply {
                putExtra(AUTHOR_TEXT, newAuthorText)
                putExtra(POSITION, position)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                setResult(RESULT_CANCELED)
                finish()
            }
        })

    }


    companion object {
        const val TITLE_TEXT = "titleText"
        const val AUTHOR_TEXT = "authorText"
        const val POSITION = "position"

        fun createIntent(context: Context): Intent {
            return Intent(context, SecondActivity::class.java)
        }
    }
}