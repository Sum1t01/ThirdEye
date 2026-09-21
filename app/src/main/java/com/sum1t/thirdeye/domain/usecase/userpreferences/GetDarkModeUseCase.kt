package com.sum1t.thirdeye.domain.usecase.userpreferences

import kotlinx.coroutines.flow.Flow

interface GetDarkModeUseCase {
    fun invoke(): Flow<Boolean>
}
