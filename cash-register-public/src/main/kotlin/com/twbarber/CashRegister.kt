package com.twbarber

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class CashRegister() {

    @Autowired
    private lateinit var balance : Balance

    fun show() : String {
        return balance.show()
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
        val change = makeChange(amount)
        balance = balance.take(change)
        return change
    }

    private fun makeChange(amount: Int) : Balance{
        return Balance(0, 0, 0, 0, 0)
    }
}