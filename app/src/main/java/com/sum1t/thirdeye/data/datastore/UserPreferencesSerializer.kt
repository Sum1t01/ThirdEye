package com.sum1t.thirdeye.data.datastore

import androidx.datastore.core.Serializer
import com.sum1t.thirdeye.datastore.UserPreferences
import com.sum1t.thirdeye.domain.model.ThemePalette
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer : Serializer<UserPreferences> {

    override val defaultValue: UserPreferences =
        UserPreferences.newBuilder()
            .setDarkMode(false)
            .setOnboardingCompleted(false)
            .setHapticsEnabled(true)
            .setSelectedThemePalette(ThemePalette.Default.name)
            .build()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        return try {
            UserPreferences.parseFrom(input)
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: UserPreferences,
        output: OutputStream
    ) {
        t.writeTo(output)
    }
}
