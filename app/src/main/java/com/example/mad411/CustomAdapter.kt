package com.example.mad411

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val dataSet: List<Expense>, private val listener: ExpenseItemListener) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */

    interface ExpenseItemListener{
        fun onEditClick(expense: Expense)
        fun onDeleteClick(expense: Expense)
    }

    inner class ExpenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val expenseText: TextView = view.findViewById(R.id.expenseNameText)
        private val expenseAmountText: TextView = view.findViewById(R.id.expenseAmountText)
        private val deleteButton : Button = view.findViewById(R.id.deleteButton)
        private val detailButton: Button = view.findViewById(R.id.detailButton)
        private val editButton: Button = view.findViewById(R.id.editButton)

        fun bind(expense: Expense){
            expenseText.text = expense.name
            expenseAmountText.text = expense.amount

            editButton.setOnClickListener{
                listener.onEditClick(expense)
            }

            deleteButton.setOnClickListener {
                listener.onDeleteClick(expense)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.expense_item, viewGroup, false)

        return ExpenseViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ExpenseViewHolder, position: Int) {

        val expense = dataSet[position]
        viewHolder.bind(expense)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}