package com.cyborg.common.domain

import com.cyborg.common.data.repository.CurrenciesRepository

class CurrencyUseCaseImpl<R, T>(private val currencyRepository: CurrenciesRepository<R, T>) :
    CurrencyUseCase<R, T> {

    override suspend fun execute(request: R?): T = currencyRepository.getCurrencies(request)
}