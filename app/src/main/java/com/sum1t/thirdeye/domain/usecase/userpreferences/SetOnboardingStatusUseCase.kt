package com.sum1t.thirdeye.domain.usecase.userpreferences

interface SetOnboardingStatusUseCase {
    suspend fun invoke(completed: Boolean)
}
