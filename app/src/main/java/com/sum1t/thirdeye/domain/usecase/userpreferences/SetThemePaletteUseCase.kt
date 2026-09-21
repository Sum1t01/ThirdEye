package com.sum1t.thirdeye.domain.usecase.userpreferences

import com.sum1t.thirdeye.domain.model.ThemePalette

interface SetThemePaletteUseCase {
    suspend fun invoke(palette: ThemePalette)
}
