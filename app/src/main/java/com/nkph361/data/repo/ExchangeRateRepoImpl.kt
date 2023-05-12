package com.nkph361.data.repo

import com.nkph361.data.api.ExchangeRateApiService
import com.nkph361.data.dto.ExchangeRateInfo
import com.nkph361.domain.entity.ExchangeRateEntity
import com.nkph361.domain.repo.ExchangeRateRepo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ExchangeRateRepoImpl @Inject constructor() : ExchangeRateRepo {

    var client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS).build()

    private val exchangeRateService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://belarusbank.by/api/")
        .client(client)
        .build()
        .create(ExchangeRateApiService::class.java)

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