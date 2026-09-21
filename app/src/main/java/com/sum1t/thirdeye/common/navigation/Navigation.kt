package com.sum1t.thirdeye.common.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sum1t.thirdeye.common.utils.Screen
import com.sum1t.thirdeye.domain.usecase.userpreferences.GetOnboardingStatusUseCase
import com.sum1t.thirdeye.presentation.screens.home.HomeScreen
import com.sum1t.thirdeye.presentation.screens.onboarding.OnboardingScreen
import org.koin.compose.koinInject

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val getOnboardingStatusUseCase: GetOnboardingStatusUseCase = koinInject()
    val isOnboardingCompleted by getOnboardingStatusUseCase.invoke().collectAsState(
        initial = null
    )

    NavHost(
        navController = navController,
        startDestination = when (isOnboardingCompleted) {
            null -> Screen.Default.route
            true -> Screen.Home.route
            false -> Screen.OnBoarding.route
        },
        enterTransition = { smoothEnter() },
        exitTransition = { smoothExit() },
        popEnterTransition = { smoothPopEnter() },
        popExitTransition = { smoothPopExit() }
    ) {

        composable(Screen.Default.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        composable(Screen.OnBoarding.route) {
            OnboardingScreen(
                onContinue = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnBoarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}

private const val DURATION = 280
private val easing = FastOutSlowInEasing

private fun smoothEnter() =
    fadeIn(
        animationSpec = tween(DURATION, easing = easing)
    ) + slideInHorizontally(
        initialOffsetX = { it / 6 },
        animationSpec = tween(DURATION, easing = easing)
    )

private fun smoothExit() =
    fadeOut(
        animationSpec = tween(DURATION - 50, easing = easing)
    ) + slideOutHorizontally(
        targetOffsetX = { -it / 8 },
        animationSpec = tween(DURATION, easing = easing)
    )

private fun smoothPopEnter() =
    fadeIn(
        animationSpec = tween(DURATION, easing = easing)
    ) + slideInHorizontally(
        initialOffsetX = { -it / 8 },
        animationSpec = tween(DURATION, easing = easing)
    )

private fun smoothPopExit() =
    fadeOut(
        animationSpec = tween(DURATION - 50, easing = easing)
    ) + slideOutHorizontally(
        targetOffsetX = { it / 6 },
        animationSpec = tween(DURATION, easing = easing)
    )
