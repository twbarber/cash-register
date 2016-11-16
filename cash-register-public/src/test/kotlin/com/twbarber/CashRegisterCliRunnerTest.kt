package com.twbarber

import org.junit.Assert.assertEquals
import org.junit.Test

class CashRegisterCliRunnerTest {

    val cli = CashRegisterCliRunner()

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
    fun parseChange() {
        val input = "change 3"
        assertEquals(3, cli.parseChange(input))
    }

}