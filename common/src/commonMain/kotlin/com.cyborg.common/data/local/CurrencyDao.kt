package com.cyborg.common.data.local

import com.cyborg.common.data.model.CurrenciesResponse
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

class CurrencyDao(private val databaseHelper: DatabaseHelper) : AbstractDao<CurrenciesResponse> {

    @ImplicitReflectionSerializer
    @UnstableDefault
    override fun insert(model: CurrenciesResponse) {
        databaseHelper.database.currencyQueries.insert(
            model.base,
            Json.nonstrict.toJson(model).toString()
        )
    }

    @UnstableDefault
    override fun getAllCurrency(): List<CurrenciesResponse> {

        val currencies = databaseHelper.database.currencyQueries.getAllCurrency().executeAsList()

        return currencies.map {
            Json.parse(CurrenciesResponse.serializer(), it.currency_meta)
        }
    }

    @UnstableDefault
    override fun getCurrency(baseCurrency: String): CurrenciesResponse? {
        val currency =
            databaseHelper.database.currencyQueries.getCurrency(baseCurrency).executeAsOneOrNull()

        return if (currency != null) {
            Json.parse(CurrenciesResponse.serializer(), currency.currency_meta)
        } else {
            null
        }
    }

    override fun clearAll() {
        databaseHelper.database.currencyQueries.deleteAll()
    }

    override fun clearCurrencyMeta(baseCurrency: String) {
        databaseHelper.database.currencyQueries.deleteCurrencyMeta(baseCurrency)
    }
}