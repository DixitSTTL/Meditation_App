package com.app.meditation.domain.repository

import com.app.meditation.data.model.ModelAiChat
import kotlinx.coroutines.flow.Flow

interface AiRepository {
    suspend fun sendQuery(query: String)
    fun getMessageList(): Flow<List<ModelAiChat>>
}