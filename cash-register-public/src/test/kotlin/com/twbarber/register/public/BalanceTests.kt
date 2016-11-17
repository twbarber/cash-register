package com.twbarber.register.public

import com.twbarber.register.public.data.Balance
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class BalanceTests {

	@Test
	fun total() {
        val bal = Balance(1, 1, 1, 1, 1)
        assertTrue(38 == bal.total())
    }

    @Test
    fun totalOfZero() {
        val bal = Balance(0, 0, 0, 0, 0)
        assertTrue(0 == bal.total())
    }

    @Test
    fun show() {
        val bal = Balance(1, 1, 1, 1, 1)
        assertEquals("$38 1 1 1 1 1", bal.show())
    }

    @Test
    fun validToString() {
        val bal = Balance(1, 1, 1, 1, 1)
        assertEquals("1 1 1 1 1", bal.bills())
    }

    @Test
    fun addBalance() {
        var bal = Balance(1, 1, 1, 1, 1)
        bal = bal.put(Balance(1, 1, 1, 1, 1))
        assertEquals(Balance(2, 2, 2, 2, 2), bal)
    }

    @Test(expected = IllegalArgumentException::class)
    fun addBalanceWithNegativesThrowsError() {
        val bal = Balance(1, 1, 1, 1, 1)
        bal.put(Balance(-1, 0, 1, 1, 1))
    }

    @Test(expected = IllegalArgumentException::class)
    fun excessiveTakeThrowsIllegalArgument() {
        val bal = Balance(1, 1, 1, 1, 1)
        bal.take(Balance(2, 2, 2, 2, 2))
    }

}
