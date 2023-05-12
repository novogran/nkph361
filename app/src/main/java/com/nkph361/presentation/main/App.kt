package com.nkph361.presentation.main

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App @Inject constructor() : Application() {}