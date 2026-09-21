package com.sum1t.thirdeye.domain.repository.userpreferences

import com.sum1t.thirdeye.domain.model.ThemePalette
import kotlinx.coroutines.flow.Flow

interface UserPreferencesDataStore {
    val darkMode: Flow<Boolean>
    val onBoardingCompleted: Flow<Boolean>
    val hapticsEnabled: Flow<Boolean>
    val selectedThemePalette: Flow<ThemePalette>

    suspend fun setDarkMode(enabled: Boolean)
    suspend fun setOnboardingStatus(completed: Boolean)
    suspend fun setHapticsEnabled(enabled: Boolean)
    suspend fun setThemePalette(palette: ThemePalette)
}
