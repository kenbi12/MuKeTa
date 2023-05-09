package at.ac.fhcampuswien.muketa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.muketa.screens.StartScreen
import at.ac.fhcampuswien.proman.screens.SplashScreen


@Composable
fun ScreensNavigation(navController : NavHostController = rememberNavController()){

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route

    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.StartScreen.route) {
            StartScreen(navController = navController)
        }

        composable(route = Screen.LoginScreen.route) {
            StartScreen(navController = navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            StartScreen(navController = navController)
        }


    }
}