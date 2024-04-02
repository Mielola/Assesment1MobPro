package org.d3if3151.assesment.navigation

sealed class Screen(val route: String) {
    data object Login: Screen("Login")
    data object  Home: Screen("MainScreen")
    data object About: Screen("About")
}