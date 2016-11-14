package com.twbarber

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CashRegisterService {

    @Autowired
    private lateinit var register : CashRegister

    fun getBalance() : String {
        return register.show()
    }

    fun deposit(balance: Balance) : String {
        return register.put(balance).show()
    }

    fun withdraw(balance: Balance) : String {
        return register.take(balance).show()
    }

    fun makeChange(amount: Int) : String {
        return register.change(amount).show()
    }

}