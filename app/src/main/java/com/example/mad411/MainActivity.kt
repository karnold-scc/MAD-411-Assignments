package com.example.mad411

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {

    private lateinit var enterNameEditText: EditText
    private lateinit var showNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enterNameEditText = findViewById(R.id.enter_name_edit_text)
        showNameTextView = findViewById(R.id.show_name_text)
    }

    fun showName(view: View) {
        val name = enterNameEditText.text.toString()

        showNameTextView.text = "Hello, $name"
    }
}