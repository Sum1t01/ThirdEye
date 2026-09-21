package com.sum1t.thirdeye.domain.usecase.userpreferences

import kotlinx.coroutines.flow.Flow

interface GetOnboardingStatusUseCase {
    fun invoke(): Flow<Boolean>
}
