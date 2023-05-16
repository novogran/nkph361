package com.nkph361.data.repo

import com.nkph361.data.api.ExchangeRateApiService
import com.nkph361.data.mapper.ExchangeRateInfoMapper
import com.nkph361.domain.entity.ExchangeRateEntity
import com.nkph361.domain.repo.ExchangeRateRepo
import javax.inject.Inject


class ExchangeRateRepoImpl @Inject constructor(
    private val exchangeRateService: ExchangeRateApiService,
    private val exchangeRateInfoMapper: ExchangeRateInfoMapper
) : ExchangeRateRepo {

    override suspend fun getExchangeRate(city: String): ExchangeRateEntity {
        return exchangeRateInfoMapper.map(exchangeRateService.getWeather(city = city)[0])
    }
}