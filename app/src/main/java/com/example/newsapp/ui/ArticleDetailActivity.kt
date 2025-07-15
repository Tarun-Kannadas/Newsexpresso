package com.example.newsapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityArticleDetailBinding
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.view.View


class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleUrl = intent.getStringExtra("url")

        // Set up Toolbar
        binding.toolbar.title = "News Article"
        binding.toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        if (articleUrl != null) {
            binding.progressBar.visibility = View.VISIBLE

            binding.webView.apply {
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        binding.progressBar.visibility = View.GONE
                    }

                    override fun onReceivedError(
                        view: WebView?,
                        request: WebResourceRequest?,
                        error: WebResourceError?
                    ) {
                        Toast.makeText(this@ArticleDetailActivity, "Failed to load page", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.GONE
                    }
                }
                settings.javaScriptEnabled = true
                loadUrl(articleUrl)
            }
        } else {
            finish()
        }

        // Modern way to handle back press
        onBackPressedDispatcher.addCallback(this) {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_article_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val url = intent.getStringExtra("url") ?: return true
        return when (item.itemId) {
            R.id.action_open_browser -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)
                true
            }
            R.id.action_share -> {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, url)
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
