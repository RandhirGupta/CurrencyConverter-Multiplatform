package com.cyborg.common.data.repository

import com.cyborg.common.data.ApiService
import com.cyborg.common.data.model.CurrenciesResponse

class CurrenciesRepositoriesImpl<R>(private val currencyApiService: ApiService<R, CurrenciesResponse>) :
    CurrenciesRepository<R, CurrenciesResponse> {

    override suspend fun getCurrencies(request: R?): CurrenciesResponse =
        currencyApiService.execute(request)
}