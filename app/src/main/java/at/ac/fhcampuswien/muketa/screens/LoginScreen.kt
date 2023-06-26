package at.ac.fhcampuswien.muketa.screens



import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.muketa.ui.theme.color1
import at.ac.fhcampuswien.muketa.ui.theme.color2
import at.ac.fhcampuswien.muketa.viewmodels.LoginViewModel


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel? = null,
    onNavToHomePage:() -> Unit,
    onNavToSignUpPage:() -> Unit,
) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),//.background(SubTextColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Black,
            color =  color1//MaterialTheme.colors.primary
        )

        if (isError){
            Text(
                text = loginUiState?.loginError ?: "unknown error",
                color = Color.Red,
            )
        }
        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = color1,
            unfocusedBorderColor = color1
        )
        OutlinedTextField(
            colors = textFieldColors,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.userName ?: "",
            onValueChange = {loginViewModel?.onUserNameChange(it)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = color1
                )
            },
            label = {
                Text(text = "Email",color = color1)
            },
            isError = isError
        )
        OutlinedTextField(
            colors = textFieldColors,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = loginUiState?.password ?: "",
            onValueChange = { loginViewModel?.onPasswordNameChange(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = color1

                )
            },
            label = {
                Text(text = "Password",color = color1)
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = isError

        )

        Button(onClick = { loginViewModel?.loginUser(context) },colors = ButtonDefaults.buttonColors(backgroundColor = color1),) {

            Text(text = "Sign In")
        }
        Spacer(modifier = Modifier.size(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't have an Account?")
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(onClick = { onNavToSignUpPage.invoke()},colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                Text(text = "Register",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Black,
                    color =  color2)




            }


        }

        if (loginUiState?.isLoading == true){
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = loginViewModel?.hasUser){
            if (loginViewModel?.hasUser == true){
                onNavToHomePage.invoke()
            }
        }

    }


}
