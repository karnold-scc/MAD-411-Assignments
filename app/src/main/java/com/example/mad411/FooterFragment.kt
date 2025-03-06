package com.example.mad411

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

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
        view?.findViewById<TextView>(R.id.footerText)?.text = "Total Expenses: $${total}"
    }
}