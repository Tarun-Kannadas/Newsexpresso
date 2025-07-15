package com.example.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.util.Constants
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val newsRepository = NewsRepository()

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun getTopHeadlines(country: String = "us", category: String = "business") {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response: Response<NewsResponse> = newsRepository.getTopHeadlines(
                    country,
                    category,
                    Constants.API_KEY
                )
                if (response.isSuccessful && response.body() != null) {
                    _articles.value = response.body()!!.articles
                    _errorMessage.value = null
                } else {
                    Log.d("API_RESPONSE", response.toString())
                    Log.d("API_BODY", response.body().toString())
                    _errorMessage.value = "Error: ${response.code()} - ${response.errorBody()?.string() ?: "No error message"}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.localizedMessage ?: "Unexpected error"}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
