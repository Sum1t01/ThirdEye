package com.sum1t.thirdeye.common.utils

sealed class Screen(val route: String) {

    data object Default : Screen("default")
    data object OnBoarding : Screen("onboarding")
    data object Home : Screen("home")
    data object Settings : Screen("settings")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
