package com.twbarber.register.public

import com.twbarber.register.public.data.Balance

import com.twbarber.register.public.data.MoneyValue.TWENTY
import com.twbarber.register.public.data.MoneyValue.TEN
import com.twbarber.register.public.data.MoneyValue.FIVE
import com.twbarber.register.public.data.MoneyValue.TWO

open class CashRegister() {

    private var balance = Balance(0, 0, 0, 0, 0)

    fun balance() : Balance = balance

    fun show() : String = balance.show()

    fun zero() : Balance {
        balance = Balance(0, 0, 0, 0, 0)
        return balance
    }

    fun put(transaction: Balance): Balance {
        balance = balance.put(transaction)
        return balance
    }

    fun take(transaction: Balance): Balance {
        balance = balance.take(transaction)
        return balance
    }

    fun change(amount: Int): Balance {
        val change = makeChange(amount, balance)
        balance = balance.take(change)
        return change
    }

    private fun makeChange(amount: Int, bal: Balance) : Balance {
        require(amount <= bal.total()) { "Unable to make change for that amount." }
        var change = Balance(0, 0, 0, 0, 0)
        val count : Int
        if (amount >= TWENTY.value && bal.twenties >  0) {
            count = floor(amount / TWENTY.value, bal.twenties)
            change = change.put(Balance(count, 0, 0, 0, 0))
            change = change.put(makeChange(amount - change.total(), bal.take(change)))
        } else if (amount >= TEN.value && bal.tens >  0) {
            count = floor(amount / TEN.value, bal.tens)
            change = change.put(Balance(0, count, 0, 0, 0))
            change = change.put(makeChange(amount - change.total(), bal.take(change)))
        } else if (amount >= FIVE.value && bal.fives > 0) {
            count = floor(amount / FIVE.value, bal.fives)
            change = change.put(Balance(0, 0, count, 0, 0))
            change = change.put(makeChange(amount - change.total(), bal.take(change)))
        } else if (amount >= TWO.value &&  bal.twos >  0) {
            count = floor(amount / TWO.value, bal.twos)
            change = change.put(Balance(0, 0, 0, count, 0))
            change = change.put(makeChange(amount - change.total(), bal.take(change)))
        } else {
            change.put(Balance(0, 0, 0, 0, amount))
        }
        return change
    }

    private fun floor(a: Int, b: Int) : Int {
        return if (a < b) a else b
    }
}