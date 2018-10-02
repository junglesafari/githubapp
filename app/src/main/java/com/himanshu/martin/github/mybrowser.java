package com.himanshu.martin.github;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class mybrowser extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
