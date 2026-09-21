package com.sum1t.thirdeye.data.usecase.userpreferences

import com.sum1t.thirdeye.domain.repository.userpreferences.UserPreferencesDataStore
import com.sum1t.thirdeye.domain.usecase.userpreferences.SetHapticsEnabledUseCase

class SetHapticsEnabledUseCaseImpl(
    private val userPreferencesDataStore: UserPreferencesDataStore
) : SetHapticsEnabledUseCase {
    override suspend fun invoke(enabled: Boolean) {
        userPreferencesDataStore.setHapticsEnabled(enabled)
    }
}
