package com.raywenderlich.coursesshemajamebeli.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

    val dataService by lazy { createDataService() }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun createDataService():Network{
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl("https://run.mocky.io/v3/")
        retrofitBuilder.client(
            OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()
        )
        retrofitBuilder.addConverterFactory(mochiConvertor())
        return retrofitBuilder.build().create(Network::class.java)
    }

    private fun mochiConvertor() =
        MoshiConverterFactory.create(
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
        )

}