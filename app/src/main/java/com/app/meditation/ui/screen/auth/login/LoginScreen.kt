package com.app.meditation.ui.screen.auth.login

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.meditation.R
import com.app.meditation.ui.theme.EdtColor
import com.app.meditation.ui.theme.GreenLight
import com.app.meditation.ui.theme.White50
import com.app.meditation.ui.theme.White90

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigateSignUp: () -> Unit,
    navigateMainActivity: () -> Unit,
    showToast: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {


    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.Crop
        )

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = GreenLight
            )
        }

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {

            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_logo_main),
                contentDescription = "",
                modifier = Modifier.size(45.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Sign In",
                color = Color.White,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_bold)),
                    fontSize = 22.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Sign in now to acces your excercises and saved music.",
                color = White50,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                    fontSize = 18.sp
                ),
            )

            Spacer(modifier = Modifier.height(25.dp))

            TextField(
                textStyle = TextStyle(
                    fontSize = 22.sp,
                    color = EdtColor,
                    fontFamily = FontFamily(Font(R.font.alegreya_regular))
                ),
                value = state.email, onValueChange = { it ->
                    viewModel.updateEmail(it)
                },
                label = {
                    Text(
                        text = "Email",
                        color = EdtColor,
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Email
                ),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = EdtColor,
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = EdtColor,
                    unfocusedIndicatorColor = EdtColor,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                textStyle = TextStyle(
                    fontSize = 22.sp,
                    color = EdtColor,
                    fontFamily = FontFamily(Font(R.font.alegreya_regular))
                ),
                value = state.password,
                onValueChange = { it ->
                    viewModel.updatePassword(it)
                },
                label = {
                    Text(
                        text = "Password",
                        color = EdtColor,
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                    )
                },
                singleLine = true,
                visualTransformation = if (state.isPasswordHide) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.loginToApp() {
                        navigateMainActivity()
                    }
                }),
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.updatePasswordVisibility()
                    }) {
                        Icon(painter = painterResource(if(state.isPasswordHide) R.drawable.ic_eye else R.drawable.ic_eye_closed) ,"")
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = EdtColor,
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = EdtColor,
                    unfocusedIndicatorColor = EdtColor,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    viewModel.loginToApp() {
                        navigateMainActivity()
                    }


                },
                enabled = !state.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 30.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GreenLight
                )
            ) {
                Text(
                    text = "LOGIN",
                    color = White90,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                        fontSize = 20.sp
                    )
                )
            }


            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don’t have an account?",
                    color = White50,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                        fontSize = 14.sp
                    )
                )

                Text(
                    text = "Sign Up",
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable {
                            navigateSignUp()
                        }
                )
            }


        }


    }


}
