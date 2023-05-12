package com.nkph361.data.dto

import com.google.gson.annotations.SerializedName

data class ExchangeRateInfo(
    @SerializedName("USD_in")
    val usdIn: Double,
    @SerializedName("USD_out")
    val usdOut: Double,
    @SerializedName("EUR_in")
    val eurIn: Double,
    @SerializedName("EUR_out")
    val eurOut: Double,
    @SerializedName("RUB_in")
    val rubIn: Double,
    @SerializedName("RUB_out")
    val rubOut: Double,
)
