package com.app.meditation.ui.screen.auth.login

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.MyApp
import com.app.meditation.domain.usecase.GetAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    var getAuthUseCase: GetAuthUseCase,
    var myApp: MyApp
) :
    ViewModel() {

    private var _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    var state: StateFlow<LoginState> = _state

    private fun validation(
    ): Boolean {
        if (state.value.email.isEmpty()) {
            Toast.makeText(
                myApp.applicationContext,
                "Please enter email address",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (state.value.password.isEmpty()) {
            Toast.makeText(myApp.applicationContext, "Please enter password", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        return true

    }

    fun loginToApp(onLogin: () -> Unit) {

        if (validation()) {
            viewModelScope.launch {
                val status = getAuthUseCase.loginToUser(_state.value)
                if (status) {
                    Toast.makeText(
                        myApp.applicationContext,
                        "Login successFully",
                        Toast.LENGTH_SHORT
                    ).show()
                    onLogin()
                } else {
                    Toast.makeText(myApp.applicationContext, "Login failed", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }


    }

    fun updateEmail(str: String) {
        _state.update {
            it.copy(email = str)
        }
    }

    fun updatePassword(str: String) {
        _state.update {
            it.copy(password = str)
        }
    }

}