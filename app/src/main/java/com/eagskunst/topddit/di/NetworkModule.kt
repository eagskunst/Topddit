package com.eagskunst.topddit.di

import android.content.Context
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class NetworkModule(context: Context) {
    companion object {
        const val OKHTTP_CACHE_FILE_NAME = "okhttp_cache"
        const val CACHE_SIZE = 1_000_000L
    }

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val cacheFile =
        Cache(
            directory = File(context.cacheDir, OKHTTP_CACHE_FILE_NAME),
            maxSize = CACHE_SIZE,
        )

    private val okHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
            .cache(cacheFile)
            .build()

    private val json =
        Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
        }

    private val converterFactory =
        json.asConverterFactory("application/json; charset=UTF8".toMediaType())

    val retrofitClient: Retrofit =
        Retrofit.Builder()
            .baseUrl("https://reddit.com")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
}
