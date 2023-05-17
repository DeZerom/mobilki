package com.example.mobilki.presentation.nav

enum class NavRoutes(
    val argName: String = "",
    private val route: String
) {
    AUTH_PAGER(
        route = "auth"
    ),
    GREETINGS(
        route = "greetings",
        argName = "userId"
    ),
    WEATHER(
        route = "weather"
    ),
    HOURS_FORECAST(
        route = "hours",
        argName = "location"
    );

    fun rawRoute(): String {
        return if (argName.isNotBlank())
            "$route/{$argName}"
        else
            route
    }

    fun withArg(arg: Int): String {
        return "$route/$arg"
    }

    fun withArg(arg: Array<Float>): String {
        return "$route/$arg"
    }
}
