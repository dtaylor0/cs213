package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

/**
 Controls activity_browser.xml
 @author Shyam Patel, Drew Taylor
 */
public class BrowserActivity extends AppCompatActivity {

    WebView webView;
    String url;
    String title;

    /**
     * Overrides onCreate in AppCompatActivity, Gets title from Intent and sets it, implements WebView for the selected museum website.
     * @param savedInstanceState saved data from previous use of this activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        setTitle(title);

        url = intent.getStringExtra("url");
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}