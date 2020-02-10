package com.cyborg.common.data.repository

import com.cyborg.common.data.ApiService
import com.cyborg.common.data.local.AbstractDao
import com.cyborg.common.data.model.CurrenciesResponse

class CurrenciesRepositoriesImpl<R>(
    private val currencyApiService: ApiService<R, CurrenciesResponse>,
    private val currencyDao: AbstractDao<CurrenciesResponse>
) :
    CurrenciesRepository<R, CurrenciesResponse> {

    override suspend fun getCurrencies(request: R?): CurrenciesResponse {

        var currencyResponse = currencyDao.getCurrency("EUR")

        return if (currencyResponse != null) {
            currencyResponse
        } else {
            currencyResponse = currencyApiService.execute(request)

            currencyDao.clearCurrencyMeta("EUR")
            currencyDao.insert(currencyResponse)

            currencyResponse
        }
    }
}