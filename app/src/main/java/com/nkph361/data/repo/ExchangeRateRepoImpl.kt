package com.nkph361.data.repo

import com.nkph361.data.api.ExchangeRateApiService
import com.nkph361.data.dto.ExchangeRateInfo
import com.nkph361.domain.entity.ExchangeRateEntity
import com.nkph361.domain.repo.ExchangeRateRepo
import javax.inject.Inject


class ExchangeRateRepoImpl @Inject constructor(
    private val exchangeRateService: ExchangeRateApiService
) : ExchangeRateRepo {

    override suspend fun getExchangeRate(city: String): ExchangeRateEntity {
        val exchangeRateInfo: ExchangeRateInfo = exchangeRateService.getWeather(city = city)[0]
        return ExchangeRateEntity(
            usdIn = exchangeRateInfo.usdIn,
            usdOut = exchangeRateInfo.usdOut,
            eurIn = exchangeRateInfo.eurIn,
            eurOut = exchangeRateInfo.eurOut,
            rubIn = exchangeRateInfo.rubIn,
            rubOut = exchangeRateInfo.rubOut
        )
    }
}