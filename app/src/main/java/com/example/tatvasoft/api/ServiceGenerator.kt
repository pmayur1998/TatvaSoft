package com.example.tatvasoft.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal object ServiceGenerator {
    val apiClass: ApiInterface
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl(ApiURLS.BASE_URL)
                .client(requestHeader)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }


    /**
     * provide extra config like timeout functionality in API call
     */
    private val requestHeader: OkHttpClient
        private get() = OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).build()
}