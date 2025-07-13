package com.example.newsapp.api

import com.example.newsapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "bada598e0a894693b9d0a416016002cc"
    ): Response<NewsResponse>
}
