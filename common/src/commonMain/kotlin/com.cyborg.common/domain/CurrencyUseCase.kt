package com.cyborg.common.domain

interface CurrencyUseCase<in R, T> {

    suspend fun execute(request: R?): T
}