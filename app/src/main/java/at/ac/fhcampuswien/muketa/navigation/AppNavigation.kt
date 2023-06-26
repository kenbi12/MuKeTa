package at.ac.fhcampuswien.muketa.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import at.ac.fhcampuswien.muketa.screens.StartScreen
import at.ac.fhcampuswien.muketa.viewmodels.LoginViewModel
import at.ac.fhcampuswien.muketa.screens.*


@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel, mainContext: Context
) {

    NavHost(
        navController = navController,
        startDestination = NestedRoute.Login.name//Screen.SplashScreen.route

    ) {

        authGraph(navController, loginViewModel)
        homeGraph(navController, loginViewModel, mainContext)
    }
}

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
) {

    navigation(
        startDestination = Screen.SplashScreen.route,
        route = NestedRoute.Login.name
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.StartScreen.route) {
            StartScreen(navController = navController)
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                onNavToHomePage = {
                    navController.navigate(NestedRoute.Main.name) {
                        launchSingleTop = true
                        popUpTo(route = Screen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                },
                loginViewModel = loginViewModel

            ) {
                navController.navigate(Screen.RegisterScreen.route) {
                    launchSingleTop = true
                    popUpTo(Screen.LoginScreen.route) {
                        inclusive = true
                    }
                }
            }
        }


        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(
                onNavToHomePage = {
                    navController.navigate(NestedRoute.Main.name) {
                        popUpTo(Screen.RegisterScreen.route) {
                            inclusive = true
                        }
                    }
                },
                loginViewModel = loginViewModel
            ) {
                navController.navigate(Screen.LoginScreen.route)
            }

        }

    }
}

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    context: Context

) {
    navigation(
        startDestination = Screen.CategoryScreen.route,
        route = NestedRoute.Main.name
    ) {
        composable(route = Screen.CategoryScreen.route) {
            CategoryScreen(loginViewModel = loginViewModel,
                {
                    navController.navigate(Screen.StartScreen.route) {
                        launchSingleTop = true
                        popUpTo(0) {
                            inclusive = true

                        }
                    }
                }, context = context
            )
        }
    }
}

