package com.nkph361.domain.repo

import com.nkph361.domain.entity.ExchangeRateEntity

interface ExchangeRateRepo {
    suspend fun getExchangeRate(city: String): ExchangeRateEntity
}