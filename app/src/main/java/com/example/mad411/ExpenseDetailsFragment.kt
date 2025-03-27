package com.example.mad411

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ExpenseDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_expense_details, container, false)

        //retrive data
        val expenseName = arguments?.getString("expenseName") ?: "No Name"
        val expenseAmount = arguments?.getString("expenseAmount") ?: "0.00"

        view.findViewById<TextView(R.id.expenseNameText).text = expenseName
        view.findViewById<TextView(R.id.expenseAmountText).text = expenseAmount

        return view
    }


}