package com.app.meditation.domain.repository

import com.app.meditation.data.model.ModelProfile

interface UserRepository {
    suspend fun getFirstName(): String
    suspend fun getUserName(): String
    suspend fun getProfile(): ModelProfile
    suspend fun userLogout()
}