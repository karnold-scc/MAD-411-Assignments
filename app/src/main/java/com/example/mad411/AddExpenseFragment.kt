package com.example.mad411

import android.app.Fragment
import android.os.Bundle
//import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddExpenseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddExpenseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var expenseToUpdate: Expense? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_expense, container, false)

        val expenseNameTextEdit: EditText = view.findViewById(R.id.expenseNameEditText)
        val expenseAmountEditText: EditText = view.findViewById(R.id.amountEditText)
        val saveButton: Button = view.findViewById(R.id.addExpenseButton)

        arguments?.let{
            val expenseId = it.getInt("expenseId", -1)
            val expenseName = it.getString("expenseName", "")
            val expenseAmount = it.getString("expenseAmount", "0.0")

            if (expenseId != -1){
                expenseToUpdate = Expense(expenseId, expenseName, expenseAmount)
                expenseNameTextEdit.setText(expenseName)
                expenseAmountEditText.setText(expenseAmount)
            }

        }

        saveButton.setOnClickListener {
            val expenseId = expenseToUpdate?.id ?: (System.currentTimeMillis() / 1000).toInt()
            val expenseName = expenseNameTextEdit.text.toString()
            val expenseAmount = expenseAmountEditText.text.toString()

            val bundle = Bundle().apply {
                putInt("expenseId", expenseId)
                putString("expenseName", expenseName)
                putString("expenseAmount", expenseAmount)
            }

            val navController = findNavController()
            navController.previousBackStackEntry?.savedStateHandle?.set("newExpense", bundle)
            navController.popBackStack()
        }

        return view
    }


}