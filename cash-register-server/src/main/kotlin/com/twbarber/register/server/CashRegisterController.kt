package com.twbarber.register.server

import com.twbarber.register.public.CashRegister
import com.twbarber.register.public.data.Balance
import com.twbarber.register.public.data.Change
import com.twbarber.register.public.web.BalanceDto
import com.twbarber.register.public.web.ChangeDto
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/register")
class CashRegisterController {

    private val register = CashRegister()

    @RequestMapping(value = "/balance", method = arrayOf(RequestMethod.GET), produces = arrayOf(APPLICATION_JSON_VALUE))
    fun balance() : BalanceDto {
        return register.balance().internalToExternal()
    }

    @RequestMapping(value = "/withdraw", method = arrayOf(RequestMethod.POST), produces = arrayOf(APPLICATION_JSON_VALUE))
    fun withdraw(@RequestBody bills : Balance) : BalanceDto {
        return register.take(bills).internalToExternal()
    }

    @RequestMapping(value = "/deposit", method = arrayOf(RequestMethod.POST), produces = arrayOf(APPLICATION_JSON_VALUE))
    fun deposit(@RequestBody bills : Balance) : BalanceDto {
        return register.put(bills).internalToExternal()
    }

    @RequestMapping(value = "/change", method = arrayOf(RequestMethod.POST), produces = arrayOf(APPLICATION_JSON_VALUE))
    fun change(@RequestBody change: Change) : ChangeDto {
        return ChangeDto(register.change(change.amount))
    }

    private fun Balance.internalToExternal() : BalanceDto =
            BalanceDto("$" + this.total(), this.twenties, this.tens, this.fives, this.twos, this.ones)

}

