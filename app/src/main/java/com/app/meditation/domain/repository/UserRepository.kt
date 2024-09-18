package com.app.meditation.domain.repository

interface UserRepository {
    suspend fun getUserName():String
}