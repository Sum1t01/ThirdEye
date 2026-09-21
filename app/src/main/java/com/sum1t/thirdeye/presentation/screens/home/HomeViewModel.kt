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
}

class HomeViewModel(
    private val speaker: Speaker,
    volumeKeyEvents: VolumeKeyEvents
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        // Spoken on entry so the control is discoverable without sight.
        speaker.speak(HOME_PROMPT)

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
            else -> {}
        }
    }

    private fun setCameraOn(on: Boolean) {
        _uiState.update { it.copy(isCameraOn = on) }
    }

    private companion object {
        const val HOME_PROMPT = "Press volume up to open camera"
    }
}
