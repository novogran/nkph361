package com.nkph361.data.repo

import com.nkph361.data.api.ExchangeRateApiService
import com.nkph361.data.dto.ExchangeRateInfo
import com.nkph361.domain.entity.ExchangeRateEntity
import com.nkph361.domain.repo.ExchangeRateRepo
import retrofit2.Retrofit

class ExchangeRateRepoImpl : ExchangeRateRepo {

    private val exchangeRateService = Retrofit.Builder()
        .baseUrl("https://belarusbank.by/api/kursExchange")
        .build()
        .create(ExchangeRateApiService::class.java)

    override suspend fun getExchangeRate(city: String): ExchangeRateEntity {
        val exchangeRateInfo: ExchangeRateInfo = exchangeRateService.getWeather(city = city)[0]
        return ExchangeRateEntity(
            name = exchangeRateInfo.name,
            usdIn = exchangeRateInfo.usdIn,
            usdOut = exchangeRateInfo.usdOut,
            eurIn = exchangeRateInfo.eurIn,
            eurOut = exchangeRateInfo.eurOut,
            rubIn = exchangeRateInfo.rubIn,
            rubOut = exchangeRateInfo.rubOut
        )
    }
}