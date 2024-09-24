package com.app.meditation.domain.repository

import com.app.meditation.common.Resource
import com.app.meditation.data.model.ModelSignIn
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun signUpUser(modelSignIn: ModelSignIn): Flow<Resource<AuthResult>>
    suspend fun saveUserData(uId: String): Flow<Resource<Boolean>>
}