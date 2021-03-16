package com.example.tatvasoft.userScreen

import android.util.Log
import com.example.tatvasoft.api.ApiInterface
import com.example.tatvasoft.api.ServiceGenerator
import com.example.tatvasoft.models.Result
import com.example.tatvasoft.utils.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserScreenRepository {
    var apiInterface: ApiInterface = ServiceGenerator.apiClass
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    /**
     * usage get data from API
     * @param
     * @return Response<Result?>
     */
    suspend fun getUsers(): Response<Result?> {
        return withContext(ioDispatcher) {

            val database = apiInterface.getUsers(10,10)

            try {
                return@withContext Response<Result?>(database!!.body())
            }
            catch (e : Exception){
                return@withContext Response<Result?>(e)
            }
        }
    }
}