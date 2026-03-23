package com.app.meditation.domain.usecase

import com.app.meditation.data.model.ModelAiChat
import com.app.meditation.domain.repository.AiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAiUseCase @Inject constructor(private val aiRepository: AiRepository) {

    suspend fun askQuery(query: String) {
        return aiRepository.sendQuery(query)
    }

    fun getMessageList(): Flow<List<ModelAiChat>> {
        return aiRepository.getMessageList()
    }


}