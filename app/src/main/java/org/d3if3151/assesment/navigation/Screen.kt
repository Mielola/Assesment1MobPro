package org.d3if3151.assesment.navigation

import org.d3if3151.assesment.Screen.KEY_ID_CATATAN

sealed class Screen(val route: String) {
    data object Login: Screen("Login")
    data object  Home: Screen("MainScreen")
    data object About: Screen("About")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_CATATAN}"){
        fun withId(id: Long) = "detailScreen/$id"
    }
}