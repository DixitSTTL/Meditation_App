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
        if (_state.value.firstName.isEmpty()) {
            Toast.makeText(myApp.applicationContext, "Please enter first name", Toast.LENGTH_SHORT).show()
            return false
        } else if (_state.value.lastName.isEmpty()) {
            Toast.makeText(myApp.applicationContext, "Please enter last name", Toast.LENGTH_SHORT).show()
            return false
        } else if (_state.value.email.isEmpty()) {
            Toast.makeText(myApp.applicationContext, "Please enter email", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (_state.value.password.isEmpty()) {
            Toast.makeText(myApp.applicationContext, "Please enter password", Toast.LENGTH_SHORT)
                .show()
            return false
        }else if (_state.value.imageUri== Uri.EMPTY) {
            Toast.makeText(myApp.applicationContext, "Please select profile picture", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        return true

    }

    fun updateFirstName(str: String) {
        _state.update {
            it.copy(firstName = str)
        }
    }

    fun updateLastName(str: String) {
        _state.update {
            it.copy(lastName = str)
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

    fun updatePasswordVisibility() {
        _state.update {
            it.copy(isPasswordHide = _state.value.isPasswordHide.not())
        }
    }

    fun clickToSignUp(navigateLogin: () -> Unit) {
        if (validation()) {
            viewModelScope.launch {
                val response = authUseCase.signUpUser(ModelSignIn(
                    firstName = _state.value.firstName,
                    lastName = _state.value.lastName,
                    email = _state.value.email,
                    password = _state.value.password,
                    uri = _state.value.imageUri
                ))
                response.collectLatest { res ->
                    when (res) {
                        is Resource.Loading -> {
                            _state.update {
                                it.copy(isLoading = true)
                            }
                        }
                        is Resource.Error -> {
                            _state.update {
                                it.copy(isLoading = false)
                            }
                            Log.d("error:::->",""+res.message)
                        }
                        is Resource.Success -> {
                            _state.update {
                                it.copy(isLoading = false)
                            }
                            navigateLogin()
                        }
                    }
                }
            }
        }
    }

}