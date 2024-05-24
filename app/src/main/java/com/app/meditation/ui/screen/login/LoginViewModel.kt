package com.app.meditation.ui.screen.login

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.app.MyApp
import com.app.meditation.R
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var shared: UtilsSharedPreferences, var myApp: MyApp) :
    ViewModel() {

    var mEmailText = MutableStateFlow<String>("")
    var mPassText = MutableStateFlow<String>("")

    fun validation(
        showToast: (String) -> Unit
    ): Boolean {
        if (mEmailText.value.isEmpty()) {

            showToast("Please enter email address")
            return false
        } else if (mPassText.value.isEmpty()) {

            showToast("Please enter password")
            return false
        }

        return true

    }

    fun loginToApp():Boolean {

        val sEmail = shared.getString(myApp.resources.getResourceName(R.string.user_email)).toString()
        val sPass = shared.getString(myApp.resources.getResourceName(R.string.user_password)).toString()

        if (sEmail == mEmailText.value && sPass == mPassText.value) {
            shared.setBoolean(
                myApp.resources.getResourceName(R.string.user_login),
                true
            )
            Toast.makeText(myApp.applicationContext,"Login successFully",Toast.LENGTH_SHORT).show()
            return true

        } else {
            Toast.makeText(myApp.applicationContext,"Login failed",Toast.LENGTH_SHORT).show()
            return false

        }


    }

}