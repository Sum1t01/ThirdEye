package com.sum1t.thirdeye.domain.usecase.userpreferences

import com.sum1t.thirdeye.domain.model.ThemePalette
import kotlinx.coroutines.flow.Flow

interface GetThemePaletteUseCase {
    fun invoke(): Flow<ThemePalette>
}
