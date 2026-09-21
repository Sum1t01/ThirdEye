package com.sum1t.thirdeye.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.sum1t.thirdeye.datastore.UserPreferences
import com.sum1t.thirdeye.domain.model.ThemePalette
import com.sum1t.thirdeye.domain.repository.userpreferences.UserPreferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
    fileName = "user_prefs.pb",
    serializer = UserPreferencesSerializer
)

class UserPreferencesDataStoreImpl(
    private val context: Context
) : UserPreferencesDataStore {

    override val darkMode: Flow<Boolean>
        get() = context.userPreferencesStore.data.map { it.darkMode }


    override val onBoardingCompleted: Flow<Boolean>
        get() = context.userPreferencesStore.data.map { it.onboardingCompleted }

    override val hapticsEnabled: Flow<Boolean>
        get() = context.userPreferencesStore.data.map { it.hapticsEnabled }

    override val selectedThemePalette: Flow<ThemePalette>
        get() = context.userPreferencesStore.data.map {
            ThemePalette.fromName(it.selectedThemePalette)
        }

    override suspend fun setDarkMode(enabled: Boolean) {
        context.userPreferencesStore.updateData { prefs ->
            prefs.toBuilder().setDarkMode(enabled).build()
        }
    }

    override suspend fun setOnboardingStatus(completed: Boolean) {
        context.userPreferencesStore.updateData { prefs ->
            prefs.toBuilder().setOnboardingCompleted(completed).build()
        }
    }

    override suspend fun setHapticsEnabled(enabled: Boolean) {
        context.userPreferencesStore.updateData { prefs ->
            prefs.toBuilder().setHapticsEnabled(enabled).build()
        }
    }

    override suspend fun setThemePalette(palette: ThemePalette) {
        context.userPreferencesStore.updateData { prefs ->
            prefs.toBuilder().setSelectedThemePalette(palette.name).build()
        }
    }
}
