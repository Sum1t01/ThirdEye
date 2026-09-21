package com.sum1t.thirdeye.presentation

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.sum1t.thirdeye.common.navigation.Navigation
import com.sum1t.thirdeye.domain.model.VolumeKey
import com.sum1t.thirdeye.domain.repository.volumekey.VolumeKeyEvents
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetDarkModeUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetThemePaletteUseCase
import com.sum1t.thirdeye.domain.model.ThemePalette
import com.sum1t.thirdeye.ui.theme.ThirdEyeTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getDarkModeUseCase: GetDarkModeUseCase by inject()
    private val getThemePaletteUseCase: GetThemePaletteUseCase by inject()
    private val volumeKeyEvents: VolumeKeyEvents by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkMode by getDarkModeUseCase.invoke().collectAsState(
                initial = isSystemInDarkTheme()
            )

            val palette by getThemePaletteUseCase.invoke().collectAsState(
                initial = ThemePalette.Default
            )

            ThirdEyeTheme(palette = palette, darkTheme = isDarkMode) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { _ ->
                    Box(modifier = Modifier) {
                        Navigation()
                    }
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                if (event?.repeatCount == 0) volumeKeyEvents.dispatch(VolumeKey.UP)
                true
            }

            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                if (event?.repeatCount == 0) volumeKeyEvents.dispatch(VolumeKey.DOWN)
                true
            }

            else -> super.onKeyDown(keyCode, event)
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_VOLUME_DOWN -> true
            else -> super.onKeyUp(keyCode, event)
        }
    }
}
