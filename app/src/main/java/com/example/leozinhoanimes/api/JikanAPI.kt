package com.example.leozinhoanimes.api



import com.example.leozinhoanimes.model.TopAnimes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JikanAPI {

    @GET("top/anime")
    suspend fun getTopAnime(@Query ("page")page: Int): Response <TopAnimes>

}