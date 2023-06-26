package at.ac.fhcampuswien.muketa.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import at.ac.fhcampuswien.muketa.R
import at.ac.fhcampuswien.muketa.navigation.Screen
import at.ac.fhcampuswien.muketa.ui.theme.LoginBackground
import at.ac.fhcampuswien.muketa.ui.theme.color1
import at.ac.fhcampuswien.muketa.ui.theme.color2


@Composable
fun StartScreen(navController: NavController){

    val customFontFamily = FontFamily(Font(R.font.splash))

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LoginBackground), // Set the background color here

        ) {
            Image(painter = painterResource(id = R.drawable.start),
                contentDescription = "login_Image",
                modifier = Modifier.fillMaxSize()

            )


            Column(modifier = Modifier.fillMaxSize(),


            ) {
                Divider(modifier = Modifier.padding(200.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "MuKeTa",
                        modifier = Modifier.size(120.dp),
                        color = Color.White,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = customFontFamily,
                    )
                    Divider()
                    //Text(text = "Start the quizz!", color = Color.LightGray)

                }


                Spacer(modifier = Modifier.padding(35.dp))



                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom,


                    ) {


                    Button(
                        onClick = {navController.navigate(Screen.LoginScreen.route)},
                        modifier = Modifier.height(45.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = color1),
                        shape = RoundedCornerShape(5.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 10.dp,
                            pressedElevation = 15.dp,
                            disabledElevation = 0.dp
                        ),


                        ) {
                        Text(text = "Sign in ")
                    }
                    Button(
                        onClick = { navController.navigate(Screen.RegisterScreen.route)},
                        modifier = Modifier.height(45.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = color2),
                        shape = RoundedCornerShape(5.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 10.dp,
                            pressedElevation = 15.dp,
                            disabledElevation = 0.dp
                        )

                    ) {
                        Text(text = "Register", color = Color.White)
                    }
                }
            }
        }
    }
}