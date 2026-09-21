package com.sum1t.thirdeye.domain.usecase.userpreferences

import kotlinx.coroutines.flow.Flow

interface GetHapticsEnabledUseCase {
    fun invoke(): Flow<Boolean>
}
