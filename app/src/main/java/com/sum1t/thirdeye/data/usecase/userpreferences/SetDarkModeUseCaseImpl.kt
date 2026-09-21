package com.sum1t.thirdeye.data.usecase.userpreferences

import com.sum1t.thirdeye.domain.repository.userpreferences.UserPreferencesDataStore
import com.sum1t.thirdeye.domain.usecase.userpreferences.SetDarkModeUseCase

class SetDarkModeUseCaseImpl(
    private val userPreferencesDataStore: UserPreferencesDataStore
) : SetDarkModeUseCase {
    override suspend fun invoke(enabled: Boolean) {
        userPreferencesDataStore.setDarkMode(enabled)
    }
}
