package com.nkph361.presentation.model

data class ExchangeRateViewData(
    val usdIn: Double = 0.0,
    val usdOut: Double = 0.0,
    val eurIn: Double = 0.0,
    val eurOut: Double = 0.0,
    val rubIn: Double = 0.0,
    val rubOut: Double = 0.0,
    val loadStatus: Boolean = false,
    val inProgress: Boolean = false,
    val loadError: Boolean = false
)
