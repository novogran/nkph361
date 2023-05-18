package com.nkph361.presentation.ui.state

data class MainFragmentUiState(
    val city: String = "",
    val usdIn: Double = 0.0,
    val usdOut: Double = 0.0,
    val eurIn: Double = 0.0,
    val eurOut: Double = 0.0,
    val rubIn: Double = 0.0,
    val rubOut: Double = 0.0,
    val loadComplete: Boolean = false,
    val loadInProgress: Boolean = false,
    val loadError: Boolean = false
)
