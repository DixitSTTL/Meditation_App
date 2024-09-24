package com.app.meditation.ui.screen.auth.signUp

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.app.meditation.R
import com.app.meditation.ui.theme.EdtColor
import com.app.meditation.ui.theme.GreenLight
import com.app.meditation.ui.theme.White50
import com.app.meditation.ui.theme.White90

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navigateLogin: () -> Unit,
    navigateAlreadyLogin: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let { viewModel.updateImage(it) }
        }

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
                text = "Sign Up",
                color = Color.White,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_bold)),
                    fontSize = 22.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Sign up now for free and start meditating, and explore Medic.",
                color = White50,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                    fontSize = 18.sp
                ),
            )

            Spacer(modifier = Modifier.height(25.dp))

            ProfileImage(state, {
                launcher.launch(
                    PickVisualMediaRequest(
                        //Here we request only photos. Change this to .ImageAndVideo if
                        //you want videos too.
                        //Or use .VideoOnly if you only want videos.
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            })


            Spacer(modifier = Modifier.height(25.dp))


            TextField(
                textStyle = TextStyle(
                    fontSize = 22.sp,
                    color = EdtColor,
                    fontFamily = FontFamily(Font(R.font.alegreya_regular))
                ),
                value = state.firstName,
                onValueChange = {
                    viewModel.updateFirstName(it)
                },
                label = {
                    Text(
                        text = "First Name",
                        color = EdtColor,
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words
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
                value = state.lastName,
                onValueChange = {
                    viewModel.updateLastName(it)
                },
                label = {
                    Text(
                        text = "Last Name",
                        color = EdtColor,
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words
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
                value = state.email, onValueChange = {
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
                onValueChange = {
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
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.updatePasswordVisibility()
                    }) {
                        Icon(painter = painterResource(if(state.isPasswordHide) R.drawable.ic_eye else R.drawable.ic_eye_closed) ,"")
                    }
                },
                singleLine = true,
                visualTransformation = if (state.isPasswordHide) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.clickToSignUp() {
                        navigateLogin()
                    }

                }),
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
                    viewModel.clickToSignUp() {
                        navigateLogin()
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
                    text = "SIGNUP",
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
                    text = "Already have an account?",
                    color = White50,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_regular)),
                        fontSize = 14.sp
                    )
                )

                Text(
                    text = "Sign In",
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.alegreya_semi_bold)),
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable {
                            navigateAlreadyLogin()
                        }
                )
            }


        }


    }


}

@Composable
fun ProfileImage(state: SignUpState, onClick: () -> Unit) {
    if (state.imageUri == Uri.EMPTY) {
        Image(
            painter = painterResource(R.drawable.ic_tab_profile),
            contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .border(2.dp, Color.White, CircleShape)
                .clickable {
                    onClick()
                }
                .padding(20.dp),
        )
    } else {
        val painter = rememberAsyncImagePainter(
            ImageRequest
                .Builder(LocalContext.current)
                .data(data = state.imageUri)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .clip(shape = CircleShape)
                .clickable {
                    onClick()
                }
                .padding(),
            contentScale = ContentScale.Crop,
        )
    }
}