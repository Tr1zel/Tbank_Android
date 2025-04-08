package com.example.practice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    private var position: Int = -1
//    private var existingText: String? = null
//    private var existingAuthor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_info_activity)

        val titleview = findViewById<EditText>(R.id.TitleBookText)
        val authorview = findViewById<TextView>(R.id.item_title)
        val savebutton = findViewById<Button>(R.id.BookSaveButton)

        val info = intent.getStringExtra("info")
        val existingAuthor = intent.getStringExtra(AUTHOR_TEXT)
        position = intent.getIntExtra(POSITION, -1)
//        val info = intent.getStringExtra("info")
        info?.let {
            authorview.setText(it)
        }
//        existingAuthor?.let {
//            authorview.setText(it)
//        }

        savebutton.setOnClickListener {
            val newTitleText = titleview.text.toString()
            val newAuthorText = authorview.text.toString()
            val resultIntent = Intent().apply {
                putExtra(TITLE_TEXT, newTitleText)
                putExtra(AUTHOR_TEXT, newAuthorText)
                putExtra(POSITION, position)
            }
            setResult(RESULT_OK, resultIntent)
            finish() // Закрываем activity только после нажатия кнопки
        }

        //Устанавливаем результат, если нажата кнопка "Назад"
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
//                .putExtra(EXTRA_DATA, data)
        }
    }
}