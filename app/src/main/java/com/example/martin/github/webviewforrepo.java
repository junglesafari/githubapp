package com.example.martin.github;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URI;
import java.net.URL;

public class webviewforrepo extends AppCompatActivity {
WebView browser;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_webviewforrepo );
        Intent intent=getIntent();
        String url=intent.getStringExtra( reposactivity.URL_SEND_KEY );
       // browser.setWebViewClient(new mybrowser());
        browser=findViewById( R.id.webview );
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setScrollBarStyle( View.SCROLLBARS_INSIDE_OVERLAY);
        browser.loadUrl(url);

    }


}
