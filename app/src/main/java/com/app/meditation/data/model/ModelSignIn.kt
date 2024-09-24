package com.app.meditation.data.model

import android.net.Uri

data class ModelSignIn(
    val firstName: String="",
    val lastName: String="",
    val email: String="",
    val password: String="",
    val uri: Uri= Uri.EMPTY,
    var uId:String?=null
)