package com.twbarber

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CashRegisterController {

    @RequestMapping("/balance")
    fun getBalance() : String {
        return "1"
    }

}

