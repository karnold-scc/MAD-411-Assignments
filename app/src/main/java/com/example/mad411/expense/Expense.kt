package com.example.mad411.expense

data class Expense(val id: Int, val name: String, val amount: String, val currency: String,
    val convertedCost: Double){

}