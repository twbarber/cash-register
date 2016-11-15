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
    private lateinit var register: CashRegister

    @RequestMapping(value = "/balance", method = arrayOf(RequestMethod.GET))
    fun balance() : String {
        return register.show()
    }

    @RequestMapping(value = "/withdraw", method = arrayOf(RequestMethod.POST))
    fun withdraw(@RequestBody bills : Balance) : String {
        return register.take(bills).show()
    }

    @RequestMapping(value = "/deposit", method = arrayOf(RequestMethod.POST))
    fun deposit(@RequestBody bills : Balance) : String {
        return register.put(bills).show()
    }

    @RequestMapping(value = "/change", method = arrayOf(RequestMethod.POST))
    fun change(@RequestBody amount : Int) : String {
        return register.change(amount).show()
    }

}

