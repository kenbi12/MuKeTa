package at.ac.fhcampuswien.muketa.screens


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.muketa.QuizActivity
import at.ac.fhcampuswien.muketa.FlagQuizActivity
import at.ac.fhcampuswien.muketa.helper.HelperClass
import at.ac.fhcampuswien.muketa.ui.theme.*
import at.ac.fhcampuswien.muketa.viewmodels.LoginViewModel
import java.util.Random

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryScreen(
    loginViewModel: LoginViewModel,
    navToLoginPage: () -> Unit,
    context: Context
) {
    val colors = listOf(cl1, cl2, cl3, cl4, cl5, cl6)
    var currentColorIndex by remember { mutableStateOf(0) }


    Scaffold(

        topBar = {

            TopAppBar(backgroundColor = colors[currentColorIndex],
                navigationIcon = {},
                actions = {

                    IconButton(onClick = {
                        loginViewModel.signOut()
                        navToLoginPage.invoke()
                    }) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = null,


                            )
                    }
                },
                title = {
                    Text(
                        text = "Category",
                        color = Color.White,
                        modifier = Modifier.padding(75.dp, 0.dp)
                    )
                }
            )


        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    currentColorIndex = (currentColorIndex + 1) % colors.size
                }
                .background(colors[currentColorIndex]),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .clickable {
                        HelperClass.mySelection = "Flaggen"
                        val i = Intent(context, FlagQuizActivity::class.java)
                        context.startActivity(i)
                    },
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                elevation = 6.dp
            ) {

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Flaggen",
                        style = MaterialTheme.typography.h6
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .clickable {
                        HelperClass.mySelection = "Hauptstädte"
                        val i = Intent(context, QuizActivity::class.java)
                        context.startActivity(i)
                    },
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                elevation = 6.dp
            ) {

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Hauptstädte",
                        style = MaterialTheme.typography.h6
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .clickable {
                        HelperClass.mySelection = "Allgemein Wissen"
                        val i = Intent(context, QuizActivity::class.java)
                        context.startActivity(i)
                    },
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                elevation = 6.dp
            ) {

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Allgemein Wissen",
                        style = MaterialTheme.typography.h6
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .clickable {
                        HelperClass.mySelection = "Geschichte & Ethik"
                        val i = Intent(context, QuizActivity::class.java)
                        context.startActivity(i)
                    },
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                elevation = 6.dp
            ) {

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Geschichte & Ethik",
                        style = MaterialTheme.typography.h6
                    )
                }
            }

        }

    }
}

/*


@Composable
fun generateRandomColor(): Color {
    val randomColor = Color(
        alpha = 255,
        red = (0..255).random(),
        green = (0..255).random(),
        blue = (0..255).random()
    )
    return randomColor
}
 */