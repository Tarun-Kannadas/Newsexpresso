package com.example.newsapp.repository

import com.example.newsapp.api.RetrofitInstances
import com.example.newsapp.model.NewsResponse
import retrofit2.Response

class NewsRepository {
    suspend fun getTopHeadlines(country: String, s: String): Response<NewsResponse> {
        return RetrofitInstances.api.getTopHeadlines(country)
    }
}
