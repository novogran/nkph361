package com.nkph361.DI

import com.nkph361.data.api.ExchangeRateApiService
import com.nkph361.data.repo.ExchangeRateRepoImpl
import com.nkph361.domain.repo.ExchangeRateRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
interface AppBindModule {
    @Binds
    fun bindRepoImpl(exchangeRateRepoImpl: ExchangeRateRepoImpl): ExchangeRateRepo
}

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build()
    }

    @Provides
    fun provideExchangeRateApiService(client: OkHttpClient): ExchangeRateApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://belarusbank.by/api/")
            .client(client)
            .build()
            .create(ExchangeRateApiService::class.java)
    }

}