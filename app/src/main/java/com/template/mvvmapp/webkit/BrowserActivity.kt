package com.template.mvvmapp.webkit

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.webkit.*
import com.template.base.BaseActivity
import com.template.mvvmapp.R
import kotlinx.android.synthetic.main.activity_browser.*
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * 网页浏览器界面
 */
class BrowserActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_browser

    private var title: String? = null
    private lateinit var url: String
    private var isShow = true

    /**
     * 是否一直显示指定title
     */
    var isTitleSticky = false
    var isHostTrust = false
    private val httpCode = 0x111
    var client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS).build()
    private var call: Call? = null
    var handler = Handler { msg ->
        when (msg.what) {
            httpCode -> when (msg.obj) {
                is Response -> {
                    val response = msg.obj as Response
                    when (response.code) {
                        404 -> web.loadUrl("file:///android_asset/web_404.html")
                        200 -> web.loadUrl(url)
                        else -> web.loadUrl(url)
                    }
                }
                is IOException -> {
                    web.loadUrl("file:///android_asset/web_error.html")
                }
                else -> {
                    web.loadUrl(url)
                }
            }
        }
        false
    }

    override fun initViews() {
        super.initViews()
        setSupportActionBar(toolbar)
        initExtra()
        initToolBar(toolbar, title, true)
        init()
        load()
    }

    private fun initExtra() {
        val uri = intent.data
        if (uri != null) {
            url = uri.getQueryParameter(URL) ?: ""
            title = uri.getQueryParameter(TITLE) ?: ""
            isTitleSticky = uri.getBooleanQueryParameter(TITLE_STICKY, false)
        }
        isHostTrust = URLUtil.isNetworkUrl(url)
    }

    private fun load() {
        if (isShow) {
            if (!TextUtils.isEmpty(url)) {
                if (!isHostTrust) {
                    web.loadUrl("file:android_asset/web_host_error.html")
                    return
                }
                val request: Request = Request.Builder().url(url).tag(this).build()
                if (request.isHttps) {
                    web.loadUrl(url)
                } else {
                    call = client.newCall(request)
                    call!!.enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            e.printStackTrace()
                            val msg = handler.obtainMessage()
                            msg.what = httpCode
                            msg.obj = e
                            handler.sendMessage(msg)
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val msg = handler.obtainMessage()
                            msg.what = httpCode
                            msg.obj = response
                            handler.sendMessage(msg)
                        }
                    })
                }
            } else {
                web.loadUrl("file:///android_asset/web_url_error.html")
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        val settings: WebSettings = web.getSettings()
        settings.allowContentAccess = true
        settings.allowFileAccess = true
        settings.databaseEnabled = true
        settings.javaScriptEnabled = true
        settings.defaultTextEncodingName = "UTF-8"
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mixedContentMode =
            WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        web.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                if (!pb.isShown) {
                    pb.visibility = View.VISIBLE
                }
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView, url: String) {
                if (pb.isShown) pb.visibility = View.GONE
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.startsWith("tel:")) {
                    val tel = Uri.parse(url)
                    val intent = Intent(Intent.ACTION_DIAL, tel)
                    startActivity(intent)
                }
                if (URLUtil.isNetworkUrl(url)) {
                    view.loadUrl(url)
                } else {
                    view.loadUrl("file:///android_asset/web_host_error.html")
                }
                return true
            }
        }
        web.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                setPageProgress(newProgress)
                super.onProgressChanged(view, newProgress)
            }

            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
                if (!isTitleSticky) {
                    //if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
                    initToolBar(toolbar, title, true)
                }
            }
        }
    }

    @Synchronized
    private fun setPageProgress(p: Int) {
        pb.progress = p
    }

    override fun onBackPressed() {
        if (web.canGoBack()) {
            web.goBack()
            return
        }
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        call?.cancel()
    }

    fun setWebViewData(data: String) {
        web.loadDataWithBaseURL("", data, "text/html", "UTF-8", "")
    }

    companion object {
        const val TITLE = "title"
        const val TITLE_STICKY = "title_sticky"
        const val URL = "url"
    }
}