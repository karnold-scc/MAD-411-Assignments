package com.example.mad411

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val dataSet: List<Expense>, private val onDeleteClick: (Int) -> Unit) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val expenseText: TextView
        val expenseAmountText: TextView
        val deleteButton : Button

        init {
            // Define click listener for the ViewHolder's View
            expenseText = view.findViewById(R.id.expenseNameText)
            expenseAmountText = view.findViewById(R.id.expenseAmountText)
            deleteButton = view.findViewById(R.id.deleteButton)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.inner_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.expenseText.text = dataSet[position].name
        viewHolder.expenseAmountText.text = dataSet[position].amount.toString()
        viewHolder.deleteButton.setOnClickListener{ onDeleteClick(position)}
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}