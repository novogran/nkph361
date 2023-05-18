package com.nkph361.presentation.mapper

import com.nkph361.comm.Mapper
import com.nkph361.domain.entity.ExchangeRateEntity
import com.nkph361.presentation.model.ExchangeRateModel
import javax.inject.Inject

class ExchangeRateEntityMapper @Inject constructor() :
    Mapper<ExchangeRateEntity, ExchangeRateModel> {
    override fun map(from: ExchangeRateEntity) = ExchangeRateModel(
        usdIn = from.usdIn,
        usdOut = from.usdOut,
        eurIn = from.eurIn,
        eurOut = from.eurOut,
        rubIn = from.rubIn,
        rubOut = from.rubOut
    )
}