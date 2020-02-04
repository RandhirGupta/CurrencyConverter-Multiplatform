package com.cyborg.common.data

import com.cyborg.common.data.model.CurrenciesResponse
import com.cyborg.common.data.network.getHttpEngine
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.HttpHeaders
import io.ktor.http.takeFrom
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

class CurrenciesApiService(
    private val currencyUrl: String,
    private val baseCurrency: String
) :
    ApiService<String, CurrenciesResponse> {

    private val client: HttpClient by lazy {
        HttpClient(getHttpEngine()) {
            install(JsonFeature) {
                serializer = KotlinxSerializer().apply {
                    setMapper(CurrenciesResponse::class, CurrenciesResponse.serializer())
                }
            }

            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.HEADERS
            }
        }
    }

    private fun HttpRequestBuilder.apiUrl(path: String? = null) {
        header(HttpHeaders.CacheControl, "no-cache")
        url {
            takeFrom(currencyUrl).parameters.append("base", baseCurrency)
            path?.let {
                encodedPath = it
            }
        }
    }

    @UnstableDefault
    override suspend fun execute(request: String?): CurrenciesResponse {

        val httpResponse = client.get<HttpResponse> {
            apiUrl()
//            parameter("s", request)
        }

        val json = httpResponse.readText()

        return Json.nonstrict.parse(CurrenciesResponse.serializer(), json)
    }

}