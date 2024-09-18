package com.app.meditation.domain.repository

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): Boolean
    suspend fun signUpUser(name: String, email: String, password: String): Boolean
}