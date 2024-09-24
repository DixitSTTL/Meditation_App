package com.app.meditation.domain.usecase

import com.app.meditation.domain.repository.UserRepository
import javax.inject.Inject

class GetDashboardUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getFirstName(): String {
        return userRepository.getFirstName()
    }
}