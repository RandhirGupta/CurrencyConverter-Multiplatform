package com.cyborg.common.data

interface ApiService<in R, T> {

    suspend fun execute(request: R?): T
}