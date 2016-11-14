package com.twbarber

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class CashRegisterApplication {

    companion object{
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(CashRegisterApplication::class.java, *args)
        }
    }

}
