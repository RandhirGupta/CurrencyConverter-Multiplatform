package com.cyborg.common.data

interface Mapper<in T, out E> {
    fun transform(response: T): E
}