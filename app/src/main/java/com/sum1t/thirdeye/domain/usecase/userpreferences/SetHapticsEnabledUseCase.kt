package com.sum1t.thirdeye.domain.usecase.userpreferences

interface SetHapticsEnabledUseCase {
    suspend fun invoke(enabled: Boolean)
}
