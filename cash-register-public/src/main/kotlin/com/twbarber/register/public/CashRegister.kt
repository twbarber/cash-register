package com.twbarber.register.public

import com.twbarber.register.public.data.Balance

open class CashRegister() {

    private var balance = Balance(0, 0, 0, 0, 0)

    fun balance() : Balance = balance

    fun show() : String = balance.show()

    fun put(transaction: Balance): Balance {
        balance = balance.put(transaction)
        return balance
    }

    fun take(transaction: Balance): Balance {
        balance = balance.take(transaction)
        return balance
    }

    fun change(amount: Int): Balance {
        val change = makeChange(amount)
        balance = balance.take(change)
        return change
    }

    private fun makeChange(amount: Int) : Balance {
        return Balance(0, 0, 0, 0, 0)
    }
}