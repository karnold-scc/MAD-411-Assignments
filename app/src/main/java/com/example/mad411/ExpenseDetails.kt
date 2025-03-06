package com.example.mad411

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ExpenseDetails : AppCompatActivity() {
    //View Components or whatever they're called
    private lateinit var expenseName: TextView
    private lateinit var expenseAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_expense_details)

        //Retrieve data from main activity
        val name = intent.getStringExtra("expense_name")
        val amount = intent.getStringExtra("expense_amount")

        //Initialize views
        expenseName = findViewById(R.id.expenseNameDetails)
        expenseAmount = findViewById(R.id.expenseAmountDetails) //I need to start giving better IDs -note to self
        //now set the views to the data from the main activity
        expenseName.text = "Expense Name: $name"
        expenseAmount.text = "Expense Amount: $$amount"
    }
}