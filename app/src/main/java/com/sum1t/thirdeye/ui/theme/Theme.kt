package com.sum1t.thirdeye.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.sum1t.thirdeye.domain.model.ThemePalette

data class ThemePaletteSet(
    val light: ColorScheme,
    val dark: ColorScheme
)

object ThemeRegistry {

    fun get(palette: ThemePalette): ThemePaletteSet {
        return when (palette) {
            ThemePalette.DEFAULT ->
                ThemePaletteSet(DefaultPalette.Light, DefaultPalette.Dark)

            ThemePalette.HIGH_CONTRAST ->
                ThemePaletteSet(HighContrastPalette.Light, HighContrastPalette.Dark)

            ThemePalette.AMBER ->
                ThemePaletteSet(AmberPalette.Light, AmberPalette.Dark)

            ThemePalette.OCEAN ->
                ThemePaletteSet(OceanPalette.Light, OceanPalette.Dark)

            ThemePalette.MONOCHROME ->
                ThemePaletteSet(MonoPalette.Light, MonoPalette.Dark)
        }
    }
}

@Composable
fun ThirdEyeTheme(
    palette: ThemePalette = ThemePalette.Default,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorSet = ThemeRegistry.get(palette)
    val colorScheme = if (darkTheme) colorSet.dark else colorSet.light

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
