package at.ac.fhcampuswien.muketa.screens


import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import at.ac.fhcampuswien.muketa.viewmodels.LoginViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryScreen(loginViewModel: LoginViewModel,
                   navToLoginPage: () -> Unit
){


    Scaffold(

        topBar = {

            TopAppBar(backgroundColor = Color.Gray,
                navigationIcon = {},
                actions = {

                    IconButton(onClick = {
                        loginViewModel?.signOut()
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
                    Text(text = "Category",  color = Color.White)
                }
            )


        }
    ){
        Text(text = "choose a Category")  }



}