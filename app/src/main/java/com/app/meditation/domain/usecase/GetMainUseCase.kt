package com.app.meditation.domain.usecase

import com.app.meditation.domain.repository.UserRepository
import javax.inject.Inject

class GetMainUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun logoutUser() {
        return userRepository.userLogout()
    }


}