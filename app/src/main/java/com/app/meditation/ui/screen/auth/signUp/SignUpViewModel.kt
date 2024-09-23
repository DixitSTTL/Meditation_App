package com.app.meditation.ui.screen.auth.signUp

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.MyApp
import com.app.meditation.common.Resource
import com.app.meditation.data.model.ModelSignIn
import com.app.meditation.domain.usecase.GetAuthUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class SignUpViewModel @Inject constructor(private val myApp: MyApp, private val authUseCase: GetAuthUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state

    private fun validation(): Boolean {
        if (_state.value.name.isEmpty()) {
            Toast.makeText(myApp.applicationContext, "Please enter name", Toast.LENGTH_SHORT).show()
            return false
        } else if (_state.value.email.isEmpty()) {
            Toast.makeText(myApp.applicationContext, "Please enter password", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (_state.value.password.isEmpty()) {
            Toast.makeText(myApp.applicationContext, "Please enter password", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        return true

    }

    fun updateName(str: String) {
        _state.update {
            it.copy(name = str)
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

    fun updateImage(str: Uri) {
        str.let {
            _state.update {
                it.copy(imageUri = str)
            }

        }
    }

    fun clickToSignUp(navigateLogin: () -> Unit) {
        if (validation()) {
            viewModelScope.launch {
                val response = authUseCase.signUpUser(ModelSignIn(
                    name = _state.value.name,
                    email = _state.value.email,
                    password = _state.value.password,
                    uri = _state.value.imageUri
                ))
                response.collectLatest {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Error -> {

                            Log.d("error:::->",""+it.message)
                        }
                        is Resource.Success -> {
                            navigateLogin()
                        }
                    }
                }
            }
        }

    }

}