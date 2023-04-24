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
    );

    fun rawRoute(): String {
        return if (route.isNotBlank())
            "$route/{$argName}"
        else
            route
    }

    fun withArg(arg: Int): String {
        return "$route/$arg"
    }
}
