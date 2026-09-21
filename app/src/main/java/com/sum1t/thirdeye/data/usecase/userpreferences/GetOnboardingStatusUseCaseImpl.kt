package com.sum1t.thirdeye.data.usecase.userpreferences

import com.sum1t.thirdeye.domain.repository.userpreferences.UserPreferencesDataStore
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetOnboardingStatusUseCase
import kotlinx.coroutines.flow.Flow

class GetOnboardingStatusUseCaseImpl(
    private val userPreferencesDataStore: UserPreferencesDataStore
) : GetOnboardingStatusUseCase {
    override fun invoke(): Flow<Boolean> {
        return userPreferencesDataStore.onBoardingCompleted
    }
}
