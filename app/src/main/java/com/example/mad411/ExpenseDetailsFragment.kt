package com.example.mad411

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class ExpenseDetailsFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_expense_details, container, false)

        //retrive data
        val expenseName = arguments?.getString("expenseName") ?: "No Name"
        val expenseAmount = arguments?.getString("expenseAmount") ?: "0.00"
        val expenseCurrency = arguments?.getString("expenseCurrency") ?: "CAD"
        val expenseCostFinal = arguments?.getDouble("expenseConverted") ?: 0.00

        view.findViewById<TextView>(R.id.expenseNameDetails).text = expenseName
        view.findViewById<TextView>(R.id.expenseAmountDetails).text = expenseAmount
        view.findViewById<TextView>(R.id.expenseCurrency).text = "CURRENCY: " + expenseCurrency
        view.findViewById<TextView>(R.id.expenseAmountDetails).text = "Converted Cost: " + expenseAmount

        val backButton : Button = view.findViewById(R.id.backButton)
        backButton.setOnClickListener { findNavController().navigate(R.id.action_expenseDetailsFragment_to_mainFragment) }

        return view
    }


}