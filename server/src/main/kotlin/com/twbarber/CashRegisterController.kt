package com.twbarber

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/register")
class CashRegisterController {

    @Autowired
    lateinit var service : CashRegisterService

    @RequestMapping(value = "/balance", method = arrayOf(RequestMethod.GET))
    fun getBalance() : String {
        return service.getBalance()
    }

    @RequestMapping(value = "/withdraw", method = arrayOf(RequestMethod.POST))
    fun withdraw(@RequestBody bills : Balance) : String {
        return service.withdraw(bills)
    }

    @RequestMapping(value = "/deposit", method = arrayOf(RequestMethod.POST))
    fun deposit(@RequestBody bills : Balance) : String {
        return service.deposit(bills)
    }

    @RequestMapping(value = "/change", method = arrayOf(RequestMethod.POST))
    fun makechange(@RequestBody amount : Int) : String {
        return service.makeChange(amount)
    }

}

