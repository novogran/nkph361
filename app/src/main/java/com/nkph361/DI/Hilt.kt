package com.nkph361.DI

import com.nkph361.data.repo.ExchangeRateRepoImpl
import com.nkph361.domain.repo.ExchangeRateRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface AppBindModule {
    @Binds
    fun bindRepoImpl(exchangeRateRepoImpl: ExchangeRateRepoImpl): ExchangeRateRepo
}