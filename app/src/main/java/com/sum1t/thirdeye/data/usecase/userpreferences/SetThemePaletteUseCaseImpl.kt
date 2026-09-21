package com.sum1t.thirdeye.data.usecase.userpreferences

import com.sum1t.thirdeye.domain.model.ThemePalette
import com.sum1t.thirdeye.domain.repository.userpreferences.UserPreferencesDataStore
import com.sum1t.thirdeye.domain.usecase.userpreferences.SetThemePaletteUseCase

class SetThemePaletteUseCaseImpl(
    private val userPreferencesDataStore: UserPreferencesDataStore
) : SetThemePaletteUseCase {
    override suspend fun invoke(palette: ThemePalette) {
        userPreferencesDataStore.setThemePalette(palette)
    }
}
