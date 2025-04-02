package com.example.mad411.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.mad411.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class FooterFragment : Fragment(R.layout.fragment_footer) {
    private var total = 0.0
   // private lateinit var footerText: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val footerText = view.findViewById<TextView>(R.id.footerText)
        footerText.text = "Total Expenses: $${total}"
    }

    //update expenses
    fun updateTotal(newTotal : Double){
        total = newTotal

        //UI Update
        val test = view?.findViewById<TextView>(R.id.footerText)?.text
        Log.d("inFooter", test.toString())
        view?.findViewById<TextView>(R.id.footerText)?.text = "Total Expenses: $${total}"
    }
}