package com.app.meditation.domain.usecase

import com.app.meditation.domain.repository.UserRepository
import javax.inject.Inject

class GetUseDataUseCase @Inject constructor(val userRepository: UserRepository) {

    suspend fun getUserName(): String {
        return userRepository.getUserName()
    }
}