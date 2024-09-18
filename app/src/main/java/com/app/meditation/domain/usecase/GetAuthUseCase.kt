package com.app.meditation.domain.usecase

import com.app.meditation.domain.repository.AuthRepository
import com.app.meditation.ui.screen.auth.login.LoginState
import com.app.meditation.ui.screen.auth.signUp.SignUpState
import javax.inject.Inject

class GetAuthUseCase @Inject constructor(val authRepository: AuthRepository) {

    suspend fun loginToUser(loginState: LoginState): Boolean {
        return authRepository.loginUser(loginState.email, loginState.password)
    }

    suspend fun signUpUser(signUpState: SignUpState): Boolean {
        return authRepository.signUpUser(signUpState.name, signUpState.email, signUpState.password)
    }
}