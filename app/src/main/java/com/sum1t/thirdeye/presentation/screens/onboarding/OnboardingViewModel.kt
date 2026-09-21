package com.sum1t.thirdeye.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sum1t.thirdeye.domain.model.ThemePalette
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetDarkModeUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetThemePaletteUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.SetDarkModeUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.SetOnboardingStatusUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.SetThemePaletteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OnboardingUiState(
    val isDarkMode: Boolean = false,
    val palette: ThemePalette = ThemePalette.Default
)

sealed class OnboardingEvent {
    data object CompleteOnboarding : OnboardingEvent()
    data object ToggleDarkMode : OnboardingEvent()
    data class SelectPalette(val palette: ThemePalette) : OnboardingEvent()
}

class OnboardingViewModel(
    private val getDarkModeUseCase: GetDarkModeUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase,
    private val getThemePaletteUseCase: GetThemePaletteUseCase,
    private val setThemePaletteUseCase: SetThemePaletteUseCase,
    private val setOnboardingStatusUseCase: SetOnboardingStatusUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getDarkModeUseCase.invoke().collect { darkMode ->
                _uiState.update { it.copy(isDarkMode = darkMode) }
            }
        }
        viewModelScope.launch {
            getThemePaletteUseCase.invoke().collect { palette ->
                _uiState.update { it.copy(palette = palette) }
            }
        }
    }

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            is OnboardingEvent.CompleteOnboarding -> viewModelScope.launch {
                setOnboardingStatusUseCase.invoke(true)
            }

            is OnboardingEvent.ToggleDarkMode -> viewModelScope.launch {
                setDarkModeUseCase.invoke(!_uiState.value.isDarkMode)
            }

            is OnboardingEvent.SelectPalette -> viewModelScope.launch {
                setThemePaletteUseCase.invoke(event.palette)
            }
        }
    }
}
