package com.twbarber


open class CashRegisterConfig {

    open fun initialBalance() : Balance {
        return Balance(0, 0, 0, 0, 0)
    }

}
