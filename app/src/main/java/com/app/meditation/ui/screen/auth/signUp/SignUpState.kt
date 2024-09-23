package com.app.meditation.ui.screen.auth.signUp

import android.net.Uri

data class SignUpState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val imageUri: Uri=Uri.EMPTY
)
