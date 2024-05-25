package com.eagskunst.topddit.common

interface Mapper<T, out R> {
    suspend fun map(value: T): R
}
