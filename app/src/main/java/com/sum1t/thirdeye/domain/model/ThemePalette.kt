package com.sum1t.thirdeye.domain.model

enum class ThemePalette {
    DEFAULT,
    HIGH_CONTRAST,
    AMBER,
    OCEAN,
    MONOCHROME;

    companion object {
        val Default: ThemePalette = DEFAULT

        /**
         * Preferences store the palette as a plain string, so an empty value
         * (pref never written) or an unknown one (palette renamed or removed
         * in a later version) must fall back rather than throw.
         */
        fun fromName(name: String?): ThemePalette =
            entries.firstOrNull { it.name == name } ?: Default
    }
}
