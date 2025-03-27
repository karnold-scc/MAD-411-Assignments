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

data class Expense(val id: Int, val name: String, val amount: String){

}



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

    fun addExpense(){

        val expName = expenseNameEditText.text.toString()
        val amount = expenseAmountEditText.text.toString()


        //val expDate = expenseDateEditText.text.toString()
        if (expName.isNotEmpty() && amount.isNotEmpty()) {
            expenses.add(Expense((System.currentTimeMillis() / 1000).toInt(), expName, amount))
            adapter.notifyItemInserted(expenses.size -1 )
            updateFooter()
        }
        expenseNameEditText.setText(" ")
        expenseAmountEditText.setText(" ")
        expenseDateEditText.setText(" ")
    }

    //call footer update function
    private fun updateFooter(){
        val total = expenses.sumByDouble { it.amount.toDouble() }
        footer.updateTotal(total)
    }

    //button events
    private fun deleteItem (pos: Int){
        expenses.removeAt(pos)
        adapter.notifyItemRemoved(pos)
    }

    private fun showExpenseDetails(position: Int){
        val expense = expenses[position]
        val intent = Intent(this, ExpenseDetails::class.java)
        intent.putExtra("expense_name",expense.name)
        intent.putExtra("expense_amount", expense.amount)
        startActivity(intent)
    }

    private fun startFinancialTips(){
        //Investopedia is a funny word
        val intent = Intent(Intent.ACTION_VIEW, "https://www.investopedia.com/articles/younginvestors/08/eight-tips.asp".toUri())
        startActivity(intent)
    }

    //Lifecycle log stuff
    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart called successfully.")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume called successfully.")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause called successfully.")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop called successfully.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy called successfully.")
    }

}