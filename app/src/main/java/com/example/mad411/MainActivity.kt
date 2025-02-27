package com.example.mad411

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil
import kotlin.math.exp

data class Expense(val name: String, val amount: String){

}



class MainActivity : AppCompatActivity() {

    //Recycler View
    private lateinit var expenseRecyclerView: RecyclerView
    private lateinit var expenseNameEditText: EditText
    private lateinit var expenseAmountEditText: EditText
    private lateinit var expenseDateEditText: EditText
    private lateinit var addButton: Button
    private lateinit var adapter: CustomAdapter

    val expenses = mutableListOf<Expense>()

    //edit texts + buttons


    //inside recycler view
//    val expenseText = findViewById<TextView>(R.id.expenseNameText)
//    val expenseAmountText = findViewById<TextView>(R.id.expenseAmountText)
//    val deleteButton = findViewById<Button>(R.id.deleteButton)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expenseNameEditText = findViewById<EditText>(R.id.expenseNameEditText)
        expenseAmountEditText = findViewById<EditText>(R.id.amountEditText)
        expenseDateEditText = findViewById<EditText>(R.id.dateEditText)
        addButton = findViewById<Button>(R.id.addExpenseButton)
        expenseRecyclerView = findViewById(R.id.expenseRecyclerView)

        expenseRecyclerView.layoutManager = LinearLayoutManager(this)

        //set adapter
        adapter = CustomAdapter(expenses){
            position -> deleteItem(position)
        }
        expenseRecyclerView.adapter = adapter

        addButton.setOnClickListener{addExpense()}
    }

    fun addExpense(){
        val expName = expenseNameEditText.text.toString()
        val amount = expenseAmountEditText.text.toString()


        //val expDate = expenseDateEditText.text.toString()
        if (expName.isNotEmpty() && amount.isNotEmpty()) {
            expenses.add(Expense(expName, amount))
            adapter.notifyItemInserted(expenses.size -1 )
        }
        expenseNameEditText.setText(" ")
        expenseAmountEditText.setText(" ")
        expenseDateEditText.setText(" ")
    }

    private fun deleteItem (pos: Int){
        expenses.removeAt(pos)
        adapter.notifyItemRemoved(pos)
    }

}