package com.nkph361.domain.entity

data class ExchangeRateEntity(
    val name: String,
    val usdIn: Double,
    val usdOut: Double,
    val eurIn: Double,
    val eurOut: Double,
    val rubIn: Double,
    val rubOut: Double,
)