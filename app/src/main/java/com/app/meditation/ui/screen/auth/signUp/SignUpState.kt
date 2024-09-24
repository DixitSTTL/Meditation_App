package com.app.meditation.ui.screen.auth.signUp

import android.net.Uri

data class SignUpState(
    val isLoading: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordHide: Boolean= true,
    val imageUri: Uri=Uri.EMPTY
)
