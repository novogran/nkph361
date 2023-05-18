package com.nkph361.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkph361.domain.ExchangeRateUseCase
import com.nkph361.presentation.mapper.ExchangeRateEntityMapper
import com.nkph361.presentation.mapper.MainFragmentUiStateMapper
import com.nkph361.presentation.ui.state.MainFragmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exchangeRateEntityMapper: ExchangeRateEntityMapper,
    private val mainFragmentUiStateMapper: MainFragmentUiStateMapper,
) : ViewModel() {

    @Inject
    lateinit var exchangeRateUseCase: ExchangeRateUseCase

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main

    private var _mainFragmentUiState =
        MutableStateFlow(MainFragmentUiState())

    val mainFragmentUiState: StateFlow<MainFragmentUiState> =
        _mainFragmentUiState.asStateFlow()

    fun loadData(city: String) {

        if (city == _mainFragmentUiState.value.city) return

        _mainFragmentUiState.update { currentState ->
            currentState.copy(
                loadComplete = false,
                loadInProgress = true,
                loadError = false
            )
        }

        viewModelScope.launch(ioDispatcher) {
            try {
                val exchangeEntity = exchangeRateUseCase.execute(city)
                val exchangeRateModel = exchangeRateEntityMapper.map(exchangeEntity)
                val uiData = mainFragmentUiStateMapper.map(exchangeRateModel)
                withContext(mainDispatcher) {
                    _mainFragmentUiState.update { currentState ->
                        currentState.copy(
                            city = city,
                            usdIn = uiData.usdIn,
                            usdOut = uiData.usdOut,
                            eurIn = uiData.eurIn,
                            eurOut = uiData.eurOut,
                            rubIn = uiData.rubIn,
                            rubOut = uiData.rubOut,
                            loadComplete = true,
                            loadInProgress = false
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d("TAG", e.toString())
                _mainFragmentUiState.update { currentState ->
                    currentState.copy(
                        loadInProgress = false,
                        loadError = true
                    )
                }
            }
        }
    }
}