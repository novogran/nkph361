package com.nkph361.presentation.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkph361.domain.ExchangeRateUseCase
import com.nkph361.presentation.entity.ExchangeRateViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var exchangeRateUseCase: ExchangeRateUseCase

    private var exchangeRateMutableStateFlow =
        MutableStateFlow(ExchangeRateViewData())

    val exchangeRateStateFlow: StateFlow<ExchangeRateViewData> =
        exchangeRateMutableStateFlow.asStateFlow()

    fun loadData(city: String) {
        exchangeRateMutableStateFlow.value = ExchangeRateViewData(inProgress = true)
        viewModelScope.launch(Dispatchers.IO) {
            exchangeRateMutableStateFlow.value = try {
                ExchangeRateViewData(
                    inProgress = false
                )
                val exchangeRateEntity = exchangeRateUseCase.execute(city)
                ExchangeRateViewData(
                    exchangeRateEntity.usdIn,
                    exchangeRateEntity.usdOut,
                    exchangeRateEntity.eurIn,
                    exchangeRateEntity.eurOut,
                    exchangeRateEntity.rubIn,
                    exchangeRateEntity.rubOut,
                    loadStatus = true,
                    inProgress = false
                )
            } catch (e: Exception) {
                Log.d("TAG", e.toString())
                ExchangeRateViewData(
                    usdIn = 1.0,
                    usdOut = 1.0,
                    eurIn = 1.0,
                    eurOut = 1.0,
                    rubIn = 1.0,
                    rubOut = 1.0,
                )
            }
        }
    }
}