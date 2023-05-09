package at.ac.fhcampuswien.muketa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import at.ac.fhcampuswien.muketa.navigation.ScreensNavigation
import at.ac.fhcampuswien.muketa.screens.StartScreen
import at.ac.fhcampuswien.muketa.ui.theme.MuKeTaTheme
import at.ac.fhcampuswien.proman.screens.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuKeTaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreensNavigation()
                }
            }
        }
    }
}

