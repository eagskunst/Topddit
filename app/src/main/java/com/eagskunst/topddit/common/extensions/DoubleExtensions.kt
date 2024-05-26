package com.eagskunst.topddit.common.extensions

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime

fun Double?.toZonedDateTime(): ZonedDateTime =
    this?.let {
        ZonedDateTime.of(
            LocalDateTime.ofEpochSecond(
                this.toLong(),
                0,
                ZoneOffset.UTC,
            ),
            ZoneOffset.UTC,
        )
    } ?: ZonedDateTime.now()
