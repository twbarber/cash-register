package com.twbarber.register.public.data

import com.twbarber.register.public.data.MoneyValue.TWENTY
import com.twbarber.register.public.data.MoneyValue.TEN
import com.twbarber.register.public.data.MoneyValue.FIVE
import com.twbarber.register.public.data.MoneyValue.TWO

data class Balance(val twenties: Int, val tens: Int, val fives: Int, val twos: Int, val ones: Int) {

    private val INPUT_IS_NEGATIVE = "Must provide a non-negative value for all bills."

    private fun resultIsNegative(bill: String) : String { return "Not enough $bill to complete transaction." }

    init {
        require(this.twenties >=0 && this.tens >=0 && this.fives >=0
                && this.twos >=0 && this.ones >=0) { INPUT_IS_NEGATIVE }
    }

    fun total() : Int = TWENTY.value * twenties + TEN.value * tens + FIVE.value * fives + TWO.value * twos + ones

    fun bills() : String = "$twenties $tens $fives $twos $ones"

    fun take(transaction: Balance) : Balance {
        require(this.twenties - transaction.twenties >= 0) { resultIsNegative("twenties") }
        require(this.tens - transaction.tens >= 0) { resultIsNegative("tens") }
        require(this.fives - transaction.fives >= 0) { resultIsNegative("fives") }
        require(this.twos - transaction.twos >= 0) {  resultIsNegative("twos") }
        require(this.ones - transaction.ones >= 0) {  resultIsNegative("ones") }
        return Balance(this.twenties - transaction.twenties, this.tens - transaction.tens,
                this.fives - transaction.fives, this.twos - transaction.twos, this.ones - transaction.ones)
    }

    fun put(transaction: Balance) : Balance {
        require(transaction.twenties >= 0) {  resultIsNegative("twenties") }
        require(transaction.tens >= 0) { resultIsNegative("tens") }
        require(transaction.fives >= 0) { resultIsNegative("fives") }
        require(transaction.twos >= 0) { resultIsNegative("twos") }
        require(transaction.ones >= 0) { resultIsNegative("ones") }
        return Balance(this.twenties + transaction.twenties, this.tens + transaction.tens,
                this.fives + transaction.fives, this.twos + transaction.twos, this.ones + transaction.ones)
    }

    fun show() : String {
        val total = total()
        val bills = bills()
        return "\$$total $bills"
    }

}