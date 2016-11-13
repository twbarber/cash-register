package com.twbarber

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CashRegisterController {

    @Autowired
    lateinit var service : CashRegisterService

    @RequestMapping("/balance")
    fun getBalance() : String {
        return service.getBalance()
    }

}

