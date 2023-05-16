package com.nkph361

interface Mapper<T, R> {
    fun map(from: T): R
}