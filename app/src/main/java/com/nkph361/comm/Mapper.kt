package com.nkph361.comm

interface Mapper<T, R> {
    fun map(from: T): R
}