package com.example.mad411

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil
import kotlin.math.exp





class MainActivity : AppCompatActivity() {

    //Recycler View
    private lateinit var expenseRecyclerView: RecyclerView
    private lateinit var expenseNameEditText: EditText
    private lateinit var expenseAmountEditText: EditText
    private lateinit var expenseDateEditText: EditText
    private lateinit var addButton: Button
    private lateinit var adapter: CustomAdapter
    private lateinit var tipButton: Button
    private lateinit var footer : FooterFragment
    private lateinit var header : HeaderFragment

    val expenses = mutableListOf<Expense>()

    //edit texts + buttons


    //inside recycler view
//    val expenseText = findViewById<TextView>(R.id.expenseNameText)
//    val expenseAmountText = findViewById<TextView>(R.id.expenseAmountText)
//    val deleteButton = findViewById<Button>(R.id.deleteButton)

    //Linter is mean to me

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //EVERYTHING FROM HERE DOWN WILL GET MOVEd
        Log.d("Lifecycle", "onCreate was called successfully.")
//
//        expenseNameEditText = findViewById<EditText>(R.id.expenseNameEditText)
//        expenseAmountEditText = findViewById<EditText>(R.id.amountEditText)
//        expenseDateEditText = findViewById<EditText>(R.id.dateEditText)
//        addButton = findViewById<Button>(R.id.addExpenseButton)
//        expenseRecyclerView = findViewById(R.id.expenseRecyclerView)
//        tipButton = findViewById(R.id.financialTipsButton)
//
//        expenseRecyclerView.layoutManager = LinearLayoutManager(this)
//
//        //set adapter
//        adapter = CustomAdapter(expenses,
//            { position -> deleteItem(position) },
//            {position -> showExpenseDetails(position)})
//        expenseRecyclerView.adapter = adapter

//        //load fragments
        header = HeaderFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameHeader, header)
            .commit()
        footer = FooterFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameFooter, footer)
            .commit()
        updateFooter()
//
//
//        addButton.setOnClickListener{addExpense()}
//        tipButton.setOnClickListener{startFinancialTips()}
    }


    //call footer update function
    private fun updateFooter(){

        val total = expenses.sumByDouble { it.amount.toDouble() }
        footer.updateTotal(total)
    }



}