package com.example.leozinhoanimes.util

import com.example.leozinhoanimes.api.JikanAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val retrofit = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val jikanApi = retrofit.create(JikanAPI::class.java)
}