package at.ac.fhcampuswien.muketa.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.muketa.ui.theme.color2
import at.ac.fhcampuswien.muketa.viewmodels.LoginViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryScreen(loginViewModel: LoginViewModel,
                   navToLoginPage: () -> Unit,

                   ){


    Scaffold(

        topBar = {

            TopAppBar(backgroundColor = color2,
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
                    Text(text = "Category",  color = Color.White, modifier = Modifier.padding(75.dp , 0.dp) )
                }
            )


        }
    ){
Column(
    verticalArrangement = Arrangement.spacedBy(4.dp)) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable { /*TODO*/ },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Flags",
                style = MaterialTheme.typography.h6
            )
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable { /*TODO*/ },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "City",
                style = MaterialTheme.typography.h6
            )
        }
    }
}



    }
}
