package com.app.meditation.ui.screen.auth.login

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.MyApp
import com.app.meditation.common.Resource
import com.app.meditation.domain.usecase.GetAuthUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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
    var state: StateFlow<LoginState> = _state.asStateFlow()

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

                status.collectLatest { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            Toast.makeText(
                                myApp.applicationContext,
                                resource.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            _state.update {
                                it.copy(isLoading = false)
                            }
                        }

                        is Resource.Loading -> {

                            _state.update {
                                it.copy(isLoading = true)
                            }

                        }

                        is Resource.Success -> {
//                            Toast.makeText(
//                                myApp.applicationContext,
//                                "Login successFully",
//                                Toast.LENGTH_SHORT
//                            ).show()
                            resource.data?.let {
                                saveUserData(resource.data,onLogin)

                            }
                            _state.update {
                                it.copy(isLoading = false)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun saveUserData(data: AuthResult,onLogin:()->Unit) {
        data.user?.let {
            viewModelScope.launch {
                val status = getAuthUseCase.saveUserData(it.uid)
                status.collectLatest { response ->
                    when (response) {
                        is Resource.Error -> {
                        }

                        is Resource.Loading -> {
                            _state.update {
                                it.copy(isLoading = true)
                            }
                        }

                        is Resource.Success -> {
                            onLogin()
                        }
                    }
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
    fun updatePasswordVisibility() {
        _state.update {
            it.copy(isPasswordHide = _state.value.isPasswordHide.not())
        }
    }

}