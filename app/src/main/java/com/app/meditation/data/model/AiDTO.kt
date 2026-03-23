package com.app.meditation.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AiDTO(
    @SerialName("candidates")
    val candidates: List<Candidate>,
    @SerialName("modelVersion")
    val modelVersion: String,
    @SerialName("responseId")
    val responseId: String,
    @SerialName("usageMetadata")
    val usageMetadata: UsageMetadata
)
@Serializable
data class Candidate(
    @SerialName("content")
    val content: Content,
    @SerialName("finishReason")
    val finishReason: String,
    @SerialName("index")
    val index: Int
)

@Serializable
data class UsageMetadata(
    @SerialName("candidatesTokenCount")
    val candidatesTokenCount: Int,
    @SerialName("promptTokenCount")
    val promptTokenCount: Int,
    @SerialName("promptTokensDetails")
    val promptTokensDetails: List<PromptTokensDetail>,
    @SerialName("thoughtsTokenCount")
    val thoughtsTokenCount: Int,
    @SerialName("totalTokenCount")
    val totalTokenCount: Int
)

@Serializable
data class Content(
    @SerialName("parts")
    val parts: List<Part>,
    @SerialName("role")
    val role: String
)

@Serializable
data class Part(
    @SerialName("text")
    val text: String,
    @SerialName("thoughtSignature")
    val thoughtSignature: String
)

@Serializable
data class PromptTokensDetail(
    @SerialName("modality")
    val modality: String,
    @SerialName("tokenCount")
    val tokenCount: Int
)
