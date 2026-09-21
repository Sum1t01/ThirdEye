package com.sum1t.thirdeye.di

import com.sum1t.thirdeye.data.datastore.UserPreferencesDataStoreImpl
import com.sum1t.thirdeye.data.speech.SpeakerImpl
import com.sum1t.thirdeye.data.volumekey.VolumeKeyEventsImpl
import com.sum1t.thirdeye.data.usecase.userpreferences.GetDarkModeUseCaseImpl
import com.sum1t.thirdeye.data.usecase.userpreferences.GetHapticsEnabledUseCaseImpl
import com.sum1t.thirdeye.data.usecase.userpreferences.GetOnboardingStatusUseCaseImpl
import com.sum1t.thirdeye.data.usecase.userpreferences.SetDarkModeUseCaseImpl
import com.sum1t.thirdeye.data.usecase.userpreferences.SetHapticsEnabledUseCaseImpl
import com.sum1t.thirdeye.data.usecase.userpreferences.GetThemePaletteUseCaseImpl
import com.sum1t.thirdeye.data.usecase.userpreferences.SetOnboardingStatusUseCaseImpl
import com.sum1t.thirdeye.data.usecase.userpreferences.SetThemePaletteUseCaseImpl
import com.sum1t.thirdeye.domain.repository.userpreferences.UserPreferencesDataStore
import com.sum1t.thirdeye.domain.repository.speech.Speaker
import com.sum1t.thirdeye.domain.repository.volumekey.VolumeKeyEvents
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetDarkModeUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetHapticsEnabledUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetOnboardingStatusUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.SetDarkModeUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.SetHapticsEnabledUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetThemePaletteUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.SetOnboardingStatusUseCase
import com.sum1t.thirdeye.domain.usecase.userpreferences.SetThemePaletteUseCase
import com.sum1t.thirdeye.presentation.screens.home.HomeViewModel
import com.sum1t.thirdeye.presentation.screens.onboarding.OnboardingViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModules by lazy {
    listOf(
        coroutineModule,
        localStoreModule,
        platformModule,
        useCaseModule,
        viewModelModule
    )
}

val coroutineModule: Module = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
}

val localStoreModule: Module = module {
    single<UserPreferencesDataStore> { UserPreferencesDataStoreImpl(androidContext()) }
}

val platformModule: Module = module {
    // Single instance: the Activity dispatches into the same bus the screens observe.
    single<VolumeKeyEvents> { VolumeKeyEventsImpl() }

    // One engine for the whole app; connecting to it is not cheap.
    single<Speaker> { SpeakerImpl(androidContext()) }
}

val useCaseModule: Module = module {
    factory<GetDarkModeUseCase> { GetDarkModeUseCaseImpl(get()) }
    factory<SetDarkModeUseCase> { SetDarkModeUseCaseImpl(get()) }
    factory<GetOnboardingStatusUseCase> { GetOnboardingStatusUseCaseImpl(get()) }
    factory<SetOnboardingStatusUseCase> { SetOnboardingStatusUseCaseImpl(get()) }
    factory<GetHapticsEnabledUseCase> { GetHapticsEnabledUseCaseImpl(get()) }
    factory<SetHapticsEnabledUseCase> { SetHapticsEnabledUseCaseImpl(get()) }
    factory<GetThemePaletteUseCase> { GetThemePaletteUseCaseImpl(get()) }
    factory<SetThemePaletteUseCase> { SetThemePaletteUseCaseImpl(get()) }
}

val viewModelModule: Module = module {
    viewModel { OnboardingViewModel(get(), get(), get(), get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
}
