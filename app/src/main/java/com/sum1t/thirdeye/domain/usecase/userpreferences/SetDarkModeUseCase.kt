package com.sum1t.thirdeye.domain.usecase.userpreferences

interface SetDarkModeUseCase {
    suspend fun invoke(enabled: Boolean)
}
