package com.sum1t.thirdeye.data.volumekey

import com.sum1t.thirdeye.domain.model.VolumeKey
import com.sum1t.thirdeye.domain.repository.volumekey.VolumeKeyEvents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class VolumeKeyEventsImpl : VolumeKeyEvents {

    // Buffered and non-suspending: a key press must never block the main thread
    // and must not be dropped while a screen is mid-recomposition.
    private val _events = MutableSharedFlow<VolumeKey>(extraBufferCapacity = 8)

    override val events: Flow<VolumeKey> = _events.asSharedFlow()

    override fun dispatch(key: VolumeKey) {
        _events.tryEmit(key)
    }
}
