package com.sum1t.thirdeye.data.usecase.userpreferences

import com.sum1t.thirdeye.domain.model.ThemePalette
import com.sum1t.thirdeye.domain.repository.userpreferences.UserPreferencesDataStore
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetThemePaletteUseCase
import kotlinx.coroutines.flow.Flow

class GetThemePaletteUseCaseImpl(
    private val userPreferencesDataStore: UserPreferencesDataStore
) : GetThemePaletteUseCase {
    override fun invoke(): Flow<ThemePalette> {
        return userPreferencesDataStore.selectedThemePalette
    }
}
