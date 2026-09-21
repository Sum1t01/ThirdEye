package com.sum1t.thirdeye.domain.repository.volumekey

import com.sum1t.thirdeye.domain.model.VolumeKey
import kotlinx.coroutines.flow.Flow

/**
 * Hardware volume presses reach the Activity, not Compose. The Activity
 * dispatches them here and screens observe, so no screen needs an Activity
 * reference to use the physical buttons.
 */
interface VolumeKeyEvents {
    val events: Flow<VolumeKey>

    fun dispatch(key: VolumeKey)
}
