package com.example.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repository.NewsRepository
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

    fun getTopHeadlines(country: String = "us") {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response: Response<NewsResponse> = newsRepository.getTopHeadlines(
                    country,
                    "bada598e0a894693b9d0a416016002cc" // Your API key here
                )
                if (response.isSuccessful && response.body() != null) {
                    _articles.value = response.body()!!.articles
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
