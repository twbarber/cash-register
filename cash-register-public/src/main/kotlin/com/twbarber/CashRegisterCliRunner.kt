package com.twbarber

import java.util.*

class CashRegisterCliRunner {

    val READY = "ready"
    val SHOW_REGEX = "show"
    val TAKE_REGEX = "take (\\d+ ){4}\\d+"
    val PUT_REGEX = "put (\\d+ ){4}\\d+"
    val CHANGE_REGEX = "change \\d+"

    private val register = CashRegister()
    private val input = Scanner(System.`in`)

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            CashRegisterCliRunner().run()
        }
    }

    fun run() {
        System.out.println(READY)
        while (true) {
            try {
                val input = input.nextLine().toLowerCase()
                if (input.matches(Regex(SHOW_REGEX))) print(register.show())
                else if (input.matches(Regex(TAKE_REGEX))) print(register.take(parseTransaction(input)).show())
                else if (input.matches(Regex(PUT_REGEX))) print(register.put(parseTransaction(input)).show())
                else if (input.matches(Regex(CHANGE_REGEX))) print(register.change(parseChange(input)).show())
                else (print("Unsupported Operation."))
            } catch (e : Exception) {
                print(e.message + "\n")
            }
        }
    }

    fun parseTransaction(input : String): Balance {
        val bills = input.split(" ")
        return Balance(bills[1].toInt(), bills[2].toInt(), bills[3].toInt(),
                bills[4].toInt(), bills[5].toInt())
    }

    fun parseChange(input : String): Int {
        return input.split(" ")[1].toInt()
    }

    fun print(output : String) {
        System.out.println(output)
    }
}
