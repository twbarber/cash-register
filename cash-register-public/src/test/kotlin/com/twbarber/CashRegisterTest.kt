package com.twbarber

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

    @Test(expected = IllegalArgumentException::class)
    fun negativePutThrowsIllegalArgument() {
        val register = CashRegister()
        register.put(Balance(1, 1, 1, 1, -1))
    }

    @Test
    fun take() {
        val register = CashRegister()
        register.put(Balance(1, 1, 1, 1, 1))
        assertEquals("$38 1 1 1 1 1", register.show())
        register.take(Balance(1, 1, 1, 1, 1))
        assertEquals("$0 0 0 0 0 0", register.show())
    }

    @Test(expected = IllegalArgumentException::class)
    fun excessiveTakeThrowsIllegalArgument() {
        val register = CashRegister()
        register.put(Balance(1, 1, 1, 1, 1))
        assertEquals("$38 1 1 1 1 1", register.show())
        register.take(Balance(2, 2, 2, 2, 2))
    }

    @Test
    fun change() {

    }

}