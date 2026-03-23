package com.app.meditation.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.timeout
import io.ktor.client.request.request
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkClient {

    fun getClient(): HttpClient {
        return HttpClient(OkHttp) {
//            if (BuildConfig.DEBUG) {
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.BODY
            }
//            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }

            engine {
                request {
                    timeout {
                        requestTimeoutMillis = 60000
                        connectTimeoutMillis = 60000
                        socketTimeoutMillis = 60000
                    }
                }
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "generativelanguage.googleapis.com"
                }
            }
        }
    }
}