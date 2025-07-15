package com.example.newsapp.repository

import com.example.newsapp.api.RetrofitInstances
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.util.Constants
import retrofit2.Response

class NewsRepository {

    suspend fun getTopHeadlines(country: String = "us", category: String = "business", apiKey: String = Constants.API_KEY): Response<NewsResponse> {
        return RetrofitInstances.api.getTopHeadlines(country, category, apiKey)
    }
}
