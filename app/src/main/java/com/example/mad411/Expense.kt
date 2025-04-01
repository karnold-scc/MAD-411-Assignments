package com.example.mad411

import java.util.Currency

data class Expense(val id: Int, val name: String, val amount: String, val currency: Currency?,
    val convertedCost: Double?){

}