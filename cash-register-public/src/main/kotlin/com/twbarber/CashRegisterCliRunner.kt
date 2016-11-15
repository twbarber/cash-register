package com.twbarber

import org.springframework.context.annotation.AnnotationConfigApplicationContext

class CashRegisterCliRunner {

    fun main() {
        val context = AnnotationConfigApplicationContext(CashRegisterConfig::class.java)
        val register = CashRegister()
        while (true) {

        }
    }
}
