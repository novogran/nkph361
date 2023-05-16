package com.nkph361.presentation.mapper

import com.nkph361.Mapper
import com.nkph361.domain.entity.ExchangeRateEntity
import com.nkph361.presentation.model.ExchangeRateViewData
import javax.inject.Inject

class ExchangeRateEntityMapper @Inject constructor() : Mapper<ExchangeRateEntity, ExchangeRateViewData> {
    override fun map(from: ExchangeRateEntity) = ExchangeRateViewData(
        usdIn = from.usdIn,
        usdOut = from.usdOut,
        eurIn = from.eurIn,
        eurOut = from.eurOut,
        rubIn = from.rubIn,
        rubOut = from.rubOut,
        loadStatus = true,
        inProgress = false,
    )
}