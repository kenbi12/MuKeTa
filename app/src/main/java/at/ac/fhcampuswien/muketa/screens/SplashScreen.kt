package at.ac.fhcampuswien.muketa.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import at.ac.fhcampuswien.muketa.R
import at.ac.fhcampuswien.muketa.navigation.Screen
import at.ac.fhcampuswien.muketa.ui.theme.color1
import at.ac.fhcampuswien.muketa.ui.theme.color2
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.StartScreen.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    val customFontFamily = FontFamily(Font(R.font.spal))
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else color1)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column( modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 84.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "MuKeTa",
                modifier = Modifier
                    //.size(120.dp)
                    .fillMaxWidth()
                    .alpha(alpha = alpha),
                color = color2,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                //textAlign = Alignment.Center,
                fontFamily = customFontFamily
            )
        }

    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview() {
    Splash(alpha = 1f)
}