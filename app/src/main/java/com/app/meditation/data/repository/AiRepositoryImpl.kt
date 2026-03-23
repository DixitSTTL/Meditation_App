package com.app.meditation.data.repository

import com.app.meditation.data.model.AiDTO
import com.app.meditation.data.model.ModelAiChat
import com.app.meditation.domain.repository.AiRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.util.StringValues
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AiRepositoryImpl(
    private var httpClient: HttpClient
) : AiRepository {
    val flowList = MutableStateFlow(mutableListOf<ModelAiChat>())

    override suspend fun sendQuery(query: String) {

        val updatedList = flowList.value.toMutableList()

        updatedList.add(ModelAiChat(owner = "user", str = query))
        flowList.value = updatedList

        val response = httpClient.post(
            "v1beta/models/gemini-3-flash-preview:generateContent"
        ) {
            setBody(
                mapOf(
                    "contents" to listOf(
                        mapOf(
                            "parts" to listOf(
                                mapOf(
                                    "text" to query
                                )
                            )
                        )
                    )
                )
            )
            headers {
                appendAll(StringValues.build {
                    append(
                        "x-goog-api-key",
                        "AIzaSyC8ZYaeTZcCOwiffsNBU_w-Zi3hOskjzEE"
                    )

                    append(
                        HttpHeaders.ContentType,
                        "application/json"
                    )
                })
            }
        }

        if (response.status.value == 200) {
            val body = response.body<AiDTO>()

            val candidate = body.candidates[0]?:null
            val message = candidate?.content?.parts[0]?.text


            message?.let { it ->
                val newList = flowList.value.toMutableList()
                newList.add(ModelAiChat(owner = "ai", str = it))
                flowList.value = newList
            }

        }
    }

    override fun getMessageList(): Flow<List<ModelAiChat>> {
        return flowList

    }


}