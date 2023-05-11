package com.nkph361.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkph361.domain.ExchangeRateUseCase
import com.nkph361.presentation.entity.ExchangeRateViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel (
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    @Inject
    lateinit var exchangeRateUseCase: ExchangeRateUseCase

    private var exchangeRateMutableStateFlow =
        MutableStateFlow(ExchangeRateViewData())

    val exchangeRateStateFlow: StateFlow<ExchangeRateViewData> =
        exchangeRateMutableStateFlow.asStateFlow()

    fun loadData(city: String) {
        viewModelScope.launch(ioDispatcher) {
            val exchangeRateEntity = exchangeRateUseCase.execute(city)
            exchangeRateMutableStateFlow.value = ExchangeRateViewData(
                exchangeRateEntity.name,
                exchangeRateEntity.usdIn,
                exchangeRateEntity.usdOut,
                exchangeRateEntity.eurIn,
                exchangeRateEntity.eurOut,
                exchangeRateEntity.rubIn,
                exchangeRateEntity.rubOut,
            )
        }
    }
}