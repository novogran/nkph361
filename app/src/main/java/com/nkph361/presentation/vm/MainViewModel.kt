package com.nkph361.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkph361.domain.ExchangeRateUseCase
import com.nkph361.presentation.mapper.ExchangeRateEntityMapper
import com.nkph361.presentation.model.ExchangeRateViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeRateEntityMapper: ExchangeRateEntityMapper
) : ViewModel() {

    @Inject
    lateinit var exchangeRateUseCase: ExchangeRateUseCase

    private var exchangeRateMutableStateFlow =
        MutableStateFlow(ExchangeRateViewData())

    val exchangeRateStateFlow: StateFlow<ExchangeRateViewData> =
        exchangeRateMutableStateFlow.asStateFlow()

    fun loadData(city: String) {
        exchangeRateMutableStateFlow.value =
            ExchangeRateViewData(
                inProgress = true,
                loadError = false
            )
        viewModelScope.launch(Dispatchers.IO) {
            exchangeRateMutableStateFlow.value = try {
                exchangeRateEntityMapper.map(exchangeRateUseCase.execute(city))
            } catch (e: Exception) {
                Log.d("TAG", e.toString())
                ExchangeRateViewData(
                    loadError = true
                )
            }
        }
    }
}