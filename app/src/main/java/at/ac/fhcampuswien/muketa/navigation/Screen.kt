package at.ac.fhcampuswien.muketa.navigation


sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object StartScreen : Screen("Start_screen")
    object LoginScreen : Screen("Login_screen")
    object RegisterScreen : Screen("Register_screen")
    object CategoryScreen : Screen("Category_screen")

}
enum class NestedRoute{
    Main,
    Login
}
