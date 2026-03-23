package com.app.meditation.ui.screen.ai

import com.app.meditation.data.model.ModelAiChat

data class AiState(
    var str: String = "",
    var messageList: List<ModelAiChat> = emptyList()
)
