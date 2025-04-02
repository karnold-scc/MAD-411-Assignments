package com.example.mad411.fragments

//import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad411.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.example.mad411.expense.CustomAdapter
import com.example.mad411.expense.Expense

//GSON
import com.google.gson.Gson
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val FILE_NAME = "expenses.txt"

class MainFragment : Fragment(), CustomAdapter.ExpenseItemListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var customAdapter: CustomAdapter
    private val expenseList = mutableListOf<Expense>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = view.findViewById(R.id.expenseRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Prevent duplicate expenses
        expenseList.clear()
        expenseList.addAll(loadTasksFromFile(requireContext()))


        customAdapter = CustomAdapter(expenseList, this)
        recyclerView.adapter = customAdapter

        val addExpenseButton : FloatingActionButton = view.findViewById(R.id.addExpenseFab)
        addExpenseButton.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_addExpenseFragment)
        }

        //fetchCurrencies()

        return view
    }



    override fun onEditClick(expense: Expense){
        val bundle = Bundle().apply{
            putInt("expenseId", expense.id)
            putString("expenseName", expense.name)
            putString("expenseAmount", expense.amount)
        }
        findNavController().navigate(R.id.action_mainFragment_to_expenseDetailsFragment, bundle)
    }

    override fun onDeleteClick(expense: Expense){
        expenseList.remove(expense)
        customAdapter.notifyDataSetChanged()
        saveTasksToFile(requireContext(), expenseList)



    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Bundle>("newExpense")
            ?.observe(viewLifecycleOwner){ bundle ->
                val updatedExpense = Expense(

                    bundle.getInt("expenseId"),
                    bundle.getString("expenseName", ""),
                    bundle.getString("expenseAmount", ""),
                    bundle.getString("expenseCurrency", ""),
                    bundle.getDouble("expenseConverted", 0.0)
                )
                Log.d("InBundle", bundle.getString("expenseName").toString())

                val index = expenseList.indexOfFirst { it.id == updatedExpense.id }
                if (index != -1){
                    expenseList[index] = updatedExpense
                    customAdapter.notifyItemChanged(index)
                }
                else{
                    expenseList.add(updatedExpense)
                    customAdapter.notifyItemInserted(expenseList.size - 1)

                }
                Log.d("BeforeSave", expenseList[0].name)
                saveTasksToFile(requireContext(), expenseList)
            }
    }

    private fun saveTasksToFile(context: Context, expenseList: List<Expense>){
        try{
            val json = Gson().toJson(expenseList)

            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use{
                    output ->
                output.write(json.toByteArray())
            }
            Log.d("FileStorage", "Expenses Saved Successfully")
        }
        catch (e: IOException){
            Log.e("FileStorage", "Error Saving Expenses: ${e.message}")
        }
    }

    private fun loadTasksFromFile(context: Context): MutableList<Expense> {
        val expenseList: MutableList<Expense> = mutableListOf()
        try {
            val file = File(context.filesDir, FILE_NAME)
            if (!file.exists()) return expenseList

            val json = file.readText()
            val type = object : TypeToken<List<Expense>>() {}.type
            val loadedTasks: List<Expense> = Gson().fromJson(json, type)
            expenseList.addAll(loadedTasks)

            Log.d("FileStorage", "Expenes loaded successfully")
        } catch (e: FileNotFoundException) {
            Log.e("FileStorage", "File not found: ${e.message}")
        } catch (e: IOException) {
            Log.e("FileStorage", "Error reading file: ${e.message}")
        }
        return expenseList
    }

}