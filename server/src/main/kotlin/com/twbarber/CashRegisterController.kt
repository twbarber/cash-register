package com.twbarber

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/register")
class CashRegisterController {

    @Autowired
    lateinit var service : CashRegisterService

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun getBalance() : String {
        return service.getBalance()
    }

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.PUT))
    fun modifyBalance(@RequestBody transaction : Balance) : String {
        return service.getBalance()
    }

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.PUT))
    fun makechange(@RequestBody transaction : Balance) : String {
        return service.getBalance()
    }

}

