package com.cyborg.common.data.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.AndroidClientEngine
import io.ktor.client.engine.android.AndroidEngineConfig

actual fun getHttpEngine(): HttpClientEngine = AndroidClientEngine(AndroidEngineConfig())