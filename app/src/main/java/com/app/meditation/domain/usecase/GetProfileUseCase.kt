package com.app.meditation.domain.usecase

import com.app.meditation.data.model.ModelProfile
import com.app.meditation.domain.repository.UserRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getUserData(): ModelProfile {
        return userRepository.getProfile()
    }

}