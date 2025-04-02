package com.example.mad411.unused

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mad411.R

class ExpenseDetails : AppCompatActivity() {
    //View Components or whatever they're called
    private lateinit var expenseName: TextView
    private lateinit var expenseAmount: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_expense_details)

    }

    fun goBack(){

    }

}