package com.nkph361.data.mapper

import com.nkph361.Mapper
import com.nkph361.data.dto.ExchangeRateInfo
import com.nkph361.domain.entity.ExchangeRateEntity
import javax.inject.Inject

class ExchangeRateInfoMapper @Inject constructor() : Mapper<ExchangeRateInfo, ExchangeRateEntity> {
    override fun map(from: ExchangeRateInfo) = ExchangeRateEntity(
        usdIn = from.usdIn,
        usdOut = from.usdOut,
        eurIn = from.eurIn,
        eurOut = from.eurOut,
        rubIn = from.rubIn,
        rubOut = from.rubOut
    )
}