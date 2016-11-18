package com.twbarber.register.public

import com.twbarber.register.public.data.Balance
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CashRegisterTest {

    @Test
    fun show() {
        val register = CashRegister()
        assertEquals("$0 0 0 0 0 0", register.show())
    }

    @Test
    fun put() {
        val register = CashRegister()
        register.put(Balance(1, 1, 1, 1, 1))
        assertEquals("$38 1 1 1 1 1", register.show())
    }

    @Test
    fun take() {
        val register = CashRegister()
        register.put(Balance(1, 1, 1, 1, 1))
        assertEquals("$38 1 1 1 1 1", register.show())
        register.take(Balance(1, 1, 1, 1, 1))
        assertEquals("$0 0 0 0 0 0", register.show())
    }

    @Test
    fun change() {
        val register = CashRegister()
        register.put(Balance(20, 20, 20, 20, 20))
        assertEquals(Balance(20, 10, 0, 0, 0), register.change(500))
    }

    @Test(expected = IllegalArgumentException::class)
    fun notEnoughCashToMakeChange() {
        val register = CashRegister()
        register.put(Balance(0, 0, 0, 0, 1))
        register.change(10)
    }

}