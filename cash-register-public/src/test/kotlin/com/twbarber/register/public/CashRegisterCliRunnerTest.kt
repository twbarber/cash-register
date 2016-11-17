package com.twbarber.register.public

import com.twbarber.register.public.cli.CashRegisterCliRunner
import com.twbarber.register.public.data.Balance
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test

class CashRegisterCliRunnerTest {

    val cli = CashRegisterCliRunner()

    @Test
    fun matchPutRegex() {
        val input = "put 1 1 1 1 1"
        assertTrue(input.matches(Regex(cli.PUT_REGEX)))
    }

    @Test
    fun badPutRegex_NotEnoughBills() {
        val input = "put 1 1 1 1"
        assertFalse(input.matches(Regex(cli.PUT_REGEX)))
    }

    @Test
    fun matchTakeRegex() {
        val input = "take 1 1 1 1 1"
        assertTrue(input.matches(Regex(cli.TAKE_REGEX)))
    }

    @Test
    fun badTakeRegex_NotEnoughBills() {
        val input = "take 1 1 1 1"
        assertFalse(input.matches(Regex(cli.TAKE_REGEX)))
    }

        @Test
    fun matchShowRegex() {
        val input = "show"
        assertTrue(input.matches(Regex(cli.SHOW_REGEX)))
    }

    @Test
    fun matchChangeRegex() {
        val input = "change 13"
        assertTrue(input.matches(Regex(cli.CHANGE_REGEX)))
    }

    @Test
    fun emptyInput() {
        val input = ""
        assertTrue(!input.matches(Regex(cli.TAKE_REGEX)))
        assertTrue(!input.matches(Regex(cli.PUT_REGEX)))
        assertTrue(!input.matches(Regex(cli.CHANGE_REGEX)))
        assertTrue(!input.matches(Regex(cli.SHOW_REGEX)))
    }

    @Test
    fun parsePutTransaction() {
        val input = "put 1 1 1 1 1"
        assertEquals(Balance(1, 1, 1, 1, 1), cli.parseTransaction(input))
    }

    @Test
    fun parseTakeTransaction() {
        val input = "take 1 1 1 1 1"
        assertEquals(Balance(1, 1, 1, 1, 1), cli.parseTransaction(input))
    }

    @Test
    fun parseExcessTransactionString() {
        val input = "take 1 1 1 1 1 EXTRA"
        assertEquals(Balance(1, 1, 1, 1, 1), cli.parseTransaction(input))
    }

    @Test
    fun parseChange() {
        val input = "change 3"
        assertEquals(3, cli.parseChange(input))
    }

}