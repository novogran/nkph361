package com.nkph361.domain

import com.nkph361.domain.entity.ExchangeRateEntity
import com.nkph361.domain.repo.ExchangeRateRepo

class ExchangeRateUseCase(
    private val exchangeRateRepo: ExchangeRateRepo
) {
    suspend fun execute(city:String): ExchangeRateEntity {
      return exchangeRateRepo.getExchangeRate(city)
    }
}