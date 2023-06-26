package at.ac.fhcampuswien.muketa

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import at.ac.fhcampuswien.muketa.helper.HelperClass
import at.ac.fhcampuswien.muketa.ui.theme.MuKeTaTheme

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuKeTaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val c = intent.getIntExtra("correct", 0)
                    val ic = intent.getIntExtra("inCorrect", 0)

                    ResultLayout(correct = c, inCorrect = ic)

                }
            }
        }
    }

    @Composable
    fun ResultLayout(correct: Int, inCorrect: Int) {
        Column(modifier = Modifier.fillMaxSize()) {

            // Toolbar layout
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                val (backBtn, title, finishBtn) = createRefs()

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(backBtn) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start, margin = 15.dp)
                            bottom.linkTo(parent.bottom)
                        }
                        .size(24.dp)
                        .clickable {
                            finish()
                        },
                    tint = Color.Black
                )

                Text(
                    text = HelperClass.mySelection +" Result",
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = Color.Black
                )


                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(finishBtn) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end, margin = 15.dp)
                            bottom.linkTo(parent.bottom)
                        }
                        .size(24.dp)
                        .clickable {
                            finish()
                        },
                    tint = Color.Black
                )
            }

            // Center layout

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Slider(
                    value = (correct * 10).toFloat(),
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .graphicsLayer {
                            scaleX = 1.0f
                        }
                        .padding(horizontal = 20.dp),
                    valueRange = 0f..100f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Transparent,
                        inactiveTrackColor = Color.LightGray,
                        activeTrackColor = Color.Green,
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                ) {

                    // Text to indicate the score correct
                    Text(
                        text = "Correct: ",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
                    )

                    // correct Score text
                    Text(
                        text = correct.toString(),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
                    )

                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)) {

                    // Text to indicate the score Incorrect
                    Text(
                        text = "InCorrect: ",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
                    )

                    // Incorrect Score text
                    Text(
                        text = inCorrect.toString(),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
                    )


                }

            }

        }
    }
}

