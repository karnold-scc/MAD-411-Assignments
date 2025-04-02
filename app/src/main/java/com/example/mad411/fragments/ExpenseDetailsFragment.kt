package com.example.mad411.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.mad411.R


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
        val expenseCurrency = arguments?.getString("expenseCurrency") ?: "CAD"
        val expenseCostFinal = arguments?.getDouble("expenseConverted") ?: 1.23
        Log.d("inDetails", expenseCostFinal.toString())
        Log.d("inDetails", expenseCurrency.toString())

        view.findViewById<TextView>(R.id.expenseNameDetails).text = expenseName
        view.findViewById<TextView>(R.id.expenseAmountDetails).text = expenseAmount + " CAD"
        view.findViewById<TextView>(R.id.expenseCurrency).text = "CURRENCY: " + expenseCurrency
        view.findViewById<TextView>(R.id.expenseConvertedCost).text = "Converted Cost: " + expenseCostFinal

        val backButton : Button = view.findViewById(R.id.backButton)
        backButton.setOnClickListener { findNavController().navigate(R.id.action_expenseDetailsFragment_to_mainFragment) }

        return view
    }


}