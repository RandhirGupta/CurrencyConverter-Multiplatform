package com.cyborg.common.data.repository

interface CurrenciesRepository<in R, T> {

    suspend fun getCurrencies(request: R?): T
}