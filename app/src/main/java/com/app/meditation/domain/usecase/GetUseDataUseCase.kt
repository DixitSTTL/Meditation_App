package com.app.meditation.domain.usecase

import com.app.meditation.domain.repository.TuneRepository
import com.app.meditation.domain.repository.UserRepository
import com.app.meditation.ui.screen.tuneList.DataTunes
import javax.inject.Inject

class GetUseDataUseCase @Inject constructor(val userRepository: UserRepository) {

    suspend fun getUserName(): String {
        return userRepository.getUserName()
    }
}