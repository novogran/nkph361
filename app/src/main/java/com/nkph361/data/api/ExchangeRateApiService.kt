package com.nkph361.data.api

import com.nkph361.data.dto.ExchangeRateInfo
import retrofit2.http.Query

interface ExchangeRateApiService {
    suspend fun getWeather(@Query("city") city: String): List<ExchangeRateInfo>
}