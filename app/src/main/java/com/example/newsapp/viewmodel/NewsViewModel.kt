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

    // Exposed LiveData for UI
    private val _articles = MutableLiveData<List<Article>>()  // Used to display current (either all or filtered)
    val articles: LiveData<List<Article>> = _articles

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    // Internal storage for all articles for search purposes
    private val _allArticles = mutableListOf<Article>()

    // Fetch top headlines
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
                    val articles = response.body()!!.articles
                    _allArticles.clear()
                    _allArticles.addAll(articles)

                    _articles.value = articles // Update LiveData for display
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

    // Search within the fetched articles
    fun searchHeadline(query: String) {
        val filtered = _allArticles.filter {
            it.title?.contains(query, ignoreCase = true) == true
        }

        _articles.value = filtered // Reuse _articles LiveData to update UI with search results
    }

    // Optionally reset the filtered list to all articles
    fun resetSearch() {
        _articles.value = _allArticles
    }
}
