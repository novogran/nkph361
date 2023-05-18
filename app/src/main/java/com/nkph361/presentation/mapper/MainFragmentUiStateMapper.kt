package com.nkph361.presentation.mapper

import com.nkph361.comm.Mapper
import com.nkph361.presentation.model.ExchangeRateModel
import com.nkph361.presentation.ui.state.MainFragmentUiState
import javax.inject.Inject

class MainFragmentUiStateMapper @Inject constructor() :
    Mapper<ExchangeRateModel, MainFragmentUiState> {
    override fun map(from: ExchangeRateModel) = MainFragmentUiState(
        usdIn = from.usdIn,
        usdOut = from.eurOut,
        eurIn = from.eurIn,
        eurOut = from.eurOut,
        rubIn = from.rubIn,
        rubOut = from.rubOut
    )
}