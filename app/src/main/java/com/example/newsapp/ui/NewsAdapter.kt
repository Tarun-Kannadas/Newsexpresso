package com.example.newsapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemArticleBinding
import com.example.newsapp.model.Article

class NewsAdapter(private var articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.apply {
            tvTitle.text = article.title
            tvSource.text = article.source?.name
            tvPublishedAt.text = article.publishedAt?.substring(0, 10)

            if (!article.urlToImage.isNullOrBlank()) {
                ivArticleImage.visibility = View.VISIBLE
                Glide.with(ivArticleImage.context)
                    .load(article.urlToImage)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(ivArticleImage)
            } else {
                ivArticleImage.visibility = View.GONE
            }

            root.setOnClickListener {
                val intent = Intent(root.context, ArticleDetailActivity::class.java).apply {
                    putExtra("url", article.url)
                }
                root.context.startActivity(intent)
            }
        }
    }

    fun submitList(newList: List<Article>) {
        articles = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = articles.size
}

