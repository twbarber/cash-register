package com.twbarber

data class Balance(val twenties: Int = 0, val tens: Int = 0, val fives: Int = 0, val ones: Int = 0) {

    fun total() : Int {
        return 20 * twenties + 10 * tens + 5 * fives + ones
    }

    fun add(balance: Balance) : Balance {
        require(balance.twenties >= 0 && balance.tens >= 0
                && balance.fives >= 0 && balance.ones >= 0) { "Can only add non-negative amounts of bills." }
        return Balance(this.twenties + balance.twenties, this.tens + balance.tens,
                this.fives + balance.fives, this.ones + balance.ones)
    }

    override fun toString() : String {
        val total = total()
        return "\$$total $twenties $tens $fives $ones"
    }

}