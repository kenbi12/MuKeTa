package at.ac.fhcampuswien.muketa

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import at.ac.fhcampuswien.muketa.helper.HelperClass
import at.ac.fhcampuswien.muketa.models.FlagQuizModel
import at.ac.fhcampuswien.muketa.models.QuizStateModel
import at.ac.fhcampuswien.muketa.ui.theme.MuKeTaTheme
import at.ac.fhcampuswien.muketa.ui.theme.Purple500
import at.ac.fhcampuswien.muketa.viewmodels.FlagQuizViewModel
import coil.compose.rememberAsyncImagePainter
import kotlin.random.Random

class FlagQuizActivity : ComponentActivity() {

    private val flagQuizViewModel: FlagQuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuKeTaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val dataList = remember { mutableStateListOf<FlagQuizModel>() }

                    flagQuizViewModel.liveDataList.observe(this) {
                        if (it != null) {
                            if (it.size > 10) {
                                val rem = it.size - 10
                                val randomNo: Int = Random.nextInt(0, rem)
                                val target = randomNo + 9
                                dataList.clear()
                                for (i in randomNo..target) {
                                    dataList.add(it[i])
                                }
                            } else {
                                dataList.clear()
                                dataList.addAll(it)
                            }
                        }
                    }

                    QuizLayout(list = dataList)

                }
            }
        }
    }

    @Composable
    fun QuizLayout(list: List<FlagQuizModel>) {

        // This state list will store the state of the solved items so that after
        // recomposition it maintains the state of the items and widgets
        val stateList = remember {
            mutableStateListOf<QuizStateModel>()
        }

        val correctScore = remember {
            mutableStateOf(0)
        }

        val inCorrectScore = remember {
            mutableStateOf(0)
        }

        val indexCount = remember {
            mutableStateOf(0)
        }


        val button1Colors = remember { mutableStateListOf<Color>() }
        val button2Colors = remember { mutableStateListOf<Color>() }
        val button3Colors = remember { mutableStateListOf<Color>() }
        val button4Colors = remember { mutableStateListOf<Color>() }

        if (button1Colors.size < list.size || button2Colors.size < list.size
            || button3Colors.size < list.size || button4Colors.size < list.size
        ) {
            button1Colors.clear()
            button2Colors.clear()
            button3Colors.clear()
            button4Colors.clear()
            repeat(list.size) {
                button1Colors.add(Color.Gray)
                button2Colors.add(Color.Gray)
                button3Colors.add(Color.Gray)
                button4Colors.add(Color.Gray)
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                val (backBtn, title) = createRefs()

                Icon(imageVector = Icons.Default.ArrowBack,
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
                    text = HelperClass.mySelection, modifier = Modifier.constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }, style = TextStyle(
                        fontSize = 16.sp, fontWeight = FontWeight.ExtraBold
                    ), color = Color.Black
                )
            }

            if (indexCount.value < list.size) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.90f)
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .background(
                                    shape = RoundedCornerShape(10.dp), color = Color.White
                                )
                                .padding(horizontal = 10.dp, vertical = 7.dp), elevation = 10.dp
                        ) {

                            Column(
                                modifier = Modifier.fillMaxSize()

                            ) {

                                Spacer(modifier = Modifier.height(10.dp))

                                // Here it is setting the question to the item
                                Text(
                                    text = "${indexCount.value + 1}) Question: " + list[indexCount.value].question,
                                    style = TextStyle(
                                        fontSize = 18.sp, fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp)
                                )

                                // This is setting image to the imageview here
                                Row {
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Image(
                                        painter = rememberAsyncImagePainter(list[indexCount.value].url),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(250.dp)
                                            .height(200.dp)
                                            .background(
                                                shape = RoundedCornerShape(10.dp),
                                                color = Color.Transparent
                                            )
                                            .padding(horizontal = 10.dp),
                                    )
                                }


                                Spacer(modifier = Modifier.height(10.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp)

                                ) {
                                    Button(
                                        onClick = {
                                            // Here the option 1 is compared if its answer is correct
                                            // it will store true else it will store false...
                                            if (indexCount.value == stateList.size) {
                                                if (list[indexCount.value].answer1 == list[indexCount.value].answer5) {
                                                    stateList.add(
                                                        QuizStateModel(
                                                            true, 1
                                                        )
                                                    )
                                                    button1Colors[indexCount.value] = Color.Green
                                                    correctScore.value++
                                                } else {
                                                    stateList.add(
                                                        QuizStateModel(
                                                            false, 1
                                                        )
                                                    )
                                                    inCorrectScore.value++
                                                    button1Colors[indexCount.value] = Color.Red
                                                }

                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth(0.5f)
                                            .height(50.dp)
                                            .padding(horizontal = 10.dp, vertical = 5.dp),
                                        contentPadding = PaddingValues()
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(
                                                    color = button1Colors[indexCount.value]
                                                ),
                                            contentAlignment = Alignment.Center,
                                        ) {

                                            Text(
                                                text = "a) " + list[indexCount.value].answer1,
                                                style = TextStyle(fontSize = 16.sp),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 10.dp)
                                            )
                                        }
                                    }


                                    Button(
                                        onClick = {

                                            // Here the option 2 is compared if its answer is correct
                                            // it will store true else it will store false...

                                            if (indexCount.value == stateList.size) {
                                                if (list[indexCount.value].answer2 == list[indexCount.value].answer5) {
                                                    stateList.add(
                                                        QuizStateModel(
                                                            true, 2
                                                        )
                                                    )
                                                    button2Colors[indexCount.value] = Color.Green
                                                    correctScore.value++
                                                } else {
                                                    stateList.add(
                                                        QuizStateModel(
                                                            false, 2
                                                        )
                                                    )
                                                    inCorrectScore.value++
                                                    button2Colors[indexCount.value] = Color.Red
                                                }

                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth(1.0f)
                                            .height(50.dp)
                                            .padding(horizontal = 10.dp, vertical = 5.dp),
                                        contentPadding = PaddingValues()
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(
                                                    color = button2Colors[indexCount.value]
                                                ),
                                            contentAlignment = Alignment.Center,
                                        ) {

                                            Text(
                                                text = "b) " + list[indexCount.value].answer2,
                                                style = TextStyle(fontSize = 16.sp),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 10.dp)
                                            )
                                        }
                                    }
                                }


                                Spacer(modifier = Modifier.height(5.dp))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp)
                                ) {
                                    Button(
                                        onClick = {

                                            // Here the option 3 is compared if its answer is correct
                                            // it will store true else it will store false...
                                            if (indexCount.value == stateList.size) {
                                                if (list[indexCount.value].answer3 == list[indexCount.value].answer5) {
                                                    stateList.add(
                                                        QuizStateModel(
                                                            true, 3
                                                        )
                                                    )
                                                    button3Colors[indexCount.value] = Color.Green
                                                    correctScore.value++
                                                } else {
                                                    stateList.add(
                                                        QuizStateModel(
                                                            false, 3
                                                        )
                                                    )
                                                    inCorrectScore.value++
                                                    button3Colors[indexCount.value] = Color.Red
                                                }

                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth(0.5f)
                                            .height(50.dp)
                                            .padding(horizontal = 10.dp, vertical = 5.dp),
                                        contentPadding = PaddingValues()
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(
                                                    color = button3Colors[indexCount.value]
                                                ),
                                            contentAlignment = Alignment.Center,
                                        ) {

                                            Text(
                                                text = "c) " + list[indexCount.value].answer3,
                                                style = TextStyle(fontSize = 16.sp),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 10.dp)
                                            )
                                        }
                                    }


                                    Button(
                                        onClick = {

                                            // Here the option 4 is compared if its answer is correct
                                            // it will store true else it will store false...
                                            if (indexCount.value == stateList.size) {
                                                if (list[indexCount.value].answer4 == list[indexCount.value].answer5) {
                                                    stateList.add(
                                                        QuizStateModel(
                                                            true, 4
                                                        )
                                                    )
                                                    button4Colors[indexCount.value] = Color.Green
                                                    correctScore.value++
                                                } else {
                                                    stateList.add(
                                                        QuizStateModel(
                                                            false, 4
                                                        )
                                                    )
                                                    inCorrectScore.value++
                                                    button4Colors[indexCount.value] = Color.Red
                                                }

                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth(1.0f)
                                            .height(50.dp)
                                            .padding(horizontal = 10.dp, vertical = 5.dp),
                                        contentPadding = PaddingValues()
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(
                                                    color = button4Colors[indexCount.value]
                                                ),
                                            contentAlignment = Alignment.Center,
                                        ) {

                                            Text(
                                                text = "d) " + list[indexCount.value].answer4,
                                                style = TextStyle(fontSize = 16.sp),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 10.dp)
                                            )
                                        }
                                    }
                                }

                            }
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Button(
                            onClick = {
                                // Button to increase count and move next
                                if (indexCount.value < stateList.size) {
                                    indexCount.value++
                                } else {
                                    Toast.makeText(
                                        this@FlagQuizActivity,
                                        "Solve this question first",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(50.dp)
                                .padding(horizontal = 10.dp),
                            contentPadding = PaddingValues()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        color = Purple500
                                    ),
                                contentAlignment = Alignment.Center,
                            ) {

                                Text(
                                    text = "Next",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            }
                        }


                    }

                }

            } else if (indexCount.value > 0) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    // progress slider
                    Slider(
                        value = (correctScore.value * 10).toFloat(),
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

                    // Correct Count show
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
                            text = correctScore.value.toString(),
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black
                        )

                    }

                    // Incorrect count show
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                    ) {

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
                            text = inCorrectScore.value.toString(),
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black
                        )


                    }


                    Spacer(modifier = Modifier.height(5.dp))

                    // Finish Button
                    Button(
                        onClick = {
                            finish()
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(50.dp)
                            .padding(horizontal = 10.dp),
                        contentPadding = PaddingValues()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Purple500
                                ),
                            contentAlignment = Alignment.Center,
                        ) {

                            Text(
                                text = "Finish",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                            )
                        }
                    }

                }
            }


            // Bottom box for progressbar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        color = Color.Black
                    )
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Slider(value = ((stateList.size) * 10).toFloat(),
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
                            activeTrackColor = Color.Green
                        ))


                }
            }

        }

    }
}
