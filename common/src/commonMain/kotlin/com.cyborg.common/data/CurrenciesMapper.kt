package com.cyborg.common.data

import com.cyborg.common.data.model.CurrenciesResponse
import com.cyborg.common.data.model.CurrencyModel

class CurrenciesMapper : Mapper<CurrenciesResponse, List<CurrencyModel>> {

    override fun transform(response: CurrenciesResponse): MutableList<CurrencyModel> {

        val currencyList = response.rates.map {
            CurrencyModel(
                it.key,
                it.value
            )
        }.toMutableList()

        currencyList.add(0, CurrencyModel(response.base, 1.0))
        return currencyList
    }
}