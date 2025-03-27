package com.example.mad411

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController

class ExpenseDetails : AppCompatActivity() {
    //View Components or whatever they're called
    private lateinit var expenseName: TextView
    private lateinit var expenseAmount: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_expense_details)

        //Retrieve data from main activity
        val name = intent.getStringExtra("expense_name")
        val amount = intent.getStringExtra("expense_amount")

        //Initialize views
        expenseName = findViewById(R.id.expenseNameDetails)
        expenseAmount = findViewById(R.id.expenseAmountDetails)
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener{goBack()}
        //I need to start giving better IDs -note to self

        //now set the views to the data from the main activity
        expenseName.text = "Expense Name: $name"
        expenseAmount.text = "Expense Amount: $$amount"
    }

    fun goBack(){

    }

}