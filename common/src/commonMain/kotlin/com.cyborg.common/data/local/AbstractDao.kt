package com.cyborg.common.data.local

import com.cyborg.common.data.model.CurrenciesResponse

interface AbstractDao<T> {

    fun insert(model: T)

    fun getCurrency(baseCurrency: String): CurrenciesResponse?

    fun getAllCurrency(): List<T>

    fun clearCurrencyMeta(baseCurrency: String)

    fun clearAll()
}