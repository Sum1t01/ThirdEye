package com.sum1t.thirdeye.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.sum1t.thirdeye.domain.model.ThemePalette

object DefaultPalette {

    val Light = lightColorScheme(
        primary = Color(0xFF2E5AAC),
        onPrimary = Color.White,
        secondary = Color(0xFF4A6572),
        onSecondary = Color.White,
        tertiary = Color(0xFF00897B),
        background = Color(0xFFF7F9FC),
        onBackground = Color(0xFF16181C),
        surface = Color.White,
        onSurface = Color(0xFF16181C),
        surfaceVariant = Color(0xFFDDE2EB),
        onSurfaceVariant = Color(0xFF42474E),
        error = Color(0xFFBA1A1A),
        onError = Color.White
    )

    val Dark = darkColorScheme(
        primary = Color(0xFF9FC0FF),
        onPrimary = Color(0xFF00305F),
        secondary = Color(0xFFB4C9D6),
        onSecondary = Color(0xFF1E333F),
        tertiary = Color(0xFF5CDBCA),
        background = Color(0xFF101418),
        onBackground = Color(0xFFE2E2E6),
        surface = Color(0xFF1A1E23),
        onSurface = Color(0xFFE2E2E6),
        surfaceVariant = Color(0xFF42474E),
        onSurfaceVariant = Color(0xFFC2C7CF),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005)
    )
}

/**
 * Maximum luminance separation, no mid-tones. The palette to reach for when a
 * low-vision user needs every edge to be unambiguous.
 */
object HighContrastPalette {

    val Light = lightColorScheme(
        primary = Color(0xFF000000),
        onPrimary = Color.White,
        secondary = Color(0xFF1A1A1A),
        onSecondary = Color.White,
        tertiary = Color(0xFF00008B),
        background = Color.White,
        onBackground = Color.Black,
        surface = Color.White,
        onSurface = Color.Black,
        surfaceVariant = Color(0xFFE8E8E8),
        onSurfaceVariant = Color.Black,
        outline = Color.Black,
        error = Color(0xFFB00020),
        onError = Color.White
    )

    val Dark = darkColorScheme(
        primary = Color(0xFFFFFFFF),
        onPrimary = Color.Black,
        secondary = Color(0xFFE8E8E8),
        onSecondary = Color.Black,
        tertiary = Color(0xFF8FD8FF),
        background = Color.Black,
        onBackground = Color.White,
        surface = Color.Black,
        onSurface = Color.White,
        surfaceVariant = Color(0xFF1A1A1A),
        onSurfaceVariant = Color.White,
        outline = Color.White,
        error = Color(0xFFFF6E6E),
        onError = Color.Black
    )
}

/** Yellow-on-black / black-on-yellow, the classic low-vision pairing. */
object AmberPalette {

    val Light = lightColorScheme(
        primary = Color(0xFF6B4E00),
        onPrimary = Color.White,
        secondary = Color(0xFF7A5900),
        onSecondary = Color.White,
        tertiary = Color(0xFF4F3D00),
        background = Color(0xFFFFF4CC),
        onBackground = Color(0xFF1F1A00),
        surface = Color(0xFFFFFBEF),
        onSurface = Color(0xFF1F1A00),
        surfaceVariant = Color(0xFFF0E3B8),
        onSurfaceVariant = Color(0xFF4C4530),
        error = Color(0xFFBA1A1A),
        onError = Color.White
    )

    val Dark = darkColorScheme(
        primary = Color(0xFFFFD230),
        onPrimary = Color(0xFF3A2E00),
        secondary = Color(0xFFFFC300),
        onSecondary = Color(0xFF3A2E00),
        tertiary = Color(0xFFFFE594),
        background = Color(0xFF0A0A00),
        onBackground = Color(0xFFFFE07A),
        surface = Color(0xFF171400),
        onSurface = Color(0xFFFFE07A),
        surfaceVariant = Color(0xFF3A3520),
        onSurfaceVariant = Color(0xFFFFD230),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005)
    )
}

object OceanPalette {

    val Light = lightColorScheme(
        primary = Color(0xFF00629E),
        onPrimary = Color.White,
        secondary = Color(0xFF00687C),
        onSecondary = Color.White,
        tertiary = Color(0xFF6C63FF),
        background = Color(0xFFF4FAFF),
        onBackground = Color(0xFF101820),
        surface = Color.White,
        onSurface = Color(0xFF101820),
        surfaceVariant = Color(0xFFD5E4F2),
        onSurfaceVariant = Color(0xFF3F4A52),
        error = Color(0xFFBA1A1A),
        onError = Color.White
    )

    val Dark = darkColorScheme(
        primary = Color(0xFF8ECDFF),
        onPrimary = Color(0xFF003354),
        secondary = Color(0xFF85D2E6),
        onSecondary = Color(0xFF00363F),
        tertiary = Color(0xFFC2C0FF),
        background = Color(0xFF0B1219),
        onBackground = Color(0xFFE0E3E7),
        surface = Color(0xFF151C23),
        onSurface = Color(0xFFE0E3E7),
        surfaceVariant = Color(0xFF3F4A52),
        onSurfaceVariant = Color(0xFFBFC8D2),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005)
    )
}

object MonoPalette {

    val Light = lightColorScheme(
        primary = Color(0xFF2B2B2B),
        onPrimary = Color.White,
        secondary = Color(0xFF5C5C5C),
        onSecondary = Color.White,
        tertiary = Color(0xFF7A7A7A),
        background = Color(0xFFFAFAFA),
        onBackground = Color(0xFF1A1A1A),
        surface = Color.White,
        onSurface = Color(0xFF1A1A1A),
        surfaceVariant = Color(0xFFE3E3E3),
        onSurfaceVariant = Color(0xFF454545),
        error = Color(0xFFBA1A1A),
        onError = Color.White
    )

    val Dark = darkColorScheme(
        primary = Color(0xFFD6D6D6),
        onPrimary = Color(0xFF1A1A1A),
        secondary = Color(0xFFB0B0B0),
        onSecondary = Color(0xFF1A1A1A),
        tertiary = Color(0xFF8F8F8F),
        background = Color(0xFF121212),
        onBackground = Color(0xFFE6E6E6),
        surface = Color(0xFF1E1E1E),
        onSurface = Color(0xFFE6E6E6),
        surfaceVariant = Color(0xFF3A3A3A),
        onSurfaceVariant = Color(0xFFC6C6C6),
        error = Color(0xFFFFB4AB),
        onError = Color(0xFF690005)
    )
}

/** Single representative colour per palette, for compact swatch previews. */
fun themePreviewColor(palette: ThemePalette): Color {
    return when (palette) {
        ThemePalette.DEFAULT -> Color(0xFF2E5AAC)
        ThemePalette.HIGH_CONTRAST -> Color(0xFF111111)
        ThemePalette.AMBER -> Color(0xFFFFC300)
        ThemePalette.OCEAN -> Color(0xFF0077FF)
        ThemePalette.MONOCHROME -> Color(0xFF7A7A7A)
    }
}
