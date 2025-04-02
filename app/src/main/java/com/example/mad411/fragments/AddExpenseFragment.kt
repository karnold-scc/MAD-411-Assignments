package com.example.mad411.fragments

//import android.app.Fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mad411.R
import com.example.mad411.apiFiles.CurrencyApiObj
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.mad411.apiFiles.RetrofitInstance
import com.example.mad411.expense.Expense

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
    private lateinit var currencies: CurrencyApiObj

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_expense, container, false)

        val expenseNameTextEdit: EditText = view.findViewById(R.id.expenseNameEditText)
        val expenseAmountEditText: EditText = view.findViewById(R.id.amountEditText)
        val saveButton: Button = view.findViewById(R.id.addExpenseButton)
        val currencySpinner: Spinner = view.findViewById(R.id.currencySpinner)
        val completedCheckBox: CheckBox = view.findViewById(R.id.conversionNeededCheckBox)
        val convertedAmount: TextView = view.findViewById(R.id.convertedCostTextView)
        fetchCurrencies()




        arguments?.let{
            val expenseId = it.getInt("expenseId", -1)
            val expenseName = it.getString("expenseName", "")
            val expenseAmount = it.getString("expenseAmount", "0.0")
            val currencyCode = currencySpinner.selectedItem.toString()

            if (expenseId != -1){
                val convertedCost= if (completedCheckBox.isChecked){
                    expenseAmount.toDouble() * (currencies.rates[currencyCode] ?: 1.0)
                }
                else{
                    expenseAmount.toDouble()
                }

                expenseToUpdate = Expense(expenseId, expenseName, expenseAmount, (currencySpinner.selectedItem.toString()), convertedCost)
                expenseNameTextEdit.setText(expenseName)
                expenseAmountEditText.setText(expenseAmount)
                convertedAmount.setText(convertedCost.toString())
            }

        }

        saveButton.setOnClickListener {
            val expenseId = expenseToUpdate?.id ?: (System.currentTimeMillis() / 1000).toInt()
            val expenseName = expenseNameTextEdit.text.toString()
            val expenseAmount = expenseAmountEditText.text.toString()
            val convertedCostAmount = convertedAmount.text.toString().toDouble()
            val currencyCode = currencySpinner.selectedItem.toString()

            val newExpense = Expense(
                expenseId, expenseName, expenseAmount, currencyCode, convertedCostAmount
            )



            val navController = findNavController()
            navController.previousBackStackEntry?.savedStateHandle?.set("newExpense", bundleOf(
                "expenseId" to newExpense.id,
                "expenseName" to newExpense.name,
                "expenseAmount" to newExpense.amount,
                "expenseCurrency" to newExpense.currency,
                "expenseConverted" to newExpense.convertedCost,
            ))
            navController.popBackStack()
        }

        return view
    }

    private fun fetchCurrencies(){
        lifecycleScope.launch{
            try{
                currencies = withContext(Dispatchers.IO){
                    RetrofitInstance.api.getCurrency()
                }

                if(currencies != null){
                    val currencyList = currencies.rates.keys.toList()

                    val currencySpinner: Spinner = requireView().findViewById(R.id.currencySpinner)

                    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencyList)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    currencySpinner.adapter = adapter

                    val defaultIndex = currencyList.indexOfFirst { it == "CAD" }

                    if (defaultIndex >= 0) {
                        currencySpinner.setSelection(defaultIndex)
                    }
                }
            }
            catch (e: Exception){
                Snackbar.make(requireView(), "Error: ${e.message}", Snackbar.LENGTH_SHORT).show()
            }
        }
    }




}