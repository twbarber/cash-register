package com.twbarber

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class CashRegisterConfig {

    @Bean
    open fun initialBalance() : Balance {
        return Balance(0, 0, 0, 0, 0)
    }

}
