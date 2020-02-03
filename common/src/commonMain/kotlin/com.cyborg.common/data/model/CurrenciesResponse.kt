package com.cyborg.common.data.model

import com.badoo.reaktive.observable.Observable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrenciesResponse(

    @SerialName("base")
    val base: String,

    @SerialName("date")
    val date: String,

    @SerialName("rates")
    val rates: HashMap<String, Double>
)