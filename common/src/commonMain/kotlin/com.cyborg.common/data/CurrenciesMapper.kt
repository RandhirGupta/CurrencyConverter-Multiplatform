package com.cyborg.common.data

import com.cyborg.common.data.model.CurrenciesResponse
import com.cyborg.common.data.model.CurrencyModel

class CurrenciesMapper : Mapper<CurrenciesResponse, List<CurrencyModel>> {

    override fun transform(response: CurrenciesResponse): List<CurrencyModel> {

        return response.rates.map {
            CurrencyModel(
                it.key,
                it.value
            )
        }
    }
}