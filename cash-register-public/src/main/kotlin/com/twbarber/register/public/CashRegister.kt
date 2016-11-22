package com.twbarber.register.public

import com.twbarber.register.public.data.Balance
import com.twbarber.register.public.data.MoneyValue.*

open class CashRegister() {

    private val CANT_MAKE_CHANGE = "Unable to make change for that amount."

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

    fun change(amount: Int): String {
        if (amount == 0) return Balance(0, 0, 0, 0, 0).bills()
        val change = makeChange(amount, balance)
        if (change == Balance(0, 0, 0, 0, 0)) {
            throw RuntimeException("Unable to make change for \$$amount")
        }
        balance = balance.take(change)
        return change.bills()
    }

    /**
     * Makes changes with the least amount of bills given an input.
     *
     *
     */
    private fun makeChange(amount: Int, bal: Balance) : Balance {
        if (amount == 0) { return Balance(0, 0, 0, 0, 0) }
        require(amount <= bal.total()) { CANT_MAKE_CHANGE }

        var change = Balance(0, 0, 0, 0, 0)
        if (amount >= TWENTY.value && bal.twenties >  0) {
            change = change.put(utilizeTwenties(amount, bal, change))
        } else if (amount >= TEN.value && bal.tens >  0 ) {
            change = change.put(utilizeTens(amount, bal, change))
        } else if (amount >= FIVE.value && bal.fives > 0) {
            change = change.put(utilizeFives(amount, bal, change))
        } else if (amount >= TWO.value &&  bal.twos > 0) {
            change = change.put(utilizeTwos(amount, bal, change))
        } else if (bal.ones > 0) {
            change = change.put(Balance(0, 0, 0, 0, amount))
        }

        if (change == Balance(0, 0, 0, 0, 0)) { throw RuntimeException(CANT_MAKE_CHANGE) }
        return change
    }

    private fun utilizeTwenties(amount: Int, bal: Balance, currentChange: Balance) : Balance {
        var change = currentChange
        val count = floor(amount / TWENTY.value, bal.twenties)
        change = change.put(Balance(count, 0, 0, 0, 0))
        return change.put(makeChange(amount - change.total(),
                Balance(0, bal.tens, bal.fives, bal.twos, bal.ones)))
    }

    private fun utilizeTens(amount: Int, bal: Balance, currentChange: Balance) : Balance {
        var change = currentChange
        val count = floor(amount / TEN.value, bal.tens)
        val balWithoutTens = Balance(0, 0, bal.fives, bal.twos, bal.ones)
        try {
            change = change.put(Balance(0, count, 0, 0, 0))
            change = change.put(makeChange(amount - change.total(), balWithoutTens))
        } catch(e: Exception) {
            change = change.take(Balance(0, 1, 0, 0, 0))
            change = change.put(makeChange(amount - change.total(), balWithoutTens))
        }
        return change
    }

    private fun utilizeFives(amount: Int, bal: Balance, currentChange: Balance) : Balance {
        var change = currentChange
        val count = floor(amount / FIVE.value, bal.fives)
        val balWithoutFives = Balance(0, 0, 0, bal.twos, bal.ones)
        try {
            change = change.put(Balance(0, 0, count, 0, 0))
            change = change.put(makeChange(amount - change.total(), balWithoutFives))
        } catch(e: Exception) {
            change = change.take(Balance(0, 0, 1, 0, 0))
            change = change.put(makeChange(amount - change.total(), balWithoutFives))
        }
        return change
    }

    private fun utilizeTwos(amount: Int, bal: Balance, currentChange: Balance) : Balance {
        var change = currentChange
        val count = floor(amount / TWO.value, bal.twos)
        val balWithoutTwos = Balance(0, 0, 0, 0, bal.ones)
        change = change.put(Balance(0, 0, 0, count, 0))
        change = change.put(makeChange(amount - change.total(), balWithoutTwos))
        return change
    }

    private fun floor(a: Int, b: Int) : Int {
        return if (a < b) a else b
    }
}