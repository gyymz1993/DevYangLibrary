package com.gystar.master.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.just.agentwebX5.AgentWebX5;
import com.just.agentwebX5.ChromeClientCallbackManager;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import vaxsoft.com.vaxphone.R;


/**
 * 创建人：$ gyymz1993
 * 创建时间：2017/10/16 10:09
 */

public abstract class BaseWebMvpActivity extends AppCompatActivity {
    protected AgentWebX5 mAgentWeb;
    LinearLayout container;
    public static final String CONTRL = "JSCallMobile";
    public EditText editText;
    public Button tvGo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_web);
        container = findViewById(R.id.container);
        editText=findViewById(R.id.id_ed_text);
        tvGo=findViewById(R.id.id_tv_go);
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
          //  return super.shouldOverrideUrlLoading(view, request);
            view.loadUrl(mUrl);
            return true;

        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
            Log.i("Info", "BaseWebActivity onPageStarted");
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            //T_.showCustomToast("请检查网络");
            // showNoNetworkView();
            //  showEmptyView(R.mipmap.icon_no_network, "请检查网络");
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
        }


    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Log.i("Info", "progress:" + newProgress);
        }


    };
    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (!title.contains("网页无法")) {

            }
        }
    };


    protected AgentWebX5.CommonAgentBuilder getWebBuilder() {
        long p = System.currentTimeMillis();
        return AgentWebX5.with(this)
                //new LinearLayout.LayoutParams(-1, -1)
                .setAgentWebParent(container, new LinearLayout.LayoutParams(-1, -1))
                //.useDefaultIndicator()
                //.defaultProgressBarColor()
                // .setIndicatorColor(UIUtils.getColor(R.color.apptheme))
                .closeProgressBar()
                .setReceivedTitleCallback(mCallback).
                        setWebChromeClient(mWebChromeClient).
                        setWebViewClient(mWebViewClient).
                        setSecutityType(AgentWebX5.SecurityType.strict);


//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
        // .setWebLayout(new WebLayout(this))
        //preAgentWeb.go(getUrl());
        // long n = System.currentTimeMillis();
        // Log.i("Info", "init used time:" + (n - p));
    }

    public WebView getWebView() {
        WebView mWebView = null;
        if (mAgentWeb != null) {
            mWebView = mAgentWeb.getWebCreator().get();
        }
        return mWebView;

    }

    private String mUrl;

    public void initAgentWeb(String url) {
        mUrl=url;
        mAgentWeb = getWebBuilder().createAgentWeb().ready().go(url);
        // getWebView().setBackgroundColor();
        // getWebView().setBackgroundResource(R.color.apptheme_bg);
        getWebView().setWebContentsDebuggingEnabled(true);
        //设置 缓存模式
       // getWebView().getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        // 开启 DOM storage API 功能
        getWebView().getSettings().setDomStorageEnabled(true);
        //开启 database storage API 功能
        getWebView().getSettings().setDatabaseEnabled(true);
        // getWebView().getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        // getWebView().getSettings().setDomStorageEnabled(true);
        WebSettings webSetting = getWebView().getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();


    }

    protected abstract void showTitle(String string);

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb == null) {
            return super.onKeyDown(keyCode, event);
        }
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }
}
