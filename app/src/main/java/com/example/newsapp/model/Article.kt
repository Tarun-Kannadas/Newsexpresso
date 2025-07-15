package com.example.newsapp.model

import android.icu.text.CaseMap.Title

data class Article(
    val source: Source?,            // <- Required to match nested "source"
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)