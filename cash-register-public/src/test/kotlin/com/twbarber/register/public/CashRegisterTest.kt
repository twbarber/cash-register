package com.twbarber.register.public

import com.twbarber.register.public.data.Balance
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CashRegisterTest {

    @Test
    fun balance() {
        val register = CashRegister()
        assertEquals(Balance(0, 0, 0, 0, 0), register.balance())
    }

    @Test
    fun show() {
        val register = CashRegister()
        assertEquals("$0 0 0 0 0 0", register.show())
    }

    @Test
    fun zero() {
        val register = CashRegister()
        register.put(Balance(1, 1, 1, 1, 1))
        assertEquals(Balance(1, 1, 1, 1, 1), register.balance())
        register.zero()
        assertEquals(Balance(0, 0, 0, 0, 0), register.balance())
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

    @Test
    fun changeZero() {
        val register = CashRegister()
        register.put(Balance(200, 200, 200, 200, 200))
        assertEquals(Balance(0, 0, 0, 0, 0), register.change(0))
    }

    @Test
    fun changeLargeNumbers() {
        val register = CashRegister()
        register.put(Balance(200, 200, 200, 200, 200))
        assertEquals(Balance(193, 0, 0, 1, 1), register.change(3863))
    }

    @Test
    fun changeLargerNumbers() {
        val register = CashRegister()
        register.put(Balance(2000, 2000, 2000, 2000, 2000))
        assertEquals(Balance(1850, 1, 0, 1, 0), register.change(37012))
    }

    @Test
    fun changeNoTwenties() {
        val register = CashRegister()
        register.put(Balance(0, 1, 1, 1, 1))
        assertEquals(Balance(0, 1, 1, 1, 1), register.change(18))
    }

    @Test
    fun changeNoTens() {
        val register = CashRegister()
        register.put(Balance(1, 0, 1, 1, 1))
        assertEquals(Balance(1, 0, 1, 1, 1), register.change(28))
    }

    @Test
    fun changeNoFives() {
        val register = CashRegister()
        register.put(Balance(1, 1, 0, 1, 1))
        assertEquals(Balance(1, 1, 0, 1, 1), register.change(33))
    }

    @Test
    fun changeNoTwos() {
        val register = CashRegister()
        register.put(Balance(1, 1, 1, 0, 1))
        assertEquals(Balance(1, 1, 1, 0, 0), register.change(35))
    }

    @Test
    fun changeNoOnes() {
        val register = CashRegister()
        register.put(Balance(1, 1, 1, 2, 0))
        assertEquals(Balance(0, 1, 0, 2, 0), register.change(14))
    }

    @Test(expected = IllegalArgumentException::class)
    fun notEnoughCashToMakeChange() {
        val register = CashRegister()
        register.put(Balance(0, 0, 0, 0, 1))
        register.change(10)
    }

    @Test(expected = RuntimeException::class)
    fun improperBillsToMakeChange() {
        val register = CashRegister()
        register.put(Balance(1, 1, 1, 0, 0))
        register.change(3)
    }

}