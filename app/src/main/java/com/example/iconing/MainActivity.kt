package com.glisgames.iconing

import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import java.io.InputStream
import java.util.Properties

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)
        // Make sure to enable JavaScript
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.databaseEnabled = true

        // Enable cookies
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(webView, true)

        webView.webViewClient = WebViewClient()
        val url = getUrlFromConfig()
        webView.loadUrl(url)
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    private fun getUrlFromConfig(): String {
        return try {
            val rawResource: InputStream = resources.openRawResource(R.raw.config)
            val properties = Properties()
            properties.load(rawResource)
            properties.getProperty("url", "https://sey.bet/") // Default URL
        } catch (e: Exception) {
            e.printStackTrace()
            "https://sey.bet/" // Default URL in case of error
        }
    }
}