package com.twbarber


import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test


class BalanceTests {

	@Test
	fun total() {
        val bal = Balance(1, 1, 1, 1)
        assertTrue(36 == bal.total())
    }

    @Test
    fun totalOfZero() {
        val bal = Balance(1, 1, 1, 1)
        assertTrue(36 == bal.total())
    }

    @Test
    fun validToString() {
        val bal = Balance(1, 1, 1, 1)
        assertTrue("$36 1 1 1 1" == bal.toString())
    }

    @Test
    fun addBalance() {
        var bal = Balance(1, 1, 1, 1)
        bal = bal.add(Balance(1, 1, 1, 1))
        assertEquals(Balance(2, 2, 2, 2), bal)
    }

    @Test(expected = IllegalArgumentException::class)
    fun addBalanceWithNegativesThrowsError() {
        val bal = Balance(1, 1, 1, 1)
        bal.add(Balance(-1, 0, 1, 1))
    }


}
