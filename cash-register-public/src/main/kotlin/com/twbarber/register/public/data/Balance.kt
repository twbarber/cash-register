package com.twbarber.register.public.data

import com.twbarber.register.public.data.MoneyValue.TWENTY
import com.twbarber.register.public.data.MoneyValue.TEN
import com.twbarber.register.public.data.MoneyValue.FIVE
import com.twbarber.register.public.data.MoneyValue.TWO

data class Balance(val twenties: Int, val tens: Int, val fives: Int, val twos: Int, val ones: Int) {

    fun total() : Int = TWENTY.value * twenties + TEN.value * tens + FIVE.value * fives + TWO.value * twos + ones

    fun bills() : String = "$twenties $tens $fives $twos $ones"

    fun take(transaction: Balance) : Balance {
        require(this.twenties - transaction.twenties >= 0) { "Not enough twenties to complete transaction." }
        require(this.tens - transaction.tens >= 0) { "Not enough tens to complete transaction." }
        require(this.fives - transaction.fives >= 0) { "Not enough fives to complete transaction." }
        require(this.twos - transaction.twos >= 0) { "Not enough twos to complete transaction." }
        require(this.ones - transaction.ones >= 0) { "Not enough ones to complete transaction." }
        return Balance(this.twenties - transaction.twenties, this.tens - transaction.tens,
                this.fives - transaction.fives, this.twos - transaction.twos, this.ones - transaction.ones)
    }

    fun put(transaction: Balance) : Balance {
        require(transaction.twenties >= 0) { "Not enough twenties to complete transaction." }
        require(transaction.tens >= 0) { "Not enough tens to complete transaction." }
        require(transaction.fives >= 0) { "Not enough fives to complete transaction." }
        require(transaction.twos >= 0) { "Not enough twos to complete transaction." }
        require(transaction.ones >= 0) { "Not enough ones to complete transaction." }
        return Balance(this.twenties + transaction.twenties, this.tens + transaction.tens,
                this.fives + transaction.fives, this.twos + transaction.twos, this.ones + transaction.ones)
    }

    fun show() : String {
        val total = total()
        val bills = bills()
        return "\$$total $bills"
    }

}