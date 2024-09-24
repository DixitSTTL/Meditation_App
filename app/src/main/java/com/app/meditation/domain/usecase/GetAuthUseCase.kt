package com.app.meditation.domain.usecase

import com.app.meditation.common.Resource
import com.app.meditation.data.model.ModelSignIn
import com.app.meditation.domain.repository.AuthRepository
import com.app.meditation.ui.screen.auth.login.LoginState
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun loginToUser(loginState: LoginState): Flow<Resource<AuthResult>> {
        return authRepository.loginUser(loginState.email, loginState.password)
    }

    suspend fun signUpUser(modelSignIn: ModelSignIn): Flow<Resource<AuthResult>>  {
        return authRepository.signUpUser(modelSignIn)
    }
    suspend fun saveUserData(uId: String): Flow<Resource<Boolean>>  {
        return authRepository.saveUserData(uId)
    }
}