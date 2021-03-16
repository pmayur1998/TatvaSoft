package com.example.tatvasoft.api

import com.example.tatvasoft.models.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("users")
    suspend fun getUsers(@Query("offset") res: Int, @Query("limit") res1: Int): Response<Result>?
}