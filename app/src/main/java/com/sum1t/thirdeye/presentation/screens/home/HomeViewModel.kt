package com.sum1t.thirdeye.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sum1t.thirdeye.domain.model.VolumeKey
import com.sum1t.thirdeye.domain.repository.speech.Speaker
import com.sum1t.thirdeye.domain.repository.volumekey.VolumeKeyEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val isCameraOn: Boolean = false
)

sealed class HomeEvent {
    data object StartCamera : HomeEvent()
    data object StopCamera : HomeEvent()
    data object ToggleCamera : HomeEvent()

    /** Text is resolved by the UI so the ViewModel stays free of resources. */
    data class AnnouncePrompt(val text: String) : HomeEvent()
}

class HomeViewModel(
    private val speaker: Speaker,
    volumeKeyEvents: VolumeKeyEvents
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // Guards against re-speaking when the composition restarts, e.g. on
    // rotation. Once per ViewModel, not once per composition.
    private var hasAnnouncedPrompt = false

    init {
        viewModelScope.launch {
            volumeKeyEvents.events.collect { key ->
                when (key) {
                    VolumeKey.UP -> setCameraOn(true)
                    VolumeKey.DOWN -> setCameraOn(false)
                }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ToggleCamera -> setCameraOn(!_uiState.value.isCameraOn)

            is HomeEvent.AnnouncePrompt -> {
                // Spoken on entry so the control is discoverable without sight.
                if (!hasAnnouncedPrompt) {
                    hasAnnouncedPrompt = true
                    speaker.speak(event.text)
                }
            }

            else -> {}
        }
    }

    private fun setCameraOn(on: Boolean) {
        _uiState.update { it.copy(isCameraOn = on) }
    }
}
