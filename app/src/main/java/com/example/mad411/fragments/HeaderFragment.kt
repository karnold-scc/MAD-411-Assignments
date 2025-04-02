package com.example.mad411.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.mad411.R


class HeaderFragment : Fragment(R.layout.fragment_header) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access the header TextView
        val textViewHeader = view.findViewById<TextView>(R.id.textViewHeader)
        textViewHeader.text = "The Expense Tracker App"
    }
}