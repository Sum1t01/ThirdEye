package com.sum1t.thirdeye.data.usecase.userpreferences

import com.sum1t.thirdeye.domain.repository.userpreferences.UserPreferencesDataStore
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetDarkModeUseCase
import kotlinx.coroutines.flow.Flow

class GetDarkModeUseCaseImpl(
    private val userPreferencesDataStore: UserPreferencesDataStore
) : GetDarkModeUseCase {
    override fun invoke(): Flow<Boolean> {
        return userPreferencesDataStore.darkMode
    }
}
