package org.d3if3151.assesment.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3151.assesment.Screen.AboutScreen
import org.d3if3151.assesment.Screen.DetailScreen
import org.d3if3151.assesment.Screen.KEY_ID_CATATAN
import org.d3if3151.assesment.Screen.MainScreen

@Composable
fun setupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
    ){
        composable(route = Screen.Login.route){
           LoginScreen(navController)
        }
        composable(route = Screen.Home.route){
            MainScreen(navController)
        }
        composable(route = Screen.About.route){
            AboutScreen(navController)
        }
        composable(route = Screen.FormBaru.route){
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_CATATAN){ type = NavType.LongType }
            )
        ){
                navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_CATATAN)
            DetailScreen(navController, id)
        }
    }
}